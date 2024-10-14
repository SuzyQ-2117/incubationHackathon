import React, { useState } from 'react';

const AccountAdmin = () => {
  // User Information States
  const [title, setTitle] = useState('');
  const [firstName, setFirstName] = useState('');
  const [surname, setSurname] = useState('');
  const [preferredName, setPreferredName] = useState('');
  const [dob, setDob] = useState('');
  const [address, setAddress] = useState('');
  const [contact, setContact] = useState('');
  
  // Contact Preferences States
  const [marketingEmails, setMarketingEmails] = useState(false);
  const [pushNotifications, setPushNotifications] = useState(false);
  const [marketingTexts, setMarketingTexts] = useState(false);

  // Identity Confirmation State
  const [isIdentityConfirmed, setIsIdentityConfirmed] = useState(false);

  const handleTextChange = (event, setState) => {
    // Only allows letters and spaces
    const value = event.target.value;
    if (/^[A-Za-z\s]*$/.test(value)) {
      setState(value);
    }
  };

  const handleConfirmIdentity = () => {
    setIsIdentityConfirmed(true);
  };

  const handleUpdateDetails = () => {
    console.log("Update details button clicked");
  };

  const handleUpdatePreferences = () => {
    console.log("Update contact preferences button clicked");
  };

  return (
    <div className="account-admin">
      <h2>Account Administration</h2>
      
      <form className="user-details-form">
        <div className="form-group">
        <label htmlFor="title">Title:</label>
          <select id="title" value={title} onChange={(e) => setTitle(e.target.value)}>
            <option value="">Select...</option>
            <option value="Mr">Mr</option>
            <option value="Mrs">Mrs</option>
            <option value="Ms">Ms</option>
            <option value="Dr">Dr</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="firstName">First Name(s):</label>
          <input
            type="text"
            id="firstName"
            value={firstName}
            onChange={(e) => handleTextChange(e, setFirstName)}
            placeholder="Enter first name(s)"
          />
        </div>

        <div className="form-group">
          <label htmlFor="surname">Surname:</label>
          <input
            type="text"
            id="surname"
            value={surname}
            onChange={(e) => handleTextChange(e, setSurname)}
            placeholder="Enter surname"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="preferredName">Preferred Name:</label>
          <input
            type="text"
            id="preferredName"
            value={preferredName}
            onChange={(e) => setPreferredName(e.target.value)}
            placeholder="Optional"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="dob">Date of Birth:</label>
          <input
            type="date"
            id="dob"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
            readOnly={isIdentityConfirmed}
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="address">Address:</label>
          <input
            type="text"
            id="address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="contact">Contact Number:</label>
          <input
            type="text"
            id="contact"
            value={contact}
            onChange={(e) => setContact(e.target.value)}
          />
        </div>
        
        <div className="form-group">
          <button
            type="button"
            onClick={handleConfirmIdentity}
            disabled={isIdentityConfirmed}
            style={{ backgroundColor: isIdentityConfirmed ? '#ccc' : '#007bff', color: '#fff' }}
          >
            Confirm Identity
          </button>
        </div>

        <div className="form-group">
          <button type="button" onClick={handleUpdateDetails}>
            Update Details
          </button>
        </div>
      </form>
      
      <h3>Contact Preferences</h3>
      <div className="preferences">
        <div>
          <input
            type="checkbox"
            id="marketingEmails"
            checked={marketingEmails}
            onChange={() => setMarketingEmails(!marketingEmails)}
          />
          <label htmlFor="marketingEmails">Marketing Emails</label>
        </div>
        
        <div>
          <input
            type="checkbox"
            id="pushNotifications"
            checked={pushNotifications}
            onChange={() => setPushNotifications(!pushNotifications)}
          />
          <label htmlFor="pushNotifications">Push Notifications</label>
        </div>
        
        <div>
          <input
            type="checkbox"
            id="marketingTexts"
            checked={marketingTexts}
            onChange={() => setMarketingTexts(!marketingTexts)}
          />
          <label htmlFor="marketingTexts">Marketing Texts</label>
        </div>

        <button type="button" onClick={handleUpdatePreferences} style={{ marginTop: '20px' }}>
        Update Contact Preferences
      </button>
      
      </div>
    </div>
  );
};

export default AccountAdmin;