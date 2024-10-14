import React from 'react';
import Welcome from '../widgets/Welcome';

const Dashboard = () => {
  return (
    <div>
      <div className="intro-container">

        <div className="welcome-widget">
          <Welcome />
        </div>

        <div className="calendar-widget">

        </div>

        <div className="pieChart-widget">

        </div>

        <div className="transactions-widget">

        </div>

        <div className="savings-widget">

        </div>

        <div className="totalSpend-widget">

        </div>

        <div className="topCategories-widget">

        </div>

        <div className="creditCard-widget">

        </div>
      </div>
    </div>
  )

};

export default Dashboard;
