import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaRegCalendarAlt } from "react-icons/fa";
import { AiOutlineLoading3Quarters } from "react-icons/ai";

const Login = () => {
  const [studentID, setStudentID] = useState("");
  const [dob, setDob] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  // 🎯 Handle Form Submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Basic Validation
    if (!studentID || !dob) {
      setErrorMessage("⚠️ Please enter both Student ID and Date of Birth.");
      return;
    }

    setLoading(true); // Show loader

    try {
      // 🎯 API Call to Backend for Authentication
      const response = await fetch("http://localhost:8080/api/v1/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          studentId: studentID,
          dateOfBirth: dob,
        }),
      });

      const data = await response.json();

      // ✅ Check API Response Status
      if (data.status === 200) {
        // ✅ Successful Login - Store Token and Redirect
        localStorage.setItem("isAuthenticated", "true");
        localStorage.setItem("token", data.data); // Save JWT Token
        localStorage.setItem("studentID", studentID);
        navigate("/dashboard"); // ✅ Redirect to Dashboard
      } else {
        // ❌ Authentication Failed
        setErrorMessage(data.message || "❌ Invalid Student ID or Date of Birth.");
      }
    } catch (error) {
      console.error("Error during login:", error);
      setErrorMessage("❌ Something went wrong. Try again later.");
    } finally {
      setLoading(false); // Hide loader
    }
  };

  return (
    <section
      id="login"
      className="relative min-h-screen flex items-center justify-center bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      {/* 🎨 Background Glow Effects */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl animate-pulse"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl animate-pulse"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl animate-pulse"></div>

      {/* ✅ Login Card */}
      <div
        className="w-full max-w-md flex flex-col items-center bg-gradient-to-br from-[#2b0638] to-[#190a20] p-8 rounded-2xl shadow-2xl border border-[#ff005c]/30 relative z-10
        transition-transform duration-300 hover:scale-105 hover:shadow-[0_0_20px_#ff005c] hover:border-[#ff005c]/50"
      >
        <h1 className="text-[#ff005c] text-5xl font-extrabold mb-3">
          Student Login
        </h1>
        <p className="text-[#e5e5e5] mb-8">Access your student portal securely</p>

        {/* 🚨 Error Message Display */}
        {errorMessage && (
          <div className="mb-4 p-3 bg-red-600/20 text-red-400 rounded w-full border border-red-500/30">
            {errorMessage}
          </div>
        )}

        {/* 📝 Login Form */}
        <form onSubmit={handleSubmit} className="w-full space-y-5">
          {/* Student ID Input */}
          <div>
            <input
              type="text"
              placeholder="Student ID"
              value={studentID}
              onChange={(e) => setStudentID(e.target.value)}
              className="w-full px-4 py-3 bg-[#1a0f23] border border-[#ff005c]/40 rounded text-white placeholder-white/60 focus:outline-none focus:ring-2 focus:ring-[#ff005c]"
            />
          </div>

          {/* Date of Birth Input */}
          <div className="relative w-full">
            <input
              type="date"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
              className="w-full px-4 py-3 bg-[#1a0f23] border border-[#ff005c]/40 rounded text-white focus:outline-none focus:ring-2 focus:ring-[#ff005c] appearance-none"
            />
            <div className="absolute right-4 top-3 text-[#ff005c] pointer-events-none">
              <FaRegCalendarAlt />
            </div>
          </div>

          {/* Submit Button with Loading Spinner */}
          <button
            type="submit"
            className={`w-full flex items-center justify-center gap-2 bg-[#ff005c] text-white py-3 px-4 rounded-lg hover:bg-[#e6004c] transition border border-[#ff005c]/50 
            transform hover:scale-105 hover:shadow-[0_0_15px_#ff005c] ${
              loading ? "cursor-not-allowed opacity-70" : ""
            }`}
            disabled={loading}
          >
            {loading ? (
              <>
                <AiOutlineLoading3Quarters className="animate-spin" />
                Logging in...
              </>
            ) : (
              "Log In"
            )}
          </button>
        </form>

        {/* 🔗 Additional Options */}
        <div className="mt-4 w-full text-center">
          <a
            href="#"
            className="text-[#ff8cff] hover:text-[#e2aaff] transition duration-300 hover:underline"
          >
            Forgot Account?
          </a>
        </div>

        {/* ✅ Create New Account Button */}
        <div className="mt-6 w-full">
          <button
            onClick={() => navigate("/create-account")}
            className="w-full bg-[#ff005c] text-white py-3 px-4 rounded-lg hover:bg-[#e6004c] transition border border-[#ff005c]/50 
            transform hover:scale-105 hover:shadow-[0_0_15px_#ff005c]"
          >
            Create New Account
          </button>
        </div>
      </div>
    </section>
  );
};

export default Login;
