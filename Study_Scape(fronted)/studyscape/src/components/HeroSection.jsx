import React from "react";

const HeroSection = () => {
  return (
    <section className="relative min-h-screen bg-[#0b0212] flex flex-col items-center justify-center text-center pt-20 px-4">
      {/* ✅ Main Card Container */}
      <div className="bg-[#190a20] border border-[#ff005c]/40 shadow-lg rounded-2xl p-10 max-w-3xl w-full text-white transform transition hover:-translate-y-2 hover:shadow-pink-500/40">
        <h1 className="text-5xl font-bold text-[#ff005c] mb-6 drop-shadow-lg">
          The Future of Education is Here
        </h1>
        <p className="mt-3 text-lg text-gray-300 mb-8">
          Uplift College is a revolutionary new way to learn. Our AI-powered,
          personalized courses are designed to make you smarter, faster.
        </p>

        {/* ✅ Centered Search Box */}
        <div className="flex justify-center mt-8">
          <div className="relative max-w-xl w-full">
            <input
              type="text"
              placeholder="Search for a course"
              className="w-full bg-[#1a0f23] text-white py-4 px-6 rounded-full pr-32 border border-[#ff005c]/30 focus:outline-none focus:ring-2 focus:ring-pink-500 placeholder-gray-400"
            />
            <button className="absolute right-0 top-0 h-full bg-[#ff005c] hover:bg-[#e6004c] text-white px-6 rounded-full transition">
              Search
            </button>
          </div>
        </div>
      </div>

      {/* ✅ Decorative Background Circles */}
      <div className="absolute top-20 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-20 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-20 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-20 blur-3xl"></div>
    </section>
  );
};

export default HeroSection;
