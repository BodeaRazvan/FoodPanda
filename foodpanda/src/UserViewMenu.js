import {Link, useLocation} from 'react-router-dom'
import React, {useEffect, useState} from "react";
import axios from "axios";
import {useAuth} from "./store";
import {useNavigate} from "react-router";

function UserViewMenu () {
    let navigate = useNavigate();
    const location = useLocation()
    const {id} = location.state
    const [menu, setMenu] = useState([])
    const[cart, setCart] = useState([])
    const auth = useAuth();

    function addToCart(newItem){
        return [...cart, newItem];
    }

    useEffect(() => {
        console.log("Got hre");
        async function getData() {
            axios.get('http://localhost:8080/getRestaurantMenu/' + location.state.id,
                {headers: {Authorization: auth.token}}
                )
                .then(async response => {
                    const data = await response.data
                    console.log(data);
                    setMenu(response.data)
                })
        }
        getData();
    },[])

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    const breakfasts = menu.filter(food => food.category === "BREAKFAST");
    const lunches = menu.filter(food => food.category === "LUNCH");
    const desserts = menu.filter(food => food.category === "DESSERT");
    const beverages = menu.filter(food => food.category === "BEVERAGE");

    return(
        <div className="App">
            <header className="App-header2"> Breakfast
                <ul>
                    {
                        breakfasts.map(food =>
                            <li
                                key={food.id}>{food.name} {food.price}$
                                <button onClick={()=>setCart(addToCart(food))}> Add to cart </button>
                            </li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header2"> Lunches
                <ul>
                    {
                        lunches.map(food =>
                            <li
                                key={food.id}>{food.name} {food.price}$
                                <button onClick={() => setCart(addToCart(food))}> Add to cart </button>
                            </li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header2"> Desserts
                <ul>
                    {
                        desserts.map(food =>
                            <li
                                key={food.id}>{food.name} {food.price}$
                                <button onClick={() => setCart(addToCart(food))}> Add to cart </button>
                            </li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header2"> Beverages
                <ul>
                    {
                        beverages.map(food =>
                            <li
                                key={food.id}>{food.name} {food.price}$
                                <button onClick={() => setCart(addToCart(food))}> Add to cart </button>
                            </li>
                        )
                    }
                </ul>
                <Link to="/UserViewCart" state={{cart : cart}} >
                <button onClick={()=>console.log(cart)}> See Cart </button>
                </Link>
            </header>
        </div>
    );

}
export default UserViewMenu