// src/components/LoginForm.jsx
import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { UserContext } from "../context/UserContext"; // Assuming UserContext is set up

const LoginForm = () => {
  const { setAuthToken, setIsAuthenticated } = useContext(UserContext); // Access context for token and authentication state
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();

  // const handleLogin = async (e) => {
  //   e.preventDefault();
  
  //   try {
  //     const config = {
  //       headers: {
  //         "Content-Type": "application/json", // No need for Access-Control-Allow-Origin here
  //       },
  //     };
  
  //     const response = await axios.post(
  //       "http://localhost:8084/users/login",
  //       { username, password },
  //       config
  //     );
  
  //     if (response.status === 200) {
  //       const { token } = response.data;
  //       localStorage.setItem("jwtToken", token);
  //       setAuthToken(token);
  //       setIsAuthenticated(true);
  //       navigate("/dashboard");
  //     }
  //   } catch (error) {
  //     console.error("Login error:", error);
  //     alert("Invalid login credentials. Please try again.");
  //   }
  // };
  
  //TRY THIS
  const handleLogin = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch('http://localhost:8084/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
        credentials: 'include', // To handle cookies or tokens properly
      });
  
      if (response.ok) {
        const data = await response.json();
        localStorage.setItem('jwtToken', data.token); // Assuming the response contains a token
        setAuthToken(data.token);
        setIsAuthenticated(true);
        navigate('/dashboard');
      } else {
        throw new Error('Login failed');
      }
    } catch (error) {
      console.error('Login error:', error);
      alert('Invalid login credentials. Please try again.');
    }
  };
  

  return (
    <form onSubmit={handleLogin}>
      <label>
        Username:
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </label>
      <br />

      <label>
        Password:
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </label>
      <br />

      <label>
        <input
          type="checkbox"
          checked={rememberMe}
          onChange={(e) => setRememberMe(e.target.checked)}
        />
        Remember me?
      </label>
      <br />

      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;
