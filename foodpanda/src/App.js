import './App.css';
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import AdminPage from "./AdminPage";
import UserPage from "./UserPage";
import AdminRestaurant from "./AdminRestaurant";
import AddRestaurant from "./AddRestaurant";
import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    //Link
} from "react-router-dom";
import AddFood from "./AddFood";

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
         </Routes>
     </Router>
  );
}

export default App;
