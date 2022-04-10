import React, {useEffect} from "react";
import './App.css';
import './index.css';
import {Link, useLocation} from "react-router-dom";
import axios from "axios";

function UserOrderDetails() {
    const location = useLocation()
    const {id} = location.state
    const[food,setFood] = React.useState([]);

    useEffect(()=>{
        async function getData(){
            axios.get('http://localhost:8080/foodpanda/getOrderFoods/' + location.state.id)
                .then(async res=>{
                    const data = await res.data;
                    console.log(data)
                    setFood(res.data)
                })
        }
        getData()
        },[])
    return(
        <div className="App">
            <header className="App-header">
                <h1> Order Details </h1>
                <ul>
                    {food.map(food =>
                        <li key={food.id}>
                            {food.name} <span>, </span>
                            {food.price} <span>$ </span>
                        </li>
                    )}
                </ul>
            </header>
        </div>
    );
}

export default UserOrderDetails;