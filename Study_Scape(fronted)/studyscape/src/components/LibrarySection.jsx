import React from "react";

const LibrarySection = () => {
  return (
    <section
      id="library"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-pink-600/50"
    >
      <div className="container mx-auto px-4 text-center relative z-10">
        {/* 📚 Section Title */}
        <h2 className="text-5xl font-bold mb-6 text-[#ff005c] drop-shadow-lg">
          Library
        </h2>
        <p className="text-gray-400 mb-6 max-w-2xl mx-auto leading-relaxed">
          Our library hosts a collection of over{" "}
          <strong className="text-[#ff005c]">50,000 books</strong>, journals,
          and research papers. Students have 24/7 access to e-learning materials
          and digital resources.
        </p>

        {/* 📚 Library Resources Cards */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mt-10">
          {/* 📖 Books Card */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]">
            <h3 className="text-xl font-semibold text-white mb-2">Books</h3>
            <p className="text-gray-300">
              Explore a wide range of academic books across various subjects.
            </p>
          </div>

          {/* 📚 Journals Card */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#3a0ca3]">
            <h3 className="text-xl font-semibold text-white mb-2">Journals</h3>
            <p className="text-gray-300">
              Access the latest journals and publications from renowned
              researchers.
            </p>
          </div>

          {/* 🌐 Digital Resources Card */}
          <div className="bg-[#1a0b1d] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#7209b7]">
            <h3 className="text-xl font-semibold text-white mb-2">Digital Resources</h3>
            <p className="text-gray-300">
              Enjoy 24/7 access to digital content, including e-books and
              research papers.
            </p>
          </div>
        </div>
      </div>

      {/* 🎨 Background Glow Circles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-[#ff005c] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute bottom-20 right-20 w-40 h-40 bg-[#3a0ca3] rounded-full opacity-30 blur-3xl"></div>
      <div className="absolute top-1/3 right-1/4 w-24 h-24 bg-[#7209b7] rounded-full opacity-30 blur-3xl"></div>
    </section>
  );
};

export default LibrarySection;
