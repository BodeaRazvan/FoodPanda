import React, {useEffect} from "react";
import './App.css';
import './index.css';
import {Link, useLocation} from "react-router-dom";
import axios from "axios";
import {useAuth} from "./store";
import {useNavigate} from "react-router";

function UserOrderDetails() {
    let navigate = useNavigate();
    const location = useLocation()
    const {id} = location.state
    const[food,setFood] = React.useState([]);
    const auth = useAuth();

    useEffect(()=>{
        async function getData(){
            axios.get('http://localhost:8080/getOrderFoods/' + location.state.id,
                {headers:{ Authorization: auth.token}})
                .then(async res=>{
                    const data = await res.data;
                    console.log(data)
                    setFood(res.data)
                })
        }
        getData()
        },[])

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
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