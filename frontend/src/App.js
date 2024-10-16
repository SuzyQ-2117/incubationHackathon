import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { UserProvider } from './context/UserContext';
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
import RegistrationsPage from './pages/RegistrationPage';

function App() {
  return (
    <UserProvider>
      <Router>
        <div className="app-layout">
          <Sidebar />
          <main className="content">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/users/login" element={<Login />} />
              <Route path="/register" element={<RegistrationsPage />} />
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/transactions" element={<Transactions />} />
              <Route path="/budget" element={<Budget />} />
              <Route path="/goals" element={<Goals />} />
              <Route path="/current-accounts" element={<CurrentAccounts />} />
              <Route path="/savings-accounts" element={<SavingsAccounts />} />
              <Route path="/credit-cards" element={<CreditCards />} />
              <Route path="/admin" element={<AccountAdmin />} />
            </Routes>
          </main>
        </div>
      </Router>
    </UserProvider>
  );
}

export default App;