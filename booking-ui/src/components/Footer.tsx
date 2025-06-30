"use client";

import {
  IoAirplane,
  IoLogoFacebook,
  IoLogoInstagram,
  IoLogoTwitter,
} from "react-icons/io5";

const Footer = () => {
  return (
    <footer className="bg-gray-50 pt-20 pb-10">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-12">
          {/* Company Info */}
          <div className="lg:col-span-2">
            <a
              href="#"
              className="text-2xl font-bold text-yellow-500 flex items-center gap-2 mb-6"
            >
              <IoAirplane className="text-3xl" />
              <span>Booking</span>
            </a>
            <p className="text-gray-600 mb-6">
              Book your trip in minutes, get full control for much longer.
            </p>
            <div className="flex space-x-4">
              <a
                href="#"
                className="w-10 h-10 rounded-full bg-white shadow-md flex items-center justify-center text-yellow-500 hover:bg-yellow-500 hover:text-white transition duration-300"
              >
                <IoLogoFacebook className="text-xl" />
              </a>
              <a
                href="#"
                className="w-10 h-10 rounded-full bg-white shadow-md flex items-center justify-center text-yellow-500 hover:bg-yellow-500 hover:text-white transition duration-300"
              >
                <IoLogoInstagram className="text-xl" />
              </a>
              <a
                href="#"
                className="w-10 h-10 rounded-full bg-white shadow-md flex items-center justify-center text-yellow-500 hover:bg-yellow-500 hover:text-white transition duration-300"
              >
                <IoLogoTwitter className="text-xl" />
              </a>
            </div>
          </div>

          {/* Links */}
          <div>
            <h3 className="font-bold text-lg mb-6">Company</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  About
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Careers
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Mobile
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-6">Contact</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Help/FAQ
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Press
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Partners
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-6">More</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Destinations
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Blog
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  Sustainability
                </a>
              </li>
            </ul>
          </div>
        </div>

        <div className="border-t border-gray-200 mt-16 pt-8 flex flex-col md:flex-row justify-between items-center">
          <p className="text-gray-600 text-sm">
            © 2025 Booking. All rights reserved.
          </p>
          <div className="flex space-x-6 mt-4 md:mt-0">
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              Terms of Service
            </a>
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              Privacy Policy
            </a>
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              Cookie Settings
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
