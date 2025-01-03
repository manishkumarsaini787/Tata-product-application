
// import React, { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";

// const ProductList = () => {
//   const [products, setProducts] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState("");
//   const navigate = useNavigate();

//   useEffect(() => {
//     const fetchProducts = async () => {
//       try {
//         const response = await fetch("http://localhost:9091/api/user/v1/getuserproducts ", {
//           headers: {
//             Authorization: `Bearer ${localStorage.getItem("token")}`,
//             "Content-Type": "application/json",
//           },
//         });

//         if (!response.ok) {
//           throw new Error("Failed to fetch products");
//         }

//         const data = await response.json();
//         setProducts(data); // Assuming data is an array of products
//       } catch (err) {
//         setError("Error fetching products: " + err.message);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchProducts();
//   }, []);

//   const handleDelete = async (id) => {
//     try {
//       const response = await fetch(`http://localhost:9091/api/user/v1/delete/product/${id}`, {
//         method: "DELETE",
//         headers: {
//           Authorization: `Bearer ${localStorage.getItem("token")}`,
//           "Content-Type": "application/json",
//         },
//       });

//       if (!response.ok) {
//         throw new Error("Failed to delete product");
//       }

//       // Remove the deleted product from the state
//       setProducts(products.filter((product) => product.id !== id));
//     } catch (err) {
//       setError("Error deleting product: " + err.message);
//     }
//   };

//   if (loading) {
//     return <p>Loading products...</p>;
//   }

//   if (error) {
//     return <p>{error}</p>;
//   }

//   return (
//     <div>
//       <h2>Product List</h2>
//       {products.length === 0 ? (
//         <p>No products found.</p>
//       ) : (
//         <ul>
//           {products.map((product) => (
//             <li key={product.id}>
//               <h3>{product.name || "Unnamed Product"}</h3>
//               <p>{product.description || "No description available"}</p>
//               <p>Price: ${product.price || "N/A"}</p>
//               <button onClick={() => navigate(`/products/manage/${product.id}`)}>Edit</button>
//               <button onClick={() => handleDelete(product.id)}>Delete</button>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default ProductList;








// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom';

// const ProductList = () => {
//   const [products, setProducts] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState('');
//   const navigate = useNavigate();

//   // Fetch products on component mount
//   useEffect(() => {
//     const fetchProducts = async () => {
//       try {
//         const response = await fetch('http://localhost:9091/api/products', {
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json'
//           },
//         });

//         // if (!response.ok) {
//         //   throw new Error('Failed to fetch products');
//         // }

//         const data = await response.json();
//         setProducts(data);
//       } catch (err) {
//         setError(err.message);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchProducts();
//   }, []);

//   // Handle product deletion
//   const handleDelete = async (id) => {
//     if (window.confirm('Are you sure you want to delete this product?')) {
//       try {
//         const response = await fetch(`http://localhost:9091/api/products/delete/${id}`, {
//           method: 'DELETE',
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json',
//           },
//         });

//         if (!response.ok) {
//           throw new Error('Failed to delete product');
//         }

//         // Remove deleted product from the UI
//         setProducts(products.filter((product) => product.id !== id));
//       } catch (err) {
//         setError(err.message);
//       }
//     }
//   };

//   if (loading) {
//     return <p>Loading products...</p>;
//   }

//   if (error) {
//     return <p>{error}</p>;
//   }

//   return (
//     <div>
//       <h2>Product List</h2>
//       {products.length === 0 ? (
//         <p>No products found.</p>
//       ) : (
//         <ul>
//           {products.map((product) => (
//             <li key={product.id}>
//               <h3>{product.name || 'Unnamed Product'}</h3>
//               <p>{product.description || 'No description available'}</p>
//               <p>Price: ${product.price || 'N/A'}</p>
//               <button onClick={() => navigate(`/products/update/${product.id}`)}>Update</button>
//               <button onClick={() => handleDelete(product.id)}>Delete</button>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default ProductList;

























// ProductList.js
// import React, { useState, useEffect } from 'react';

// const ProductList = () => {
//   const [products, setProducts] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState("");

//   useEffect(() => {
//     const fetchProducts = async () => {
//       try {
//         const response = await fetch('http://localhost:9091/api/user/v1/getuserproducts', {
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json',
//           },
//         });

//         if (!response.ok) {
//           throw new Error('Failed to fetch products');
//         }

//         const data = await response.json();
//         setProducts(data); // Assuming data is an array of products
//       } catch (err) {
//         setError(err.message);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchProducts();
//   }, []);

