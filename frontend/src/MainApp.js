import React, { useState } from 'react';
import Login from './Login'; // Correct path for Login.js
import Registration from './Registration';
 // Correct path for Register.js

function MainApp() {
  const [showLogin, setShowLogin] = useState(true); // state to toggle between login and registration form

  return (
    <div className="App">
      <h1>Inventory Management</h1>
      {showLogin ? (
        <Login /> // If showLogin is true, render Login component
      ) : (
        <Registration /> // If showLogin is false, render Register component
      )}
      <button onClick={() => setShowLogin(!showLogin)}>
        {showLogin ? 'Go to Register' : 'Go to Login'}
      </button>
    </div>
  );
}

export default MainApp;
