import React, {Component, useEffect} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {render} from "react-dom";
import {Link} from "react-router-dom";
import jspdf from "jspdf";
import html2canvas from "html2canvas";
import menuImage from "./menu.png";
import {useAuth} from "./store";
import {useNavigate} from "react-router";

function AdminRestaurantMenu() {
    let navigate = useNavigate();
    const auth = useAuth();
    const [menu, setMenu] = React.useState([]);

    useEffect(() => {
        async function getMenu() {
            axios.get('http://localhost:8080/getRestaurantFoods',
                {headers: {'Authorization':  auth.token}}
            )
                .then(async res => {
                    const data = await res.data;
                    setMenu(data);
                })
        }
        getMenu();
    },[])

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'ADMIN'){
            navigate('/login');
        }
    },[])


    const exportPdf = () => {
        const input = document.getElementById('AdminRestaurantMenu');
        html2canvas(input,{logging:true,letterRendering:1,useCors:true}).then(canvas => {
            const imgWidth = 212;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const imageData = canvas.toDataURL('menu/png');

            const pdf = new jspdf('p', 'mm', 'a4');
            pdf.addImage(imageData, 'PNG', 0, 0, imgWidth, imgHeight);
            pdf.save('menu.pdf');
        });
    }
    const breakfasts = menu.filter(food => food.category === "BREAKFAST");
    const lunches = menu.filter(food => food.category === "LUNCH");
    const desserts = menu.filter(food => food.category === "DESSERT");
    const beverages = menu.filter(food => food.category === "BEVERAGE");

    return (
        <div className="App" >
            <header id = "AdminRestaurantMenu" className="App-header2">
                <img src={menuImage} alt="menuImage" />
                <button onClick={() => exportPdf()}> Print PDF</button>
                <header className="App-header2"> Breakfast
                    <ul>
                        {
                            breakfasts.map(food =>
                                <li key={food.id}>{food.name} {food.price}$</li>
                            )
                        }
                    </ul>
                </header>
                <header className="App-header2"> Lunches
                    <ul>
                        {
                            lunches.map(food =>
                                <li key={food.id}>{food.name} {food.price}$</li>
                            )
                        }
                    </ul>
                </header>
                <header className="App-header2"> Desserts
                    <ul>
                        {
                            desserts.map(food =>
                                <li key={food.id}>{food.name} {food.price}$</li>
                            )
                        }
                    </ul>
                </header>
                <header className="App-header2"> Beverages
                    <ul>
                        {
                            beverages.map(food =>
                                <li key={food.id}>{food.name} {food.price}$</li>
                            )
                        }
                    </ul>
                </header>
            </header>
        </div>
    );
}

export default AdminRestaurantMenu;