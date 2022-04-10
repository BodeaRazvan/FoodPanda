import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React from "react";

function AdminPage(){
    return (
        <div className="App">
            <header className="App-header">
                <h1> AdminPage </h1>
                <Link to="/login"> Log out </Link>
                <Link to="/adminRestaurant"> My Restaurant </Link>
                <Link to="/adminRestaurantMenu"> View Menu </Link>
                <Link to="/adminViewOrder"> Orders </Link>
            </header>
        </div>
    );
}

export default AdminPage;