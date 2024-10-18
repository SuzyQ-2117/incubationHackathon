// src/components/AccountAdmin.jsx
import React, { useContext, useState } from 'react';
import { UserContext } from '../context/UserContext';
import UserAdminForm from '../components/UserAdminForm';
import ContactPreferences from '../components/ContactPreferences';

const AccountAdmin = () => {
  const { userDetails, isAuthenticated } = useContext(UserContext);
  const [isIdentityConfirmed, setIsIdentityConfirmed] = useState(false);

  const handleConfirmIdentity = () => {
    setIsIdentityConfirmed(true);
  };

  const handleUpdateDetails = () => {
    // Implement the logic to update user details here
    console.log('Update details button clicked');
  };

  const handleUpdatePreferences = () => {
    // Implement the logic to update contact preferences here
    console.log('Update contact preferences button clicked');
  };

  if (!isAuthenticated) {
    return <p>Please log in to access your account.</p>;
  }

  return (
    <div className="account-admin">
      <h2>Account Administration</h2>
      <UserAdminForm
        userDetails={userDetails}
        isIdentityConfirmed={isIdentityConfirmed}
        handleConfirmIdentity={handleConfirmIdentity}
        handleUpdateDetails={handleUpdateDetails}
      />
      <ContactPreferences
        userDetails={userDetails}
        handleUpdatePreferences={handleUpdatePreferences}
      />
    </div>
  );
};

export default AccountAdmin;
