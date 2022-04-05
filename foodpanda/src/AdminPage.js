import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React from "react";

function AdminPage(){
    return (
        <div className="App">
            <header className="App-header">
                <h1> AdminPage </h1>
                <Link to="/register">Go to Register</Link>
                <Link to="/adminRestaurant"> My Restaurant</Link>
            </header>
        </div>
    );
}

export default AdminPage;