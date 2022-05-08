import './App.css';
import './index.css';
import {Link} from "react-router-dom";
import React, {useEffect} from "react";
import {useNavigate} from "react-router";
import {useAuth} from "./store";

function AdminPage(){
    let navigate = useNavigate();
    const auth = useAuth();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'ADMIN'){
            navigate('/login');
        }
    },[])

    return (
        <div className="App">
            <header className="App-header">
                <h1> AdminPage </h1>
                <Link to="/adminRestaurant"> My Restaurant </Link>
                <Link to="/adminRestaurantMenu"> View Menu </Link>
                <Link to="/adminViewOrder"> Orders </Link>
            </header>
        </div>
    );
}

export default AdminPage;