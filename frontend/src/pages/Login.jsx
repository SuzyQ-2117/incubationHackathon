import React from "react";
import LoginForm from "../components/LoginForm";
import { useNavigate } from "react-router-dom"; // Add this import

const Login = () => {
  const navigate = useNavigate(); // Use the navigate function here

  return (
    <div>
      <h2>Login</h2>
      <LoginForm />

      <div style={{ marginTop: "20px" }}>
        <p>Not registered? Click below to sign up!</p>
        <button onClick={() => navigate("/register")}>Sign Up</button>
      </div>
    </div>
  );
};

export default Login;
