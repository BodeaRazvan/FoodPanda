import React, {Component, useState} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {Link} from "react-router-dom";

class UserViewRestaurants extends Component{
    constructor(props) {
        super(props);
        this.state={
            restaurants:[],
            restaurantNames:''
        }
    }

    async componentDidMount(){
        axios.get('http://localhost:8080/foodpanda/getRestaurants')
            .then(response =>{
                const responseData = response.data;
                console.log(responseData);
                this.setState({restaurants:responseData});
            })
    }

    render(){
        return(
        <div className="App">
            <header className="App-header"> Restaurant List
                <br/><br/>
                <p> Search for name</p>
                <input onChange={(e) => this.setState({...this.state, restaurantNames:e.target.value})}/>
                <br/>
                    <ul>
                        {
                            this.state.restaurants.filter((restaurant) =>{
                                console.log(this.state.restaurantNames)
                                if(this.state.restaurantNames === ''){
                                    return true;
                                }
                                return restaurant.name.toLowerCase().includes(this.state.restaurantNames.toLowerCase());
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
}


export default UserViewRestaurants;