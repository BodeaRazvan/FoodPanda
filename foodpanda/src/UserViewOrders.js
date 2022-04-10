import React, {useEffect} from "react";
import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import axios from "axios";

function UserViewOrders() {
    const[orders, setOrders] = React.useState([]);
    const[statusFilter, setStatusFilter] = React.useState("");

    useEffect(() => {
        async function getOrders(){
            axios.get('http://localhost:8080/foodpanda/getUserOrders')
                .then(async response => {
                    console.log(response.data);
                    const data = await response.data;
                    setOrders(response.data);
                })
        }
        getOrders();
        },[])

    return(
        <div className="App">
            <header className="App-header">
                <h1> My Orders </h1>
                <select onChange={(e) => setStatusFilter(e.target.value)} >
                    <option value=""> Show All </option>
                    <option value="PENDING"> PENDING </option>
                    <option value="ACCEPTED"> ACCEPTED </option>
                    <option value="IN DELIVERY"> IN DELIVERY </option>
                    <option value="DELIVERED"> DELIVERED </option>
                    <option value="DECLINED"> DECLINED </option>
                </select>
                <ul>
                    {
                     orders.filter((order) =>{
                         if(statusFilter === ""){
                             return true;
                         }else{
                             return order.status === statusFilter;
                         }
                         })
                         .map(order =>
                         <li key={order.id}>
                             {order.restaurant.name}<span>, </span>
                             {order.status}<span>, </span>
                             {order.price}<span>$ </span>
                             <Link to="/OrderDetails" state={{id: order.id}}>
                                 <button > See details</button>
                             </Link>
                         </li>
                     )

                    }
                </ul>
            </header>
        </div>
    );
}

export default UserViewOrders;