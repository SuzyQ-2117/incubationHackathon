import React, { useEffect, useState, useContext } from "react";
import { UserContext } from "../context/UserContext"; // Assuming UserContext is set up

const UserAdminForm = () => {
  const { jwt } = useContext(UserContext); // Get the JWT from context or localStorage
  const [userDetails, setUserDetails] = useState(null);

  const [title, setTitle] = useState('');
  const [firstName, setFirstName] = useState('');
  const [surname, setSurname] = useState('');
  const [preferredName, setPreferredName] = useState('');
  const [dob, setDob] = useState('');
  const [address, setAddress] = useState('');
  const [contact, setContact] = useState('');
  const [isIdentityConfirmed, setIsIdentityConfirmed] = useState(false);

  useEffect(() => {
    const fetchUserDetails = async () => {
        debugger
      if (jwt) {
        try {
          const response = await fetch('http://localhost:8084/users/me', {
            method: 'GET',
            headers: {
              'Authorization': `Bearer ${jwt}`, // Include the JWT token in the request
              'Content-Type': 'application/json',
            }, 
            credentials: 'include',
          }
        );

          if (response.ok) {
            const data = await response.json();
            setUserDetails(data); // Set user details
            setTitle(data.title || '');
            setFirstName(data.firstName || '');
            setSurname(data.surname || '');
            setPreferredName(data.preferredName || '');
            setDob(data.dob || '');
            setAddress(data.address || '');
            setContact(data.contactNumber || '');
          } else {
            console.error('Error fetching user details:', response.status);
          }
        } catch (error) {
          console.error('Error fetching user details:', error);
        }
      }
    };

    fetchUserDetails();
  }, [jwt]);

  if (!userDetails) {
    return <p>Loading user details...</p>;
  }

  // Handle text changes with validation
  const handleTextChange = (event, setState) => {
    const value = event.target.value;
    if (/^[A-Za-z\s]*$/.test(value)) {
      setState(value);
    }
  };

  // Handle confirming identity
  const handleConfirmIdentity = () => {
    setIsIdentityConfirmed(true);
  };

  // Handle updating user details
  const handleUpdateDetails = () => {
    console.log("Update details button clicked");
    // Add logic to send updated details to the back-end
  };


  return (
    <form className="user-details-form">
      <div className="form-group">
        <label htmlFor="title">Title:</label>
        <select
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          disabled={isIdentityConfirmed}
        >
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
          disabled={isIdentityConfirmed}
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
          disabled={isIdentityConfirmed}
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
          style={{
            backgroundColor: isIdentityConfirmed ? '#ccc' : '#007bff',
            color: '#fff',
          }}
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
  );
};

export default UserAdminForm;
