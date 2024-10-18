import React, { useState, useEffect } from 'react';
import '../CSS/TransactionsPage.css'; // Optional CSS for styling
import TransactionList from '../components/TransactionList'; // Import the TransactionList component

const TransactionsPage = () => {
  const [transactions, setTransactions] = useState([]);

  // Fetch the transaction data from the JSON file
  useEffect(() => {
    fetch('/data/transactions.json')
      .then((response) => response.json())
      .then((data) => setTransactions(data))
      .catch((error) => console.error('Error fetching transactions:', error));
  }, []);

  return (
    <div className="transactions-page">
      <header className="transactions-header">
      <header className="app-header">
        <h1>PennyPilot</h1>
        <h2> Helping you fly higher with every penny!</h2>
      </header>
        <h4>Transactions for your account:</h4>
        <p>Sort Code: 12-34-56 &nbsp;&nbsp;&nbsp; Account Number: 12345678</p>
      </header>

      {/* Render the TransactionList component, passing in the transactions */}
      <TransactionList transactions={transactions} />
    </div>
  );
};

export default TransactionsPage;
