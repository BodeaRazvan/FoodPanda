import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React from "react";
import {useLocation} from "react-router";

function UserViewCart(){
    const location = useLocation();
    const {cart} = location.state;
    return (
        <div className="App">
            <header className="App-header">
               <ul>
                   {
                       cart.map(foodItem =>
                       <li>
                           {foodItem.name} {foodItem.price}
                       </li>
                       )
                   }
               </ul>
            </header>
        </div>
    );
}

export default UserViewCart;