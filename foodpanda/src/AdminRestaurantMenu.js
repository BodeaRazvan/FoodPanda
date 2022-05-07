import React, {Component} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {render} from "react-dom";
import {Link} from "react-router-dom";
import jspdf from "jspdf";
import html2canvas from "html2canvas";
import menuImage from "./menu.png";


class AdminRestaurantMenu extends Component{
    state = {
        foods: []
    }

    async componentDidMount() {
        axios.get('http://localhost:8080/foodpanda/getRestaurantFoods')
            .then(response => {
                const responseData = response.data;
                if(responseData === null){
                    this.props.history.push("/");
                }
                console.log(responseData);
                this.setState({foods: responseData});
            })
    }

    render() {
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
        const breakfasts = this.state.foods.filter(food => food.category === "BREAKFAST");
        const lunches = this.state.foods.filter(food => food.category === "LUNCH");
        const desserts = this.state.foods.filter(food => food.category === "DESSERT");
        const beverages = this.state.foods.filter(food => food.category === "BEVERAGE");
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

}
export default AdminRestaurantMenu;