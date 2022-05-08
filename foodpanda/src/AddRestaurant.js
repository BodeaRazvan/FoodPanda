import './App.css';
import './index.css';
import {Link, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {useAuth} from "./store";

function AddRestaurant (){
    const auth = useAuth();
    let navigate = useNavigate();
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [availableZones, setAvailableZones] = useState('');

    const addRestaurant = () =>{
        fetch('http://localhost:8080/addRestaurant',{
            method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': auth.token
                },
                body:JSON.stringify({
                    name: name,
                    location: location,
                    availableZones: availableZones
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
                <h1>Add/Modify Restaurant</h1>
                <label> Name: </label>
                <input
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                    id="name"
                />
                <label> Location: </label>
                <input
                    type="text"
                    onChange={(e) => setLocation(e.target.value)}
                    id="location"
                />
                <label> Available Zones: </label>
                <input
                       type="text"
                       onChange={(e) => setAvailableZones(e.target.value)}
                       id="availableZones"
                />
                <button onClick={addRestaurant}>Add/Modify</button>
            </header>
        </div>
    );
}

export default AddRestaurant;