"use client";

import React, { useRef } from "react";
import { IoAirplane, IoMenu } from "react-icons/io5";
import { useRouter } from "next/navigation";

const Header: React.FC = () => {
  const router = useRouter();
  const mobileMenuRef = useRef<HTMLDivElement>(null);

  const handleToggleMenu = () => {
    if (mobileMenuRef.current) {
      mobileMenuRef.current.classList.toggle("hidden");
    }
  };

  const handleClickLogin = () => {
    router.push("/auth/login");
  };

  const handleClickRegister = () => {
    router.push("/auth/register");
  };

  return (
    <header className="sticky top-0 z-50 bg-white/90 backdrop-blur shadow-sm">
      <div className="max-w-7xl mx-auto flex justify-between items-center px-4 py-3 sm:px-6 lg:px-8">
        {/* Logo */}
        <a
          href="#"
          className="text-2xl font-bold text-yellow-500 flex items-center gap-2"
        >
          <IoAirplane className="text-3xl" />
          <span>Booking</span>
        </a>
        {/* Menu */}
        <ul className="hidden lg:flex items-center space-x-8">
          <li>
            <a
              href="#destinations"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              Destinations
            </a>
          </li>
          <li>
            <a
              href="#services"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              Services
            </a>
          </li>
          <li>
            <a
              href="#booking"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              Booking
            </a>
          </li>
          <li>
            <a
              href="#testimonials"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              Testimonials
            </a>
          </li>
        </ul>
        {/* Buttons */}
        <div className="hidden md:flex space-x-4">
          <button
            className="text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-100 transition"
            onClick={handleClickLogin}
          >
            Sign In
          </button>
          <button
            className="bg-yellow-500 text-white px-6 py-2 rounded-lg hover:bg-yellow-600 transition shadow-md"
            onClick={handleClickRegister}
          >
            Sign Up
          </button>
        </div>
        {/* Mobile menu toggle */}
        <button
          className="md:hidden text-gray-700 focus:outline-none"
          onClick={handleToggleMenu}
        >
          <IoMenu className="text-2xl" />
        </button>
      </div>
      {/* Mobile menu */}
      <div
        ref={mobileMenuRef}
        className="hidden flex-col space-y-4 p-4 md:hidden bg-white border-t border-gray-100"
      >
        <a
          href="#destinations"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          Destinations
        </a>
        <a
          href="#services"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          Services
        </a>
        <a
          href="#booking"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          Booking
        </a>
        <a
          href="#testimonials"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          Testimonials
        </a>
        <div className="flex flex-col gap-y-4 pt-2">
          <button
            className="text-gray-700 py-2 rounded-lg hover:bg-gray-100 transition"
            onClick={handleClickLogin}
          >
            Sign In
          </button>
          <button
            className="bg-yellow-500 text-white py-2 rounded-lg hover:bg-yellow-600 transition shadow-md"
            onClick={handleClickRegister}
          >
            Sign Up
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
