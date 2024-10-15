import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const UserRegistrationForm = () => {
  const [formData, setFormData] = useState({
    title: "",
    firstName: "",
    surname: "",
    preferredName: "",
    dob: "",
    address: "",
    contactNumber: "",
    username: "",
    password: "",
    identityConfirmed: false,
    marketingEmail: false,
    pushNotifications: false,
    marketingText: false,
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8088/users", formData);
      console.log("User registered successfully:", response.data);
      alert("User registered successfully!");
      setFormData({
        title: "",
        firstName: "",
        surname: "",
        preferredName: "",
        dob: "",
        address: "",
        contactNumber: "",
        username: "",
        password: "",
        identityConfirmed: false,
        marketingEmail: false,
        pushNotifications: false,
        marketingText: false,
      });
      navigate("/login");
    } catch (error) {
      console.error("Error registering user:", error);
      alert("An error occurred while registering the user.");
    }
  };

  const handleBack = () => {
    navigate("/login");
  };

  const handleCancel = () => {
    const confirmCancel = window.confirm("Cancel your registration and return to the home page?");
    if (confirmCancel) {
      navigate("/home");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Title:
        <input
          type="text"
          name="title"
          value={formData.title}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        First Name:
        <input
          type="text"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Surname:
        <input
          type="text"
          name="surname"
          value={formData.surname}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Preferred Name:
        <input
          type="text"
          name="preferredName"
          value={formData.preferredName}
          onChange={handleChange}
        />
      </label>
      <br />

      <label>
        Date of Birth:
        <input
          type="date"
          name="dob"
          value={formData.dob}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Address:
        <input
          type="text"
          name="address"
          value={formData.address}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Contact Number:
        <input
          type="text"
          name="contactNumber"
          value={formData.contactNumber}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Username:
        <input
          type="text"
          name="username"
          value={formData.username}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Password:
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
      </label>
      <br />

      <label>
        Marketing Email:
        <input
          type="checkbox"
          name="marketingEmail"
          checked={formData.marketingEmail}
          onChange={handleChange}
        />
      </label>
      <br />

      <label>
        Push Notifications:
        <input
          type="checkbox"
          name="pushNotifications"
          checked={formData.pushNotifications}
          onChange={handleChange}
        />
      </label>
      <br />

      <label>
        Marketing Text:
        <input
          type="checkbox"
          name="marketingText"
          checked={formData.marketingText}
          onChange={handleChange}
        />
      </label>
      <br />

      <button type="submit">Register</button>
      <button type="button" onClick={handleBack} style={{ marginLeft: "10px" }}>Back</button>
      <button type="button" onClick={handleCancel} style={{ marginLeft: "10px" }}>Cancel</button>
    </form>
  );
};

export default UserRegistrationForm;
