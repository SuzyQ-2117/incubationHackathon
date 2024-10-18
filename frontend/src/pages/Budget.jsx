import React, { useState, useEffect } from 'react';
import TransactionList from '../components/TransactionList';
import { Pie } from 'react-chartjs-2';
import { Chart, ArcElement } from 'chart.js';
import '../CSS/BudgetPage.css';

// Register the ArcElement used for Pie charts
Chart.register(ArcElement);

const Budget = () => {
  const [budget, setBudget] = useState(1500);
  const [transactions, setTransactions] = useState([]);
  const [spent, setSpent] = useState(0);

  useEffect(() => {
    fetch('/data/transactions.json')
      .then((response) => response.json())
      .then((data) => {
        const filteredTransactions = data.filter(
          (transaction) =>
            transaction.transactionType === 'DEB' || transaction.transactionType === 'DD'
        );
        setTransactions(filteredTransactions);

        const totalSpent = filteredTransactions.reduce(
          (total, transaction) => total + transaction.amount,
          0
        );
        setSpent(totalSpent);
      })
      .catch((error) => console.error('Error fetching transactions:', error));
  }, []);

  const handleBudgetChange = (e) => {
    setBudget(e.target.value);
  };

  const data = {
    labels: ['Spent', 'Remaining'],
    datasets: [
      {
        data: [spent, budget - spent],
        backgroundColor: ['#ff6384', '#36a2eb'],
      },
    ],
  };

  return (
    <div className="budget-page">
      <header className="budget-header">
        <h1>Your Budget Overview</h1>
        <div className="budget-input">
          <label>Set your budget: £</label>
          <input
            type="number"
            value={budget}
            onChange={handleBudgetChange}
            min="0"
            step="50"
          />
        </div>
      </header>

      <div className="budget-chart">
        <Pie data={data} />
      </div>

      <section className="transactions-section">
        <h2>Transactions (DD & DEB)</h2>
        <TransactionList transactions={transactions} />
      </section>

      <footer className="budget-footer">
        <p>Total spent: £{spent.toFixed(2)} / Budget: £{budget.toFixed(2)}</p>
        <p>Remaining: £{(budget - spent).toFixed(2)}</p>
      </footer>
    </div>
  );
};

export default Budget;
