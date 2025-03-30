import React from "react";

const ContactSection = () => {
  return (
    <section
      id="contact"
      className="relative py-20 bg-[#1a0b1d] transition-all duration-300 hover:bg-black hover:shadow-lg hover:shadow-purple-600/50"
    >
      <div className="container mx-auto px-4 relative z-10">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          {/* 📞 Contact Info on Left */}
          <div className="text-left space-y-6">
            {/* 📧 Section Title */}
            <h2 className="text-5xl font-bold text-left mb-6 text-[#ff005c] drop-shadow-lg">
              Contact Us
            </h2>

            {/* 📞 Phone */}
            <p className="text-gray-300 text-lg">
              📞 <strong className="text-[#ff005c]">Phone:</strong>{" "}
              +91-6294715554 | +91-33-2445 2131
            </p>

            {/* ✉️ Email */}
            <p className="text-gray-300 text-lg">
              ✉️ <strong className="text-[#ff005c]">Email:</strong>{" "}
              <a
                href="mailto:info@pkcollegecontai.ac.in"
                className="text-[#ff005c] hover:underline"
              >
                info@pkcollegecontai.ac.in
              </a>
            </p>

            {/* 📍 Address */}
            <p className="text-gray-300 text-lg">
              📍 <strong className="text-[#ff005c]">Address:</strong> No.311, SH
              4 Post Karkuli, Contai, West Bengal 721401
            </p>
          </div>

          {/* 🌐 Google Map (Right) */}
          <div className="overflow-hidden">
            <iframe
              title="Google Map"
              className="w-full h-80 rounded-lg"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d118472.70748554747!2d87.7025328413819!3d21.780074579280!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a03893321e01d55%3A0x7183ae2cf49758f4!2sPrabhat%20Kumar%20College%2C%20Contai!5e0!3m2!1sen!2sin!4v1711722328923!5m2!1sen!2sin"
              allowFullScreen
              loading="lazy"
            />
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

export default ContactSection;
