import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React from "react";
import {useLocation} from "react-router";

function UserViewCart(){
    const location = useLocation();
    const {cart} = location.state;
    const totalPrice = cart.reduce((total, currValue) => total + currValue.price, 0).toFixed(2);
    const [details, setDetails] = React.useState([]);

    const placeOrder = () =>{
        fetch('http://localhost:8080/foodpanda/placeOrder',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                itemIds: cart.map(item => item.id),
                details: details
            })
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            })
            .catch(err => console.log(err));
    }

    return (
        <div className="App">
            <header className="App-header">
                <p>Total price: {totalPrice} $</p>
                <button onClick={placeOrder}> Place order </button>
               <ul>
                   {
                       cart.map(foodItem =>
                       <li>
                           {foodItem.name} {foodItem.price} $
                       </li>
                       )
                   }
               </ul>
                <label> Details </label>
                <input
                    type="text"
                    value={details}
                    onChange={e => setDetails(e.target.value)}
                    />
            </header>
        </div>
    );
}

export default UserViewCart;