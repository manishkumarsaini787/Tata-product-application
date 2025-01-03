import React, { useState } from "react";

const Registration = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [successMessage, setSuccessMessage] = useState(""); // To display success message
  const [errorMessage, setErrorMessage] = useState(""); // To display error message

  const handleRegister = async (e) => {
    e.preventDefault();

    const requestData = {
      username,
      password,
      role,
    };

    try {
      const response = await fetch("http://localhost:8083/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      });

      if (response.ok) {
        // If registration is successful, clear error and show success message
        setSuccessMessage("Registration successful! Please log in.");
        setErrorMessage(""); // Clear any previous error
      } else {
        // Handle registration failure
        const errorData = await response.json();
        setErrorMessage(errorData.message || "Registration failed. Please try again.");
        setSuccessMessage(""); // Clear any previous success message
      }
    } catch (error) {
      console.error("Error during registration:", error);
      setErrorMessage("An error occurred. Please try again.");
      setSuccessMessage(""); // Clear any previous success message
    }
  };

  return (
    <div>
      <h2>Register</h2>

      {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

      <form onSubmit={handleRegister}>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
          required
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
          required
        />
        <input
          type="text"
          value={role}
          onChange={(e) => setRole(e.target.value)}
          placeholder="Role (e.g., user/admin)"
          required
        />
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Registration;
