import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { UserContext } from '../context/UserContext';

const Sidebar = () => {
  const { isLoggedIn, handleLogout } = useContext(UserContext);

  return (
    <div className="sidebar">
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/dashboard">Dashboard</Link>
          </li>
          <li>
            <Link to="/transactions">Transactions</Link>
          </li>
          <li>
            <Link to="/budget">Budget</Link>
          </li>
          <li>
            <Link to="/goals">Goals</Link>
          </li>
        </ul>
        <hr />
        <p>Accounts</p>
        <ul>
          <li>
            <Link to="/current-accounts">Current Accounts</Link>
          </li>
          <li>
            <Link to="/savings-accounts">Savings Accounts</Link>
          </li>
          <li>
            <Link to="/credit-cards">Credit Cards</Link>
          </li>
        </ul>
        <hr />
        <ul>
          {isLoggedIn && (
            <li>
              <Link to="/admin">Admin</Link>
            </li>
          )}
          <li>
            {isLoggedIn ? (
              <button onClick={handleLogout} style={{ background: 'none', border: 'none', color: '#007bff', cursor: 'pointer', padding: 0 }}>
                Log Out
              </button>
            ) : (
              <Link to="/login">Log In & Register</Link>
            )}
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Sidebar;