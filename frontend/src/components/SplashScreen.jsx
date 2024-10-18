import React from 'react';
import '../CSS/SplashScreen.css'; // Optional: Create a CSS file for styling

const SplashScreen = () => {
  return (
    <div className="homepage-container">
      <header className="app-header">
        <h1>PennyPilot</h1>
        <h2> Helping you fly higher with every penny!</h2>
      </header>
      
      <section className="app-info">
        <p>
          Welcome to PennyPilot, the app designed to help you take control of your finances with ease. 
          Whether you're looking to manage your personal savings, track spending, or integrate your banking 
          accounts through open banking, PennyPilot is here to guide you through your financial journey.
        </p>
        <p>
          With PennyPilot, you can:
        </p>
        <ul>
          <li>Securely link your banking accounts</li>
          <li>Track your spending and savings</li>
          <li>Create and manage financial goals</li>
          <li>Add and remove accounts effortlessly</li>
          <li>Monitor your progress and stay on top of your budget</li>
        </ul>
        <p>
          Ready to take flight with your finances? Get started now by creating an account or logging in!
        </p>
      </section>
      
      <footer className="app-footer">
        <p>&copy; 2024 PennyPilot. Give your finances a turbo boost and soar to new heights today.</p>
      </footer>
    </div>
  );
};

export default SplashScreen;
