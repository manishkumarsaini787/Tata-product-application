import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Registration from "./Registration";
import ProductManagement from "./ProductManagement";
import MainApp from "./MainApp"; // Assuming this is your landing page or dashboard

const App = () => {
  return (
    <BrowserRouter>
      <div>
        <Routes>
          {/* Define the route for the root path */}
          <Route path="/" element={<MainApp />} />

          {/* Define routes for login, registration, and product management */}
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Registration />} />
          <Route path="/products" element={<ProductManagement />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
};

export default App;
