import './App.css';
import './index.css';
import {Link, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {useAuth} from "./store";

function AddFood (){
    const auth = useAuth();
    let navigate = useNavigate();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [category, setCategory] = useState('');

    const addFood = () =>{
        fetch('http://localhost:8080/addFood',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization':  auth.token
            },
            body:JSON.stringify({
                name: name,
                description: description,
                price: price,
                category: category
            })
        })
            .then(response => response.json())
            .then(data =>{
                console.log(data);
                navigate('/adminRestaurant');
            })
            .catch(error => {
                console.log(error)
            });
    }

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'ADMIN'){
            navigate('/login');
        }
    },[])

    return(
        <div className="App">
            <header className="App-header">
                <h1>Add Food</h1>
                <label> Name: </label>
                <input
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                    id="name"
                />
                <label> Description: </label>
                <input
                    type="text"
                    onChange={(e) => setDescription(e.target.value)}
                    id="location"
                />
                <label> Price </label>
                <input
                    type="text"
                    onChange={(e) => setPrice(e.target.value)}
                    id="availableZones"
                />
                <select
                onChange={(e) => setCategory(e.target.value)}>
                    <option value="">Select Category</option>
                    <option value="Breakfast">Breakfast</option>
                    <option value="Lunch">Lunch</option>
                    <option value="Dessert">Dessert</option>
                    <option value="Beverage">Beverage</option>
                </select>
                <button onClick={addFood}>Add</button>
            </header>
        </div>
    );
}

export default AddFood;