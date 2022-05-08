import React, {Component, useEffect, useState} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "./store";

function UserViewRestaurants() {
    const [restaurants, setRestaurants] = useState([]);
    const auth = useAuth();
    const [restaurantNames, setRestaurantNames] = useState('');

    useEffect(() => {
        async function getRestaurants(){
        axios.get('http://localhost:8080/getRestaurants',
            {headers: {'Authorization':  auth.token}}
        )
            .then(async response => {
                const data = await response.data;
                setRestaurants(data);
            })
    }
    getRestaurants();
    }, [])

    return(
        <div className="App">
            <header className="App-header"> Restaurant List
                <br/><br/>
                <p> Search for name</p>
                <input onChange={(e) => setRestaurantNames(e.target.value)}/>
                <br/>
                <ul>
                    {
                        restaurants.filter((restaurant) =>{
                            console.log(restaurantNames)
                            if(restaurantNames === ''){
                                return true;
                            }
                            return restaurant.name.toLowerCase().includes(restaurantNames.toLowerCase());
                        })
                            .map(restaurant =>
                                <li key={restaurant.id}>
                                    {restaurant.name}
                                    <br/>
                                    Location:  <span/><span/>
                                    {restaurant.location}
                                    <br/>
                                    Available zones: <span/><span/>
                                    {restaurant.availableZones}
                                    <br/>
                                    <button>
                                        <Link to = "/UserViewMenu" state={{id: restaurant.id}} > View Menu</Link>
                                    </button>
                                    <br/>
                                    <br/>
                                </li>
                            )
                    }
                </ul>
            </header>
        </div>
    );

}

export default UserViewRestaurants;