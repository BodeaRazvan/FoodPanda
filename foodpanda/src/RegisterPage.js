import './App.css';
import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";

function RegisterPage() {
    let navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [password1, setPassword1] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');
    const register = () => {
        fetch('http://localhost:8080/foodpanda/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password1: password,
                password2: password1,
                email: email,
                address: address
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                navigate("/login");
            })
            .catch(error => {
                console.log(error);
            });
    }
    return (
        <div className="App">
            <header className="App-header">
                <h1> Register Page </h1>
                <button onClick={register}> Register </button>
                <label> Username:</label>
                <input
                    type="text"
                    onChange={(e) => setUsername(e.target.value)}
                    id="username"
                />
                <label> Email:</label>
                <input
                    type="text"
                    onChange={(e) => setEmail(e.target.value)}
                    id="email"
                />
                <label> Password:</label>
                <input
                    type="password"
                    onChange={(e) => setPassword(e.target.value)}
                    id="password"
                />
                <label> Repeat password:</label>
                <input
                    type="password"
                    onChange={(e) => setPassword1(e.target.value)}
                    id="password1"
                />
                <label> Address:</label>
                <input
                    type="text"
                    onChange={(e) => setAddress(e.target.value)}
                    id="address"
                />
                <p/>
                <Link to="/login">Go to Login</Link>
            </header>
        </div>
    );
}

export default RegisterPage;
