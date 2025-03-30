import React from "react";
import SectionTitle from "./SectionTitle"; // ✅ Reuse SectionTitle component

const CourseCards = () => {
  const courses = [
    {
      id: 1,
      title: "BSc Computer Science",
      description: "Complete program in computing fundamentals",
      imageWidth: 400,
      imageHeight: 220,
    },
    {
      id: 2,
      title: "BCA",
      description: "Bachelor's in Computer Applications",
      imageWidth: 400,
      imageHeight: 220,
    },
  ];

  return (
    <section
      id="courses"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      <div className="container mx-auto px-4 text-center relative z-10">
        {/* ✅ Section Title */}
        <SectionTitle title="Our Courses" />

        <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mt-10">
          {courses.map((course) => (
            <div
              key={course.id}
              className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]"
            >
              {/* ✅ Course Image */}
              <div className="h-48 overflow-hidden">
                <img
                  src={`/api/placeholder/${course.imageWidth}/${course.imageHeight}`}
                  alt={`${course.title} course`}
                  className="w-full h-full object-cover transition duration-300 transform hover:scale-105"
                />
              </div>

              {/* ✅ Course Content */}
              <div className="p-6">
                <h3 className="text-2xl font-semibold text-[#ff005c] mb-3 drop-shadow-lg">
                  {course.title}
                </h3>
                <p className="text-gray-300 mb-4">{course.description}</p>

                {/* ✅ Learn More Button */}
                <button className="mt-2 bg-[#ff005c] hover:bg-[#e6004c] text-white py-2 px-4 rounded-full transition border border-[#ff005c]/50 transform hover:-translate-y-1 hover:shadow-[0_0_15px_#ff005c]">
                  Learn More
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl"></div>
    </section>
  );
};

export default CourseCards;
