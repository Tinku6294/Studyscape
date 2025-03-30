import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaRegCalendarAlt } from "react-icons/fa";
import axios from "axios";

const CreateAccount = () => {
  const navigate = useNavigate();

  // 🎯 API Endpoint URL
  const API_URL = "http://localhost:8080/api/v1/students";

  // 📝 Form State
  const [formData, setFormData] = useState({
    studentName: "",
    collegeRoll: "",
    studentId: "",
    courseName: "",
    email: "",
    phoneNumber: "",
    dateOfBirth: "",
    semester: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  // 📝 Handle Input Changes
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // ✅ Form Validation
  const validateForm = () => {
    const {
      studentName,
      collegeRoll,
      studentId,
      courseName,
      email,
      phoneNumber,
      dateOfBirth,
      semester,
    } = formData;

    if (
      !studentName ||
      !collegeRoll ||
      !studentId ||
      !courseName ||
      !email ||
      !phoneNumber ||
      !dateOfBirth ||
      !semester
    ) {
      setErrorMessage("⚠️ All fields are required.");
      return false;
    }

    // ✅ Email Format Check
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
      setErrorMessage("❌ Invalid email format.");
      return false;
    }

    // ✅ Phone Number Check
    const phonePattern = /^[0-9]{10}$/;
    if (!phonePattern.test(phoneNumber)) {
      setErrorMessage("❌ Phone number must be 10 digits.");
      return false;
    }

    return true;
  };

  // 🚀 Handle Form Submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) return;

    setIsLoading(true);
    setErrorMessage("");
    setSuccessMessage("");

    try {
      const response = await axios.post(API_URL, formData);

      if (response.status === 201) {
        setSuccessMessage("🎉 Account created successfully!");
        setTimeout(() => navigate("/login"), 2000); // Redirect to login after 2s
      }
    } catch (error) {
      if (error.response) {
        setErrorMessage(
          error.response.data.message || "❌ Error creating account."
        );
      } else {
        setErrorMessage("❌ Network error. Please try again later.");
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <section
      id="create-account"
      className="relative min-h-screen flex items-center justify-center bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl"></div>

      {/* 🎯 Main Card */}
      <div className="relative w-full max-w-5xl bg-gradient-to-br from-[#2b0638] to-[#190a20] p-10 rounded-2xl shadow-2xl border border-[#ff005c]/30 z-10 transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]">
        <h1 className="text-5xl font-extrabold text-center text-[#ff005c] mb-8">
          Create New Account
        </h1>

        {/* ✅ Error or Success Message */}
        {errorMessage && (
          <div className="mb-4 p-3 bg-red-600/20 text-red-400 rounded w-full border border-red-500/30">
            {errorMessage}
          </div>
        )}
        {successMessage && (
          <div className="mb-4 p-3 bg-green-600/20 text-green-400 rounded w-full border border-green-500/30">
            {successMessage}
          </div>
        )}

        {/* ✅ Form */}
        <form onSubmit={handleSubmit} className="w-full space-y-6 text-white">
          {/* Row 1: Student Name & College Roll */}
          <div className="flex space-x-12">
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Student Name:</label>
              <input
                type="text"
                name="studentName"
                value={formData.studentName}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">College Roll:</label>
              <input
                type="text"
                name="collegeRoll"
                value={formData.collegeRoll}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
          </div>

          {/* Row 2: Student ID & Course Name */}
          <div className="flex space-x-12">
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Student ID:</label>
              <input
                type="text"
                name="studentId"
                value={formData.studentId}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Course Name:</label>
              <input
                type="text"
                name="courseName"
                value={formData.courseName}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
          </div>

          {/* Row 3: Email & Phone Number */}
          <div className="flex space-x-12">
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Email:</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Phone No:</label>
              <input
                type="text"
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              />
            </div>
          </div>

          {/* Row 4: Date of Birth & Semester */}
          <div className="flex space-x-12">
            <div className="flex items-center space-x-3 w-1/2 relative">
              <label className="w-40 text-right">Date of Birth:</label>
              <div className="relative w-full">
                <input
                  type="date"
                  name="dateOfBirth"
                  value={formData.dateOfBirth}
                  onChange={handleChange}
                  className="w-full px-4 py-3 border border-gray-300 rounded text-white bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
                />
                <FaRegCalendarAlt className="absolute right-4 top-4 text-[#ff005c]" />
              </div>
            </div>
            <div className="flex items-center space-x-3 w-1/2">
              <label className="w-40 text-right">Semester:</label>
              <select
                name="semester"
                value={formData.semester}
                onChange={handleChange}
                className="w-full px-4 py-3 border border-gray-300 rounded text-blue-400 bg-transparent focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
              >
                <option value="">Select Semester</option>
                <option value="1">1st Semester</option>
                <option value="2">2nd Semester</option>
                <option value="3">3rd Semester</option>
                <option value="4">4th Semester</option>
                <option value="5">5th Semester</option>
                <option value="6">6th Semester</option>
              </select>
            </div>
          </div>

          {/* Submit Button */}
          <div className="mt-8 text-center">
            <button
              type="submit"
              className="w-1/2 bg-[#ff005c] text-white py-3 px-6 rounded-lg hover:bg-[#e6004c] transition border border-[#ff005c]/50 transform hover:-translate-y-1 hover:shadow-[0_0_15px_#ff005c]"
              disabled={isLoading}
            >
              {isLoading ? "Creating Account..." : "Create Account"}
            </button>
          </div>
        </form>
      </div>
    </section>
  );
};

export default CreateAccount;
