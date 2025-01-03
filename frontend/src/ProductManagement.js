// src/Components/ProductManagement.js

import React, { useState, useEffect } from "react";
import AddProduct from "./AddProduct"; // Import AddProduct component

const ProductManagement = () => {
  const [products, setProducts] = useState([]);
  const token = localStorage.getItem("token"); // Retrieve token for authentication

  // Fetch all products when the component mounts
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8083/api/products/getallproduct", {
          method: "GET",
          headers: {
            "Authorization": `Bearer ${token}`, // Attach token for authentication
          },
        });

        if (response.ok) {
          const data = await response.json();
          setProducts(data); // Set products in state
        } else {
          alert("Failed to fetch products.");
        }
      } catch (error) {
        console.error("Error fetching products:", error);
        alert("Error fetching products.");
      }
    };

    if (token) {
      fetchProducts(); // Only fetch if a token is available
    } else {
      alert("Please log in to view products.");
    }
  }, [token]);

  return (
    <div>
      <h2>Product Management</h2>
      <AddProduct /> {/* Include the AddProduct form here */}
      
      <h3>Product List</h3>
      <ul>
        {products.length > 0 ? (
          products.map((product) => (
            <li key={product.id}>
              {product.name} - ${product.price} - <img src={product.imgUrl} alt={product.name} width="50" />
            </li>
          ))
        ) : (
          <p>No products found.</p>
        )}
      </ul>
    </div>
  );
};

export default ProductManagement;
