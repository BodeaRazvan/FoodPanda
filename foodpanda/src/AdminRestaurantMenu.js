import React, {Component} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {render} from "react-dom";
import {Link} from "react-router-dom";

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
        const breakfasts = this.state.foods.filter(food => food.category === "BREAKFAST");
        const lunches = this.state.foods.filter(food => food.category === "LUNCH");
        const desserts = this.state.foods.filter(food => food.category === "DESSERT");
        const beverages = this.state.foods.filter(food => food.category === "BEVERAGE");
        return (
        <div className="App">
            <header className="App-header"> Breakfast
                <ul>
                    {
                        breakfasts.map(food =>
                        <li key={food.id}>{food.name} {food.price}$</li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header"> Lunches
                <ul>
                    {
                        lunches.map(food =>
                            <li key={food.id}>{food.name} {food.price}$</li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header"> Desserts
                <ul>
                    {
                        desserts.map(food =>
                            <li key={food.id}>{food.name} {food.price}$</li>
                        )
                    }
                </ul>
            </header>
            <header className="App-header"> Beverages
                <ul>
                    {
                        beverages.map(food =>
                            <li key={food.id}>{food.name} {food.price}$</li>
                        )
                    }
                </ul>
            </header>
        </div>
    );
    }

}
export default AdminRestaurantMenu;