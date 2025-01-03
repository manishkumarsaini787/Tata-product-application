

import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const UpdateProduct = () => {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    description: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { id } = useParams(); // Get the product ID from the URL

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await fetch(`http://localhost:9091/api/user/v1/product/${id}`, {
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch product');
        }

        const data = await response.json();
        setProduct(data); // Set fetched product data
      } catch (err) {
        setError('Error fetching product: ' + err.message);
      }
    };

    fetchProduct();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:9091/api/user/v1/update/product/${id}`, {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(product),
      });

      if (!response.ok) {
        throw new Error('Failed to update product');
      }

      navigate('/products');  // Redirect to product list after updating
    } catch (err) {
      setError('Error updating product: ' + err.message);
    }
  };

  return (
    <div>
      <h2>Update Product</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Product Name</label>
          <input
            type="text"
            name="name"
            value={product.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Price</label>
          <input
            type="number"
            name="price"
            value={product.price}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Description</label>
          <input
            type="text"
            name="description"
            value={product.description}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Update Product</button>
      </form>
    </div>
  );
};

export default UpdateProduct;





















// import React, { useState, useEffect } from 'react';
// import { useNavigate, useParams } from 'react-router-dom';

// const UpdateProduct = () => {
//   const [product, setProduct] = useState({
//     name: '',
//     price: '',
//     description: ''
//   });
//   const [error, setError] = useState('');
//   const navigate = useNavigate();
//   const { id } = useParams(); // Get the product ID from the URL

//   useEffect(() => {
//     const fetchProduct = async () => {
//       try {
//         const response = await fetch(`http://localhost:9091/api/user/v1/product/${id}`, {
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json',
//           },
//         });

//         if (!response.ok) {
//           throw new Error('Failed to fetch product');
//         }

//         const data = await response.json();
//         setProduct(data); // Set fetched product data
//       } catch (err) {
//         setError('Error fetching product: ' + err.message);
//       }
//     };

//     fetchProduct();
//   }, [id]);

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setProduct({ ...product, [name]: value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await fetch(`http://localhost:9091/api/user/v1/update/product/${id}`, {
//         method: 'PUT',
//         headers: {
//           'Authorization': `Bearer ${localStorage.getItem('token')}`,
//           'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(product),
//       });

//       if (!response.ok) {
//         throw new Error('Failed to update product');
//       }

//       navigate('/products');  // Redirect to product list
//     } catch (err) {
//       setError('Error updating product: ' + err.message);
//     }
//   };

//   return (
//     <div>
//       <h2>Update Product</h2>
//       {error && <p style={{ color: 'red' }}>{error}</p>}
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label>Product Name</label>
//           <input
//             type="text"
//             name="name"
//             value={product.name}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Price</label>
//           <input
//             type="number"
//             name="price"
//             value={product.price}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Description</label>
//           <input
//             type="text"
//             name="description"
//             value={product.description}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <button type="submit">Update Product</button>
//       </form>
//     </div>
//   );
// };

// export default UpdateProduct;






// import React, { useState, useEffect } from 'react';
// import { useNavigate, useParams } from 'react-router-dom';

// const UpdateProduct = () => {
//   const [product, setProduct] = useState({
//     name: '',
//     price: '',
//     description: ''
//   });
//   const [error, setError] = useState('');
//   const navigate = useNavigate();
//   const { id } = useParams(); // Get the product ID from the URL

//   // Fetch the product data when the component loads
//   useEffect(() => {
//     const fetchProduct = async () => {
//       try {
//         const response = await fetch(`http://localhost:9091/api/user/v1/product/${id}`, {
//           headers: {
//             'Authorization': `Bearer ${localStorage.getItem('token')}`,
//             'Content-Type': 'application/json',
//           },
//         });

//         if (!response.ok) {
//           throw new Error('Failed to fetch product');
//         }

//         const data = await response.json();
//         setProduct(data); // Set the fetched product data
//       } catch (err) {
//         setError('Error fetching product: ' + err.message);
//       }
//     };

//     fetchProduct();
//   }, [id]); // This will trigger re-fetching if the product ID changes

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setProduct({ ...product, [name]: value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await fetch(`http://localhost:9091/api/user/v1/update/product/${id}`, {
//         method: 'PUT',
//         headers: {
//           'Authorization': `Bearer ${localStorage.getItem('token')}`,
//           'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(product),
//       });

//       if (!response.ok) {
//         throw new Error('Failed to update product');
//       }

//       // If update is successful, navigate back to product list
//       navigate('/products');
//     } catch (err) {
//       setError('Error updating product: ' + err.message);
//     }
//   };

//   return (
//     <div>
//       <h2>Update Product</h2>
//       {error && <p style={{ color: 'red' }}>{error}</p>}
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label>Product Name</label>
//           <input
//             type="text"
//             name="name"
//             value={product.name}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Price</label>
//           <input
//             type="number"
//             name="price"
//             value={product.price}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Description</label>
//           <input
//             type="text"
//             name="description"
//             value={product.description}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <button type="submit">Update Product</button>
//       </form>
//     </div>
//   );
// };

// export default UpdateProduct;





