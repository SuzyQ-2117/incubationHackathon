// src/components/ContactPreferences.jsx
import React, { useState, useEffect } from 'react';

const ContactPreferences = ({ userDetails, handleUpdatePreferences }) => {
  const [marketingEmails, setMarketingEmails] = useState(false);
  const [pushNotifications, setPushNotifications] = useState(false);
  const [marketingTexts, setMarketingTexts] = useState(false);

  // Populate preferences with user details
  useEffect(() => {
    if (userDetails) {
      setMarketingEmails(userDetails.marketingEmail || false);
      setPushNotifications(userDetails.pushNotifications || false);
      setMarketingTexts(userDetails.marketingText || false);
    }
  }, [userDetails]);

  return (
    <div className="preferences">
      <h3>Contact Preferences</h3>
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

      <button
        type="button"
        onClick={handleUpdatePreferences}
        style={{ marginTop: '20px' }}
      >
        Update Contact Preferences
      </button>
    </div>
  );
};

export default ContactPreferences;
