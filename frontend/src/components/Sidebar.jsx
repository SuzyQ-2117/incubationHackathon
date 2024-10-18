import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { UserContext } from '../context/UserContext';

const Sidebar = () => {
  const { isAuthenticated, handleLogout } = useContext(UserContext);

  return (
    <div className="sidebar">
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <hr />
          {/* If logged in, show these options */}
          {isAuthenticated && (
            <>
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
              <li>
                <Link to="/accounts">Accounts</Link>
              </li>
              <hr />
              <li>
                <Link to="/admin">Account Admin</Link>
              </li>
            </>
          )}
          <>
          <li>
            {/* If logged in, show Log Out button; if not, show Log In & Register */}
            {isAuthenticated ? (
              <button
                onClick={handleLogout}
                style={{
                  background: 'none',
                  border: 'none',
                  color: 'black',
                  cursor: 'pointer',
                  padding: 0,
                }}
              >
                Log Out
              </button>
            ) : (
              <Link to="/users/login">Log In & Register</Link>
            )}
          </li>
          </>
        </ul>
      </nav>
    </div>
  );
};

export default Sidebar;
