import React from "react";
import { Link } from "react-router-dom";
import { Link as ScrollLink } from "react-scroll";
import logo from "../assets/logo.png";
import studentProfile from "../assets/student.jpg";

const Navbar = () => {
  return (
    <div className="w-full">
      {/* ✅ Notice Bar */}
      <div className="bg-yellow-600 text-black text-center py-2">
        <marquee
          behavior="scroll"
          direction="left"
          scrollamount="5"
          className="font-semibold"
        >
          📢 Latest News: Admissions are open for 2025 batch | 📚 New Courses
          Added | 🎉 Upcoming Events Announced!
        </marquee>
      </div>

      {/* ✅ Navbar Below Notice Bar */}
      <nav className="w-full bg-gradient-to-r from-black via-red-900 to-black p-4 flex justify-between items-center shadow-lg z-50">
        {/* Left Section - Logo & College Info */}
        <div className="flex items-center gap-3">
          <img src={logo} alt="Logo" className="h-12" />
          <a
            href="https://pkcollegecontai.ac.in"
            target="_blank"
            rel="noopener noreferrer"
            className="text-xl font-bold text-white hover:text-red-400 transition"
          >
            Prabhat Kumar College
            <div className="text-white text-sm mt-1">
              <p className="font-semibold">
                Estd: 1926. College with Potential for Excellence (Under UGC XII
                Plan) Re-accredited by NAAC
              </p>
            </div>
          </a>
        </div>

        {/* ✅ Navbar Links */}
        <div className="flex items-center space-x-6 ml-auto text-white">
          <ScrollLink
            to="courses"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            Courses
          </ScrollLink>
          <ScrollLink
            to="about"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            About
          </ScrollLink>
          <ScrollLink
            to="library"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            Library
          </ScrollLink>
          <ScrollLink
            to="admission"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            Admission
          </ScrollLink>
          <ScrollLink
            to="resources"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            Resources
          </ScrollLink>
          <ScrollLink
            to="contact"
            smooth={true}
            duration={600}
            className="hover:text-red-400 cursor-pointer transition"
          >
            Contact
          </ScrollLink>

          {/* ✅ Student Login Button with Card Effect */}
          <Link
            to="/login"
            className="px-4 py-2 bg-red-700 text-white rounded-full hover:bg-red-800 flex items-center space-x-2 transition transform hover:-translate-y-1 hover:shadow-lg shadow-red-500/40 border border-red-500/50"
          >
            <span className="text-lg">Student Login</span>
            <img
              src={studentProfile}
              alt="Student"
              className="h-6 w-6 ml-1 rounded-full border border-gray-300 shadow-md"
            />
          </Link>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
