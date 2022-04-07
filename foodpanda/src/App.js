import {
    BrowserRouter as Router,
    Routes,
    Route,
    //Link
} from "react-router-dom";
import React from "react";

import './App.css';
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import AdminPage from "./AdminPage";
import UserPage from "./UserPage";
import AdminRestaurant from "./AdminRestaurant";
import AddRestaurant from "./AddRestaurant";
import AddFood from "./AddFood";
import AdminRestaurantMenu from "./AdminRestaurantMenu";
import UserViewRestaurants from "./UserViewRestaurants";
import UserViewMenu from "./UserViewMenu";
import UserViewCart from "./UserViewCart";

function App() {
  return (
     <Router>
         <Routes>
             <Route path="/" element={<LoginPage/>} />
             <Route path="/login" element={<LoginPage />} />
             <Route path="/register" element={<RegisterPage />} />
             <Route path="/admin" element={<AdminPage />} />
             <Route path="/user" element={<UserPage />} />
             <Route path="/adminRestaurant" element={<AdminRestaurant/>} />
             <Route path="/addRestaurant" element={<AddRestaurant/>} />
             <Route path="/addFood" element={<AddFood/>} />
             <Route path="/AdminRestaurantMenu" element={<AdminRestaurantMenu/>} />
             <Route path="/UserViewRestaurants" element = {<UserViewRestaurants/>} />
             <Route path="/UserViewMenu" element = {<UserViewMenu/>} />
             <Route path="/UserViewCart" element = {<UserViewCart/>} />
         </Routes>
     </Router>
  );
}

export default App;
