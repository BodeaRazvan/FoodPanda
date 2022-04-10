import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";



function AdminViewOrder(){
    const[orders, setOrders] = useState([])
    const[selectedOrder, setSelectedOrder] = useState(0)
    const[statusFilter, setStatusFilter] = useState('')
    const[priceFilter, setPriceFilter] = useState('')

    useEffect(() => {
        async function getOrders(){
            axios.get('http://localhost:8080/foodpanda/getOrders')
                .then(async response =>{
                    const data = await response.data;
                    console.log(data);
                    setOrders(response.data)
                })
        }
        getOrders();
    }, []);

    function declineOrder(orderId){
        fetch('http://localhost:8080/foodpanda/declineOrder', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderId)
        })
            .then(response => response.json())
            .then(data => {
                setOrders(orders.map(order => {
                    if(order.id === data.id){
                        order.status = data.status;
                    }
                    return order;
                }))
            })
    }

    function nextStatus(orderId){
        fetch('http://localhost:8080/foodpanda/nextOrderStatus', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderId)
        })
            .then(response => response.json())
            .then(data => {
               setOrders(orders.map(order => {
                   if(order.id === data.id){
                       order.status = data.status;
                   }
                   return order;
               }))
            })
    }

    return (
        <div className="App">
            <header className="App-header">
                <h1> Orders </h1>
                <p> Filter by Status</p>
                <select onChange={(e) => setStatusFilter(e.target.value)} >
                    <option value=""> Show All </option>
                    <option value="PENDING"> PENDING </option>
                    <option value="ACCEPTED"> ACCEPTED </option>
                    <option value="IN DELIVERY"> IN DELIVERY </option>
                    <option value="DELIVERED"> DELIVERED </option>
                    <option value="DECLINED"> DECLINED </option>
                </select>
                <p>
                    Price less than
                </p>
                <input onChange={(e)=>setPriceFilter(e.target.value)}/>
                <ul>
                    {
                        orders.filter((order) => {
                            if (statusFilter === ''){
                                if(priceFilter === ''){
                                    return true;
                                }
                                else{
                                    return order.price < priceFilter;
                                }
                            }else{
                                if(priceFilter === ''){
                                    return order.status === statusFilter;
                                }
                                else{
                                    return order.status === statusFilter && order.price < priceFilter;
                                }
                            }
                        })
                            .map(order =>
                            <li key={order.id}>
                                {order.user.username} <span/>
                                {order.price}$ <span/>
                                {order.status} <span/>
                                { ((order.status !== 'DECLINED') && (order.status !== 'DELIVERED')) &&
                                    <button onClick={() => nextStatus(order.id)}> Next Step</button>
                                } <span/>
                                { ((order.status !== 'DECLINED') && (order.status !== 'DELIVERED')) &&
                                <button onClick={() => {declineOrder(order.id) }}> Decline</button>
                                } <span/>
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

export default AdminViewOrder;