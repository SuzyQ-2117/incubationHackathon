import React, { useContext, useState, useEffect } from 'react';
import { UserContext } from '../context/UserContext';

export default function Welcome() {
  const { userDetails, isLoggedIn } = useContext(UserContext);
  const [preferredName, setPreferredName] = useState('');
  const [greeting, setGreeting] = useState('');

  const Greetings = ["Hello", "Howdy", "Welcome back", "Hey", "Hi"];

  function randomGreeting() {
    const randomIndex = Math.floor(Math.random() * Greetings.length);
    return Greetings[randomIndex];
  }

  useEffect(() => {
    if (isLoggedIn && userDetails) {
      setPreferredName(userDetails.preferredName || '');
      setGreeting(randomGreeting());  // Set greeting on component load
    }
  }, [userDetails]);

  useEffect(() => {
    setGreeting(randomGreeting());
  }, [])

  return (
    <div className="welcome-container">
      {isLoggedIn ? (
        <h3>{greeting}, {preferredName}!</h3>
      ) : (
        <h3>{greeting}!</h3>
      )}
    </div>
  );
}