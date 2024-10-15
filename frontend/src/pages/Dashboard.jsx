import React from 'react';
import Welcome from '../widgets/Welcome';

const Dashboard = () => {
  return (
    <div>
      <div className="dashboard-container">

        <div className="welcome-widget widget">
          <Welcome />
        </div>

        <div className="calendar-widget widget">
          <Welcome />
        </div>

        <div className="pieChart-widget widget">
          <Welcome />
        </div>

        <div className="transactions-widget widget">
          <Welcome />
        </div>

        <div className="savings-widget widget">
          <Welcome />
        </div>

        <div className="totalSpend-widget widget">
          <Welcome />
        </div>

        <div className="topCategories-widget widget">
          <Welcome />
        </div>

        <div className="creditCard-widget widget">
          <Welcome />
        </div>
      </div>
    </div>
  )

};

export default Dashboard;
