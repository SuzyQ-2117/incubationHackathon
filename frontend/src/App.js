import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import Transactions from './pages/Transactions';
import Budget from './pages/Budget';
import Goals from './pages/Goals';
import AccountAdmin from './pages/AccountAdmin';
import Login from './pages/Login';
import CurrentAccounts from './pages/CurrentAccounts';
import SavingsAccounts from './pages/SavingsAccounts';
import CreditCards from './pages/CreditCards';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <BrowserRouter>
      <div className="app-layout">
        <Sidebar isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
        <main className="content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/transactions" element={<Transactions />} />
            <Route path="/budget" element={<Budget />} />
            <Route path="/goals" element={<Goals />} />
            <Route path="/current-accounts" element={<CurrentAccounts />} />
            <Route path="/savings-accounts" element={<SavingsAccounts />} />
            <Route path="/credit-cards" element={<CreditCards />} />
            {isLoggedIn && <Route path="/admin" element={<AccountAdmin />} />}
            <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