//   if (loading) {
//     return <p>Loading products...</p>;
//   }

//   if (error) {
//     return <p>{error}</p>;
//   }

//   return (
//     <div>
//       <h2>Product List</h2>
//       {products.length === 0 ? (
//         <p>No products found.</p>
//       ) : (
//         <ul>
//           {products.map((product) => (
//             <li key={product.id}>
//               <h3>{product.name || 'Unnamed Product'}</h3>
//               <p>{product.description || 'No description available'}</p>
//               <p>Price: ${product.price || 'N/A'}</p>
//               {product.user_email ? (
//                 <p>Added by: {product.user_email}</p>
//               ) : (
//                 <p>Anonymous</p>
//               )}
//               {product.img_url ? (
//                 <img src={product.img_url} alt={product.name} />
//               ) : (
//                 <p>No image available</p>
//               )}
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default ProductList;

// import React, { useState, useEffect } from 'react';
// import { Link } from 'react-router-dom';

// const ProductList = () => {
//   const [products, setProducts] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState("");

//   useEffect(() => {
//     const fetchProducts = async () => {
//       try {
//         const response = await fetch('http://localhost:9091/api/user/v1/getuserproducts', {
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json',
//           },
//         });

//         if (!response.ok) {
//           throw new Error('Failed to fetch products');
//         }

//         const data = await response.json();
//         setProducts(data); // Assuming data is an array of products
//       } catch (err) {
//         setError(err.message);
//       } finally {
//         setLoading(false);
//       }
//     };

//     fetchProducts();
//   }, []);

//   if (loading) {
//     return <p>Loading products...</p>;
//   }

//   if (error) {
//     return <p>No product</p>;
//   }
//   const handleDelete= async(id)=>{
//     try{
//       const response = await fetch(`http://localhost:9091/api/user/v1/deleteproduct/${id}`, {
//         method: 'DELETE',
//         headers: {
//           'Authorization': `Bearer ${localStorage.getItem('token')}`,
//           'Content-Type': 'application/json',
//         },
     
//     })
//   console.log(response)
//   }
//     catch(err){
//       setError(err.message);
//     }
//   }
//   return (
//     <div>
//       <h2>Product List</h2>
//       {products.length === 0 ? (
//         <p>No products found.</p>
//       ) : (
//         <ul>
//           {products.map((product) => (
//             <li key={product.id}>
//               <h3>{product.name || 'Unnamed Product'}</h3>
//               <p>{product.description || 'No description available'}</p>
//               <p>Price: ${product.price || 'N/A'}</p>
//               {product.user_email ? (
//                 <p>Added by: {product.user_email}</p>
//               ) : (
//                 <p>Anonymous</p>
//               )}
//               <p>dfne</p>
              

//               {/* Add an Edit button */}
//               {/* <Link to={`/update-product/${product.id}`}>
//                 <button>Edit</button>
//                  </Link>

//               {/* Add a Delete button if needed */}
//               {/* <button onClick={() => handleDelete(product.id)}>Delete</button> */}
//               <div className="actions">
//   {/* Edit Button */}
//   <Link to={`/update-product/${product.id}`}>
//     <button className="edit">Edit</button>
//   </Link>

//   {/* Delete Button */}
//   <button className="delete" onClick={() => handleDelete(product.id)}>
//     Delete
//   </button>
// </div>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default ProductList;





























import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('http://localhost:9091/api/user/v1/getuserproducts', {
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch products');
        }

        const data = await response.json();
        setProducts(data); // Assuming data is an array of products
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  if (loading) {
    return <p>Loading products...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  // Handle delete functionality on the frontend
  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this product?")) {
      setProducts(products.filter((product) => product.id !== id));
    }
  };

  return (
    <div>
      <h2>Product List</h2>
      {products.length === 0 ? (
        <p>No products found.</p>
      ) : (
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              <h3>{product.name || 'Unnamed Product'}</h3>
              <p>{product.description || 'No description available'}</p>
              <p>Price: ${product.price || 'N/A'}</p>
              {product.user_email ? (
                <p>Added by: {product.user_email}</p>
              ) : (
                <p>Anonymous</p>
              )}

              <div className="actions">
                {/* Edit Button */}
                <Link to={`/update-product/${product.id}`}>
                  <button className="edit">Edit</button>
                </Link>

                {/* Delete Button */}
                <button className="delete" onClick={() => handleDelete(product.id)}>
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ProductList;







