// src/components/TransactionList.jsx
import React from 'react';
import TransactionItem from './TransactionItem';

const TransactionList = ({ transactions }) => {
  return (
    <table className="transactions-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Time</th>
          <th>Type</th>
          <th>Amount</th>
          <th>Merchant Code</th>
          <th>Category</th>
        </tr>
      </thead>
      <tbody>
        {transactions.map((transaction) => (
          <TransactionItem key={transaction.transactionId} transaction={transaction} />
        ))}
      </tbody>
    </table>
  );
};

export default TransactionList;
