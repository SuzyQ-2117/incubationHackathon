import React from 'react';
import { Link } from 'react-router-dom';

const Sidebar = ({ isLoggedIn, handleLogout }) => {
  return (
    <div className="sidebar">
      <nav>
        <h4>Main</h4>
        <Link to="/">Home</Link>
        <Link to="/dashboard">Dashboard</Link>
        <Link to="/transactions">Transactions</Link>
        <Link to="/budget">Budget</Link>
        <Link to="/goals">Goals</Link>
        <hr />
        <h4>Accounts</h4>
        <Link to="/current-accounts">Current Accounts</Link>
        <Link to="/savings-accounts">Savings Accounts</Link>
        <Link to="/credit-cards">Credit Cards</Link>
        <hr />
        {isLoggedIn && <Link to="/admin">Admin</Link>}
        <button onClick={isLoggedIn ? handleLogout : () => alert('Login clicked')}>
          {isLoggedIn ? 'Log Out' : 'Log In'}
        </button>
      </nav>
    </div>
  );
};

export default Sidebar;
