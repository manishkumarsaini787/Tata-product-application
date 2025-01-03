import React, { useState, useEffect } from 'react';

const UserProducts = () => {
    const [products, setProducts] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Fetch products for the logged-in user after component mounts
        const token = localStorage.getItem('token');  // Retrieve JWT token from localStorage
        console.log("Token from localStorage:", token); 
        if (token) {
            // Make a GET request to fetch the products for the user
            fetch('http://localhost:9091/api/user/v1/getuserproducts', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}` // Send the token in Authorization header
                }
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch products');
                }
                return response.json();
            })
            .then(data => {
                console.log("Fetched products:", data);  // Log data for debugging
                setProducts(data);
            })
            .catch(error => {
                setError("Failed to fetch products: " + error.message);  // Set the error if any
                console.error("Error fetching products:", error);
            });
        } else {
            setError("No token found. Please log in.");
        }
    }, []); // Empty array ensures this runs only once when component mounts

    return (
        <div>
            <h2>User Products</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {products.length > 0 ? (
                    products.map((product) => (
                        <li key={product.id}>
                            <strong>{product.name}</strong> - ${product.price}
                            <p>{product.description}</p>
                        </li>
                    ))
                ) : (
                    <p>No products found.</p>
                )}
            </ul>
        </div>
    );
};

export default UserProducts;
