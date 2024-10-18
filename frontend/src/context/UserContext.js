import React, { createContext, useState, useEffect } from 'react';

// Create the context
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [jwt, setJwt] = useState(null); // Stores the JWT token
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Tracks the user's login state
  const [userDetails, setUserDetails] = useState(null); // Stores any user-specific info if needed

  // On first render (or page refresh), check if a token exists in localStorage and set the user as authenticated
  useEffect(() => {
    const storedJwt = localStorage.getItem('jwt');
    if (storedJwt) {
      setJwt(storedJwt);
      setIsAuthenticated(true);
    }
  }, []); // Empty dependency array ensures this runs only once when the component mounts (on page load/refresh)

  // Login function
  const handleLogin = async (username, password) => {
    try {
      const response = await fetch('/api/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        const data = await response.json();
        const { token } = data; // Assuming the API returns a token

        // Save the token to localStorage
        localStorage.setItem('jwt', token);
        setJwt(token);
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
  };

  // Function to check if user is authenticated
  const checkAuth = () => !!jwt;

  return (
    <UserContext.Provider
      value={{
        isAuthenticated,
        jwt,
        handleLogin,
        handleLogout,
        checkAuth,
        userDetails,
        setUserDetails, // Allows you to set user details after login
      }}
    >
      {children}
    </UserContext.Provider>
  );
};
