import React, { createContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// Create the context
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [jwt, setJwt] = useState(null); // Stores the JWT token
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Tracks the user's login state
  const [userDetails, setUserDetails] = useState(null); // Stores any user-specific info if needed
  const [rememberMe, setRememberMe] = useState(false); // Tracks whether "Remember me" is checked
  const navigate = useNavigate();

  // Function to set the JWT token and rememberMe status
  const setAuthToken = (token, remember) => {
    setJwt(token);
    setRememberMe(remember);
    if (remember) {
      localStorage.setItem('jwt', token); // Store in localStorage if Remember Me is checked
    }
  };

  // On first render (or page refresh), check if a token exists in localStorage and set the user as authenticated
  useEffect(() => {
    const storedJwt = localStorage.getItem('jwt');
    if (storedJwt) {
      setJwt(storedJwt);
      setIsAuthenticated(true);
    } else if (!rememberMe && jwt === null) {
      // If no token and Remember Me was not checked, trigger logout
      handleLogout();
    }
  }, [jwt, rememberMe]);

  const handleLogin = async (username, password, rememberMe) => {
    try {
      const response = await fetch('http://localhost:8084/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const data = await response.json();
        const { jwtToken } = data;
        debugger
        // Use the setAuthToken function to set the token and Remember Me status
        setAuthToken(jwtToken, rememberMe);
        setIsAuthenticated(true);
        alert('Login successful!');
      } else {
        alert('Invalid username or password.');
      }
    } catch (error) {
      console.error('Error logging in:', error);
      alert('An error occurred while logging in.');
    }
  };

  // Logout function
  const handleLogout = () => {
    // Remove the token from localStorage and update state
    localStorage.removeItem('jwt');
    setJwt(null);
    setIsAuthenticated(false);
    setUserDetails(null); // Clear user details if you were storing them

    // Redirect the user to the homepage with an alert
    alert("You've been logged out");
    navigate("/");
  };

  return (
    <UserContext.Provider
      value={{
        isAuthenticated,
        setIsAuthenticated,
        jwt,
        setJwt,
        handleLogin,
        handleLogout,
        userDetails,
        setUserDetails,
        setAuthToken, 
      }}
    >
      {children}
    </UserContext.Provider>
  );
};
