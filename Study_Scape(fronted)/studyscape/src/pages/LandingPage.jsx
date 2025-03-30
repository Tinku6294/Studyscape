import React from "react";
import Navbar from "../components/Navbar";
import InstructorSection from "../components/InstructorSection";
import CourseSection from "../components/CourseSection";
import AboutSection from "../components/AboutSection";
import LibrarySection from "../components/LibrarySection";
import AdmissionSection from "../components/AdmissionSection";
import ResourcesSection from "../components/ResourcesSection";
import TestimonialsSection from "../components/TestimonialsSection";
import ContactSection from "../components/ContactSection";
import campus from "../assets/campus.webp";
import HeroSection from "../components/HeroSection";

const LandingPage = () => {
  return (
    <div className="text-white">
      {/* ✅ Navbar */}
      <Navbar />

     

      {/* ✅ Sections */}
      <HeroSection/>
      <InstructorSection />
      <CourseSection />
      <AboutSection />
      <LibrarySection />
      <AdmissionSection />
      <ResourcesSection />
      <TestimonialsSection />
      <ContactSection />

      {/* ✅ Footer */}
      <footer className="bg-gray-900 border-t border-gray-800 py-6">
        <div className="container mx-auto px-4 text-center text-gray-500">
          <p>© 2025 Prabhat Kumar College. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
