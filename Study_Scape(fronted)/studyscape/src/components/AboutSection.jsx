import React from "react";

const AboutSection = () => {
  return (
    <section
      id="about"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-purple-600/50"
    >
      <div className="container mx-auto px-4 relative z-10">
        {/* ✅ Neon Glow Effect on Title */}
        <h2 className="text-5xl font-bold text-center mb-10 text-[#ff005c] drop-shadow-lg hover:text-[#ff005c] transition-colors duration-300">
          About Us
        </h2>

        {/* ✅ Paragraph with Glowing Effect */}
        <p className="max-w-3xl mx-auto text-center text-lg text-gray-300 hover:text-gray-100 transition-colors duration-300">
          Established in 1926, Prabhat Kumar College has been a pioneer in
          providing quality education. Our institution is recognized for its
          academic excellence and has been re-accredited by NAAC.
        </p>
      </div>

      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl hover:opacity-50 transition duration-300"></div>
      <div className="absolute bottom-10 right-20 w-40 h-40 bg-[#7209b7] rounded-full opacity-30 blur-3xl hover:opacity-50 transition duration-300"></div>
      <div className="absolute top-1/3 left-1/3 w-24 h-24 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl hover:opacity-50 transition duration-300"></div>
    </section>
  );
};

export default AboutSection;
