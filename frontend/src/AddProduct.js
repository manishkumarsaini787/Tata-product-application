// import React, { useState } from "react";

// const AddProduct = () => {
//   const [name, setName] = useState("");
//   const [price, setPrice] = useState("");
//   const [imgUrl, setImgUrl] = useState("");
//   const [message, setMessage] = useState(""); // To show success or error messages

//   const token = localStorage.getItem("token"); // JWT token for authentication

//   // Handle form submission to add the product
//   const handleAddProduct = async (e) => {
//     e.preventDefault();

//     // Basic validation
//     if (!name || !price || !imgUrl) {
//       setMessage("All fields are required.");
//       return;
//     }

//     try {
//       const response = await fetch("http://localhost:8083/api/products/addproduct", {
//         method: "POST",
//         headers: {
//           "Authorization": `Bearer ${token}`, // Attach JWT token for authentication
//           "Content-Type": "application/json", // JSON content type
//         },
//         body: JSON.stringify({
//           name,
//           price,
//           imgUrl,
//         }),
//       });

//       if (response.ok) {
//         setMessage("Product added successfully!");
//         // Optionally reset the form after success
//         setName("");
//         setPrice("");
//         setImgUrl("");
//       } else {
//         setMessage("Failed to add product.");
//       }
//     } catch (error) {
//       console.error("Error adding product:", error);
//       setMessage("Error adding product.");
//     }
//   };

//   return (
//     <div>
//       <h2>Add New Product</h2>
//       <form onSubmit={handleAddProduct}>
//         <div>
//           <label>Product Name</label>
//           <input
//             type="text"
//             value={name}
//             onChange={(e) => setName(e.target.value)}
//             placeholder="Enter product name"
//             required
//           />
//         </div>
//         <div>
//           <label>Price</label>
//           <input
//             type="number"
//             value={price}
//             onChange={(e) => setPrice(e.target.value)}
//             placeholder="Enter product price"
//             required
//           />
//         </div>
//         <div>
//           <label>Image URL</label>
//           <input
//             type="text"
//             value={imgUrl}
//             onChange={(e) => setImgUrl(e.target.value)}
//             placeholder="Enter product image URL"
//             required
//           />
//         </div>
//         <button type="submit">Add Product</button>
//       </form>

//       {message && <p>{message}</p>} {/* Display success/error message */}
//     </div>
//   );
// };

// export default AddProduct;


import React, { useState } from "react";

const AddProduct = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [imgUrl, setImgUrl] = useState("");
  const [message, setMessage] = useState(""); // To show success or error messages

  //const token = localStorage.getItem("token"); // JWT token for authentication

  // Handle form submission to add the product
  const handleAddProduct = async (e) => {
    e.preventDefault(); // Prevent the default form submission

    // Basic validation
    if (!name || !price || !imgUrl) {
      setMessage("All fields are required.");
      return;
    }
    const token = localStorage.getItem("token");  // Make sure token is retrieved correctly

  if (!token) {
    setMessage("Token is missing. Please log in.");
    return;
  }


    try {
      const response = await fetch("http://localhost:8083/api/products/addproduct", {
        method: "POST",
        headers: {
          "Authorization": `Bearer ${token}`, // Attach JWT token for authentication
          "Content-Type": "application/json", // JSON content type
        },
        body: JSON.stringify({
          name,
          price,
          imgUrl,
        }),
      });

      if (response.ok) {
        setMessage("Product added successfully!");
        // Optionally reset the form after success
        setName("");
        setPrice("");
        setImgUrl("");
      } else {
        setMessage("Failed to add product.");
      }
    } catch (error) {
      console.error("Error adding product:", error);
      setMessage("Error adding product.");
    }
  };

  return (
    <div>
      <h2>Add New Product</h2>
      <form onSubmit={handleAddProduct}>
        <div>
          <label>Product Name</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter product name"
            required
          />
        </div>
        <div>
          <label>Price</label>
          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            placeholder="Enter product price"
            required
          />
        </div>
        <div>
          <label>Image URL</label>
          <input
            type="text"
            value={imgUrl}
            onChange={(e) => setImgUrl(e.target.value)}
            placeholder="Enter product image URL"
            required
          />
        </div>
        <button type="submit">Add Product</button>
      </form>

      {message && <p>{message}</p>} {/* Display success/error message */}
    </div>
  );
};

export default AddProduct;

