import {Link, useLocation} from 'react-router-dom'
import React, {useEffect, useState} from "react";
import axios from "axios";

function UserViewMenu () {
    const location = useLocation()
    const {id} = location.state
    const [menu, setMenu] = useState([])
    const[cart, setCart] = useState([])

    function addToCart(newItem){
        return [...cart, newItem];
    }

    useEffect(() => {
        console.log("Got hre");
        async function getData() {
            axios.get('http://localhost:8080/foodpanda/getRestaurantMenu/' + location.state.id)
                .then(async response => {
                    const data = await response.data
                    console.log(data);
                    setMenu(response.data)
                })
        }
        getData();
    },[])

    const breakfasts = menu.filter(food => food.category === "BREAKFAST");
    const lunches = menu.filter(food => food.category === "LUNCH");
    const desserts = menu.filter(food => food.category === "DESSERT");
    const beverages = menu.filter(food => food.category === "BEVERAGE");

    return(
        <div className="App">
            <header className="App-header"> Breakfast
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
            <header className="App-header"> Lunches
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
            <header className="App-header"> Desserts
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
            <header className="App-header"> Beverages
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