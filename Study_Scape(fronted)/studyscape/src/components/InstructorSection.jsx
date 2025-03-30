import React, { useState } from "react";
import instructorSarah from "../assets/sarah-johnson.jpg";
import instructorJohn from "../assets/john-smith.jpg";
import instructorBarun from "../assets/instructor3.jpg";
import instructorHod from "../assets/instructor4.jpg";

// ✅ Instructor Data with Additional Fields
const instructors = [
  {
    id: 1,
    name: "Dr. Avishek Choudhury",
    position: "Assistant Professor & HOD",
    institution: "Computer Science Department, New Alipore College",
    bio: "Dr. Avishek Choudhury holds a Ph.D. in Computer Science from IIEST Shibpur, 2023. His research interests include fault-tolerant computer architecture and hardware security. Previously, he worked as a UGC research fellow at the CVPR Unit, ISI Kolkata.",
    email: "avishek.nac.cs@gmail.com",
    phone: "+91-7003504892",
    conference: [
      "Choudhury A., et al. 'Prediction of Shoot Length Using Fuzzy Time Series', INDIACOM, 2008.",
      "Choudhury A., Biplab K. Sikdar. 'Performance Analysis of Fault Tolerance Techniques', VLSI Design, 2017.",
    ],
    image: instructorHod,
  },
  {
    id: 2,
    name: "Mr. J. Pahari",
    position: "Ph.D. in Computer Science",
    institution: "University of California, Berkeley",
    bio: "Mr. J. Pahari is a renowned computer scientist specializing in artificial intelligence and big data. He has published extensively on predictive analytics and machine learning.",
    email: "pahari.cs@berkeley.edu",
    phone: "+1-202-555-0192",
    conference: [
      "Pahari J., et al. 'AI in Predictive Analytics,' IEEE, 2023.",
      "J. Pahari & S. Roy, 'Big Data Analysis Using Deep Learning,' ACM, 2022.",
    ],
    image: instructorSarah,
  },
  {
    id: 3,
    name: "Mr. A. Roy",
    position: "Software Engineer",
    institution: "Google",
    bio: "Mr. A. Roy has vast experience in software development, system architecture, and scalable APIs. He has contributed to multiple open-source projects.",
    email: "a.roy@google.com",
    phone: "+1-415-555-1234",
    conference: [
      "Roy A., et al. 'API Design for Scalability,' TechCon, 2022.",
      "A. Roy, 'Optimizing Microservices Communication,' DevCon, 2021.",
    ],
    image: instructorBarun,
  },
  {
    id: 4,
    name: "Mr. K. Nayak",
    position: "Ph.D. in Economics",
    institution: "Harvard University",
    bio: "Mr. K. Nayak is an expert in global economic trends and has published numerous research papers in the fields of financial modeling and economic forecasting.",
    email: "nayak.economics@harvard.edu",
    phone: "+1-617-555-0193",
    conference: ["Nayak K., 'Economic Growth in Asia,' World Bank Report, 2023."],
    image: instructorJohn,
  },
];

const InstructorsSection = () => {
  // ✅ State to handle modal
  const [selectedInstructor, setSelectedInstructor] = useState(null);

  // ✅ Open Modal
  const openModal = (instructor) => {
    setSelectedInstructor(instructor);
  };

  // ✅ Close Modal
  const closeModal = () => {
    setSelectedInstructor(null);
  };

  return (
    <section
      id="instructors"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      <div className="container mx-auto px-4 text-center relative z-10">
        <h2 className="text-5xl font-bold text-[#ff005c] mb-10 drop-shadow-lg">
          Our Instructors
        </h2>

        {/* ✅ Instructor Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8 mt-10">
          {instructors.map((instructor) => (
            <div
              key={instructor.id}
              className="bg-[#1a0f23] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]"
            >
              <img
                src={instructor.image}
                alt={instructor.name}
                className="w-full h-64 object-contain"
              />
              <div className="p-6">
                <h3 className="text-xl font-semibold text-[#ff005c] mb-2 drop-shadow-lg">
                  {instructor.name}
                </h3>
                <p className="text-gray-300">{instructor.position}</p>
                <p className="text-gray-500 mb-4">{instructor.institution}</p>
                <button
                  onClick={() => openModal(instructor)}
                  className="bg-[#ff005c] hover:bg-[#e6004c] text-white px-4 py-2 rounded-full transition transform hover:-translate-y-1 hover:shadow-[0_0_15px_#ff005c]"
                >
                  View Profile
                </button>
              </div>
            </div>
          ))}
        </div>

        {/* ✅ Modal for Instructor Details */}
        {selectedInstructor && (
          <div className="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
            <div className="bg-[#1a0f23] rounded-lg overflow-hidden w-11/12 md:w-2/3 lg:w-1/2 xl:w-1/3 shadow-lg border border-[#ff005c]/40">
              <div className="p-6 text-center relative">
                {/* Instructor Image */}
                <img
                  src={selectedInstructor.image}
                  alt={selectedInstructor.name}
                  className="w-28 h-28 object-cover rounded-full mx-auto mb-4 border-4 border-[#ff005c]"
                />

                {/* Name and Position */}
                <h3 className="text-3xl font-bold text-[#ff005c] mb-2">
                  {selectedInstructor.name}
                </h3>
                <p className="text-pink-300 text-lg mb-1">
                  {selectedInstructor.position}
                </p>
                <p className="text-gray-400 mb-4">{selectedInstructor.institution}</p>

                {/* 📧 Contact Information */}
                <div className="bg-[#1a0f23] p-4 rounded-lg shadow-inner mb-6 text-left">
                  <p className="text-gray-300 mb-2">
                    📧 <strong>Email:</strong>{" "}
                    <a
                      href={`mailto:${selectedInstructor.email}`}
                      className="text-[#ff8cff] hover:text-[#e2aaff] underline"
                    >
                      {selectedInstructor.email || "N/A"}
                    </a>
                  </p>
                  <p className="text-gray-300">
                    📞 <strong>Phone:</strong>{" "}
                    <a
                      href={`tel:${selectedInstructor.phone}`}
                      className="text-[#ff8cff] hover:text-[#e2aaff] underline"
                    >
                      {selectedInstructor.phone || "N/A"}
                    </a>
                  </p>
                </div>

                {/* 📚 Biography Section */}
                <div className="text-left mb-6">
                  <h4 className="text-xl font-semibold text-[#ff005c] mb-3">
                    Biography
                  </h4>
                  <p className="text-gray-300 leading-relaxed">
                    {selectedInstructor.bio}
                  </p>
                </div>

                {/* 🎓 Conference Proceedings / Publications (Optional) */}
                {selectedInstructor.conference && (
                  <div className="text-left mb-6">
                    <h4 className="text-xl font-semibold text-[#ff005c] mb-3">
                      Conference Proceedings / Publications
                    </h4>
                    <ul className="list-disc list-inside text-gray-300">
                      {selectedInstructor.conference.map((conf, index) => (
                        <li key={index} className="mb-2">
                          {conf}
                        </li>
                      ))}
                    </ul>
                  </div>
                )}

                {/* ❌ Close Button */}
                <button
                  onClick={closeModal}
                  className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-full transition transform hover:-translate-y-1 hover:shadow-[0_0_15px_#ff005c]"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        )}
      </div>

      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl"></div>
    </section>
  );
};

export default InstructorsSection;
