import React from "react";
import { Link } from "react-router-dom";

const NotFound = () => (
  <div className="h-screen flex flex-col items-center justify-center text-white bg-gray-900">
    <h1 className="text-4xl font-bold">404 - Page Not Found</h1>
    <p className="mt-2 text-gray-400">Oops! The page you're looking for doesn't exist.</p>
    <Link to="/" className="mt-4 px-6 py-3 bg-blue-500 text-white rounded">Go Home</Link>
  </div>
);

export default NotFound;
