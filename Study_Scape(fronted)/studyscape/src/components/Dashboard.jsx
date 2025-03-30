import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  // Check if user is authenticated
  useEffect(() => {
    const isAuthenticated = localStorage.getItem("isAuthenticated");
    if (!isAuthenticated) {
      navigate("/"); // Redirect to login if not authenticated
    }
  }, [navigate]);

  // Sample user data
  const user = {
    name: "Student Name",
    email: "student@example.com",
    courses: [
      { id: 1, name: "Mathematics", progress: 65 },
      { id: 2, name: "Computer Science", progress: 80 },
      { id: 3, name: "History", progress: 40 },
    ],
    assignments: [
      { id: 1, title: "Calculus Problem Set", dueDate: "2025-03-25", course: "Mathematics" },
      { id: 2, title: "Programming Project", dueDate: "2025-03-30", course: "Computer Science" },
      { id: 3, title: "Research Essay", dueDate: "2025-04-05", course: "History" },
    ],
  };

  // Sample teacher and student data
  const teachers = [
    { id: 1, name: "Dr. Smith", subject: "Mathematics" },
    { id: 2, name: "Prof. Johnson", subject: "Computer Science" },
  ];

  const students = [
    { id: 1, name: "Alice Brown", grade: "A" },
    { id: 2, name: "John Doe", grade: "B+" },
  ];

  // Logout handler
  const handleLogout = () => {
    localStorage.removeItem("isAuthenticated");
    localStorage.removeItem("studentID");
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white">
      {/* Header */}
      <header className="backdrop-blur-lg bg-white/10 shadow-lg">
        <div className="max-w-7xl mx-auto px-4 py-4 sm:px-6 flex justify-between items-center">
          <h1 className="text-3xl font-bold text-blue-400">Student Details</h1>
          <div className="flex items-center space-x-4">
            <span className="text-gray-300">Welcome, {user.name}</span>
            <button
              onClick={handleLogout}
              className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
            >
              Logout
            </button>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 py-6 sm:px-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {/* Course Progress */}
        <div className="glassmorphic p-6 rounded-lg shadow-lg">
          <h2 className="text-xl font-semibold mb-4">My Courses</h2>
          <div className="space-y-4">
            {user.courses.map((course) => (
              <div key={course.id} className="space-y-2">
                <div className="flex justify-between">
                  <span className="font-medium">{course.name}</span>
                  <span>{course.progress}%</span>
                </div>
                <div className="w-full bg-gray-700 rounded-full h-2.5">
                  <div
                    className="bg-blue-500 h-2.5 rounded-full"
                    style={{ width: `${course.progress}%` }}
                  ></div>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Upcoming Assignments */}
        <div className="glassmorphic p-6 rounded-lg shadow-lg">
          <h2 className="text-xl font-semibold mb-4">Upcoming Assignments</h2>
          <div className="space-y-4">
            {user.assignments.map((assignment) => (
              <div key={assignment.id} className="border-b border-gray-700 pb-3 last:border-b-0">
                <div className="font-medium">{assignment.title}</div>
                <div className="flex justify-between text-sm text-gray-400">
                  <span>{assignment.course}</span>
                  <span>Due: {assignment.dueDate}</span>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Teacher Details */}
        <div className="glassmorphic p-6 rounded-lg shadow-lg">
          <h2 className="text-xl font-semibold mb-4">Teacher Details</h2>
          <ul className="space-y-4">
            {teachers.map((teacher) => (
              <li key={teacher.id} className="border-b border-gray-700 pb-2 last:border-b-0">
                <div className="font-medium">{teacher.name}</div>
                <div className="text-sm text-gray-400">Subject: {teacher.subject}</div>
              </li>
            ))}
          </ul>
        </div>

        {/* Student Details */}
        <div className="glassmorphic p-6 rounded-lg shadow-lg">
          <h2 className="text-xl font-semibold mb-4">Student Details</h2>
          <ul className="space-y-4">
            {students.map((student) => (
              <li key={student.id} className="border-b border-gray-700 pb-2 last:border-b-0">
                <div className="font-medium">{student.name}</div>
                <div className="text-sm text-gray-400">Grade: {student.grade}</div>
              </li>
            ))}
          </ul>
        </div>
      </main>
    </div>
  );
};

export default Dashboard;