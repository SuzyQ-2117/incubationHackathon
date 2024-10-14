import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userDetails, setUserDetails] = useState(null);
  const [preferences, setPreferences] = useState({
    marketingEmails: false,
    pushNotifications: false,
    marketingTexts: false,
  });

  const handleLogin = async (username, password) => {
    try {
      // Fetch the JSON data
      const response = await fetch('/data/userData.json');
      const data = await response.json();

      // Check if username and password match
      if (data.username === username && data.password === password) {
        setIsLoggedIn(true);
        setUserDetails({
          title: data.title,
          firstName: data.firstName,
          surname: data.surname,
          preferredName: data.preferredName,
          dob: data.dob,
          address: data.address,
          contactNumber: data.contactNumber,
          identityConfirmed: data.identityConfirmed,
          listOfAccounts: data.listOfAccounts,
        });
        alert('Login successful!');
      } else {
        alert('Invalid username or password.');
      }
    } catch (error) {
      console.error('Error fetching user data:', error);
    }
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUserDetails(null);
  };

  const updatePreferences = (prefs) => {
    setPreferences((prev) => ({ ...prev, ...prefs }));
  };

  return (
    <UserContext.Provider
      value={{
        isLoggedIn,
        userDetails,
        preferences,
        handleLogin,
        handleLogout,
        updatePreferences,
      }}
    >
      {children}
    </UserContext.Provider>
  );
};
