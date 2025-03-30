import React from "react";

const TestimonialsSection = () => {
  // 🎤 Testimonial Data
  const testimonials = [
    {
      id: 1,
      name: "John Doe",
      feedback:
        "The instructors at Uplift College provide hands-on learning and practical knowledge that has given me a competitive edge in my career.",
      course: "CS Graduate",
      image: "https://via.placeholder.com/100",
    },
    {
      id: 2,
      name: "Jane Smith",
      feedback:
        "The course structure and resources provided by the college have been instrumental in shaping my professional journey.",
      course: "BCA Student",
      image: "https://via.placeholder.com/100",
    },
    {
      id: 3,
      name: "Rahul Sharma",
      feedback:
        "The e-learning modules and digital library resources have helped me stay ahead in my studies.",
      course: "MCA Student",
      image: "https://via.placeholder.com/100",
    },
  ];

  return (
    <section
      id="testimonials"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-purple-600/50"
    >
      <div className="container mx-auto px-4 text-center relative z-10">
        {/* 🏆 Section Title */}
        <h2 className="text-5xl font-bold mb-6 text-[#ff005c] drop-shadow-lg">
          What Our Students Say
        </h2>
        <p className="text-gray-400 max-w-2xl mx-auto mb-8 leading-relaxed">
          Hear from our alumni about their experience at Uplift College.
        </p>

        {/* 🎤 Testimonials Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mt-10">
          {testimonials.map((testimonial) => (
            <div
              key={testimonial.id}
              className="bg-[#1a0f23] border border-pink-600 rounded-lg overflow-hidden shadow-lg p-6 text-center transition transform hover:scale-105 duration-300 hover:shadow-[0_0_20px_#ff005c]"
            >
              {/* 🖼️ User Image */}
              <img
                src={testimonial.image}
                alt={testimonial.name}
                className="w-20 h-20 mx-auto rounded-full mb-4 border-2 border-[#ff005c]"
              />
              <p className="text-gray-300 italic mb-4">
                "{testimonial.feedback}"
              </p>
              <h4 className="text-lg font-semibold text-[#ff005c] drop-shadow-lg">
                {testimonial.name}
              </h4>
              <p className="text-gray-400">{testimonial.course}</p>
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

export default TestimonialsSection;
