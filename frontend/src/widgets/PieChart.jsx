import React, { useState, useEffect } from 'react';
import { Chart } from 'react-google-charts';
import transactionsData from './transactions.json';

const SpendingPieChart = () => {
  const [filteredData, setFilteredData] = useState([]);

  useEffect(() => {
    // Define today's date and the date 28 days ago
    const today = new Date();
    const startDate = new Date(today);
    startDate.setDate(today.getDate() - 28);

    // Map merchant codes to categories
    const merchantCategories = {
      123: "Bills",
      124: "Groceries",
      125: "Entertainment"
    };

    // Filter and categorize the transactions within the last 28 days
    const filteredTransactions = transactionsData
      .filter(transaction => {
        const transactionDate = new Date(transaction.timestamp);
        return transactionDate >= startDate && transactionDate <= today;
      })
      .reduce((acc, transaction) => {
        const category = merchantCategories[transaction.merchantCode] || "Other";
        acc[category] = (acc[category] || 0) + transaction.amount;
        return acc;
      }, {});

    // Prepare data for the pie chart
    const chartData = [["Category", "Amount"]];
    for (let [category, amount] of Object.entries(filteredTransactions)) {
      chartData.push([category, amount]);
    }

    setFilteredData(chartData);
  }, []);

  return (
    <div>
      <h2>Spending Breakdown (Last 28 Days)</h2>
      <Chart
        chartType="PieChart"
        data={filteredData}
        options={{
          title: "Spending by Category",
          pieHole: 0.4,
          is3D: false
        }}
        width="100%"
        height="400px"
      />
    </div>
  );
};

export default SpendingPieChart;
