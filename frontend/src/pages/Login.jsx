import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // useNavigate instead of useHistory
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // useNavigate instead of useHistory

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8088/login", {
        username,
        password,
      });
      console.log("Login successful:", response.data);
      alert("Logged in successfully!");
      navigate("/home"); // Navigate to /home after login
    } catch (error) {
      console.error("Error logging in:", error);
      alert("Invalid credentials. Please try again.");
    }
  };

  const navigateToRegistration = () => {
    navigate("/register");
  };

  return (
    <div>
      <h2>Login</h2>
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

        <button type="submit">Login</button>
      </form>

      <div style={{ marginTop: "20px" }}>
        <p>Not registered? Click below to sign up!</p>
        <button onClick={navigateToRegistration}>Sign Up</button>
      </div>
    </div>
  );
};

export default Login;
