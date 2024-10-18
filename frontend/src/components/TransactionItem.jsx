// src/components/TransactionItem.jsx
import React from 'react';

const TransactionItem = ({ transaction }) => {
  const formatDate = (timestamp) => {
    const date = new Date(timestamp);
    return date.toLocaleDateString();
  };

  const formatTime = (timestamp) => {
    const date = new Date(timestamp);
    return date.toLocaleTimeString();
  };

  return (
    <tr>
      <td>{formatDate(transaction.timestamp)}</td>
      <td>{formatTime(transaction.timestamp)}</td>
      <td>{transaction.transactionType}</td>
      <td>{`£${transaction.amount.toFixed(2)}`}</td>
      <td>{transaction.merchantCode}</td>
      <td>{/* Placeholder for Category (could be added later) */}</td>
    </tr>
  );
};

export default TransactionItem;
