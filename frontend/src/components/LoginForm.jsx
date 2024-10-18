import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/UserContext"; // Assuming UserContext is set up

const LoginForm = () => {
  const { handleLogin } = useContext(UserContext); // Access the login function from context
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault(); // Prevent form from reloading the page
    await handleLogin(username, password, rememberMe); // Call the login function with form values
    navigate("/dashboard"); // Redirect after login success (adjust the path as needed)
  };

  return (
    <form onSubmit={onSubmit}>
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
