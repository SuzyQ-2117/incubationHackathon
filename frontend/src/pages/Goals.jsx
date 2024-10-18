import React, { useState, useEffect } from 'react';
import '../CSS/GoalsPage.css'; // Optional styling for the goals page

const GoalsPage = () => {
  const [goals, setGoals] = useState([]);

  // Fetch the goals from the JSON file
  useEffect(() => {
    fetch('/data/Goals.json')
      .then((response) => response.json())
      .then((data) => setGoals(data))
      .catch((error) => console.error('Error fetching goals:', error));
  }, []);

  // Calculate the percentage of progress towards the goal
  const calculateProgress = (currentBalance, goalAmount) => {
    return Math.min((currentBalance / goalAmount) * 100, 100); // Ensures progress does not exceed 100%
  };

  return (
    <div className="goals-page">
      <header className="app-header">
        <h1>PennyPilot</h1>
        <h2> Helping you fly higher with every penny!</h2>
      </header>
      <h2>Your Savings Goals</h2>
      <div className="goals-container">
        {/* Add New Goal Tile */}
        <div className="goal-tile add-new-goal">
          <h2>Add new goal!</h2>
          <button className="add-goal-button">+</button>
        </div>

        {/* Map through existing goals */}
        {goals.map((goal, index) => (
          <div key={index} className="goal-tile">
            {/* Delete goal button (non-functional) */}
            <button className="delete-goal-button">X</button>
            <h3>{goal.GoalName}</h3>
            <p>
              Saving Amount: £{goal.GoalAmount.toFixed(2)} | Current Balance: £{goal.CurrentBalance.toFixed(2)}
            </p>
            <div className="progress-bar-container">
              <div
                className="progress-bar"
                style={{ width: `${calculateProgress(goal.CurrentBalance, goal.GoalAmount)}%` }}
              ></div>
            </div>
            <p>{calculateProgress(goal.CurrentBalance, goal.GoalAmount).toFixed(2)}% towards goal</p>
            <p>Sort Code: {goal.SortCode} | Account Number: {goal.AccountNumber}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default GoalsPage;