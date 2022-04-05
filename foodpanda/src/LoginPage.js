import './App.css';
import React, {useRef, useState} from "react";
import {Link, useNavigate} from "react-router-dom";

function LoginPage() {
    let navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const inputRefference = useRef(null);
    const inputRefference1 = useRef(null);
    const login = () => {
        fetch('http://localhost:8080/foodpanda/loginuser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.role === "USER"){
                    navigate("/user");
                    return;
                }
                else if(data.role === "ADMIN"){
                    navigate("/admin");
                    return;
                }
                navigate("/login");
            })
            .catch(error => {
                inputRefference.current.value="";
                inputRefference1.current.value="";
                console.log(error);
            });
    }
    return (
        <div className="App">
            <header className="App-header">
                <h1> Login Page </h1>
                <button onClick={login}> Login </button>
                <label> Username:</label>
                <input
                    ref={inputRefference}
                    type="text"
                    onChange={(e) => setUsername(e.target.value)}
                    id="username"
                />
                <label> Password:</label>
                <input
                    ref={inputRefference1}
                    type="password"
                    onChange={(e) => setPassword(e.target.value)}
                    id="password1"
                />
                <p/>
                <Link to="/register">Go to Register</Link>
            </header>
        </div>
    );
}

export default LoginPage;
