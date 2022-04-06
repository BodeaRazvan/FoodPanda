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
                    <ul>
                        {
                            this.state.restaurants.map(restaurant =>
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