import React, {Component} from "react";
import './App.css';
import './index.css';
import axios from "axios";
import {render} from "react-dom";
import {Link} from "react-router-dom";

class AdminRestaurant extends Component{
    state = {
        restaurant: "",
    }

    async componentDidMount() {
        axios.get('http://localhost:8080/foodpanda/getRestaurant')
            .then(response => {
                const responseData = response.data;
                if(responseData === null){
                    this.props.history.push("/");
                }
                this.setState({restaurant: responseData});
            })
    }

    render()
    {return (
            <div className="App">
                <header className="App-header">
                    <p>
                        {this.state.restaurant.name}
                    </p>
                    <p>
                        {this.state.restaurant.location}
                    </p>
                    <p>
                        {this.state.restaurant.availableZones}
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

}
export default AdminRestaurant;