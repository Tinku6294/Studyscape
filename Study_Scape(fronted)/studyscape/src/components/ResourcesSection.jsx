import React from "react";

const ResourcesSection = () => {
  return (
    <section
      id="resources"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      <div className="container mx-auto px-4 text-center relative z-10">
        {/* 📚 Section Title */}
        <h2 className="text-5xl font-bold mb-6 text-[#ff005c] drop-shadow-lg">
          Resources
        </h2>
        <p className="text-gray-400 max-w-2xl mx-auto mb-8 leading-relaxed">
          We provide a variety of resources including{" "}
          <strong className="text-[#ff005c]">e-learning modules</strong>,
          research papers, recorded lectures, and online tutorials.
        </p>

        {/* 📄 Resources List */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mt-10">
          {/* ✅ E-learning Modules */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]">
            <h3 className="text-2xl font-semibold text-white mb-3">
              🎓 E-learning Modules
            </h3>
            <p className="text-gray-400">Interactive learning modules covering various subjects.</p>
          </div>

          {/* ✅ Research Papers */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]">
            <h3 className="text-2xl font-semibold text-white mb-3">
              📚 Research Papers
            </h3>
            <p className="text-gray-400">Access to numerous high-quality research papers.</p>
          </div>

          {/* ✅ Recorded Lectures */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]">
            <h3 className="text-2xl font-semibold text-white mb-3">
              🎥 Recorded Lectures
            </h3>
            <p className="text-gray-400">Recorded sessions by expert instructors.</p>
          </div>
        </div>

        {/* 📢 View More Button */}
        <div className="mt-8">
          <a
            href="/resources"
            className="bg-[#ff005c] hover:bg-[#e6004c] text-white px-6 py-3 rounded-full transition transform hover:-translate-y-2 shadow-lg hover:shadow-[0_0_20px_#ff005c]"
          >
            Explore More
          </a>
        </div>
      </div>

      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl"></div>
    </section>
  );
};

export default ResourcesSection;
