import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      // Step 1: Get the CSRF token
      const csrfResponse = await axios.get("http://localhost:8088/csrf", {
        withCredentials: true, // Important to send cookies along
      });
      
      const csrfToken = csrfResponse.data.token || csrfResponse.headers['x-csrf-token']; // Get the token from response data or headers, depending on your setup

      // Step 2: Send the login request with the CSRF token
      const response = await axios.post(
        "http://localhost:8088/users/login",
        new URLSearchParams({
          username,
          password,
          "remember-me": rememberMe,
        }),
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "X-CSRF-TOKEN": csrfToken, // Include the CSRF token here
          },
          withCredentials: true,
        }
      );

      // Check for success status in the response
      if (response.status === 200) {
        console.log("Login successful:", response.data);
        alert("Logged in successfully!");
        navigate("/home"); // Navigate to /home after login
      }
    } catch (error) {
      console.error("Error logging in:", error);
      alert("Invalid credentials or CSRF token issue. Please try again.");
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

      <div style={{ marginTop: "20px" }}>
        <p>Not registered? Click below to sign up!</p>
        <button onClick={navigateToRegistration}>Sign Up</button>
      </div>
    </div>
  );
};

export default Login;
