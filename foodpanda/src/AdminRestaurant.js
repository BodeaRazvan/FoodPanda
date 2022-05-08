import React, {Component, useEffect} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {render} from "react-dom";
import {Link} from "react-router-dom";
import {useAuth} from "./store";
import {useNavigate} from "react-router";

function AdminRestaurant() {
    let navigate = useNavigate();
    const auth = useAuth();
    const [restaurant, setRestaurant] = React.useState([]);

    useEffect(() => {
        async function getRestaurant() {
            axios.get('http://localhost:8080/getRestaurant',{headers: {'Authorization':  auth.token}})
                .then(async res => {
                    const data = await res.data;
                    setRestaurant(data);
                })
        }
        getRestaurant();
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


    return (
        <div className="App">
            <header className="App-header">
                <p>
                    {restaurant.name}
                </p>
                <p>
                    {restaurant.location}
                </p>
                <p>
                    {restaurant.availableZones}
                </p>
                <button>
                    <Link to="/addRestaurant"> Add / Modify Restaurant</Link>
                </button>
                <button>
                    <Link to="/addFood"> Add Food</Link>
                </button>
            </header>
        </div>
    );
}

export default AdminRestaurant;