"use client";

import { IoArrowForwardOutline, IoPlayOutline } from "react-icons/io5";
import Image from "next/image";
import HeroImage from "@/assets/images/trippng.png";
import { useTranslations } from "next-intl";

const HeroSection = () => {
  const t = useTranslations("hero");

  return (
    <div className="min-h-screen py-16 flex flex-col lg:flex-row items-center gap-12">
      {/* Left Content */}
      <div className="w-full lg:w-1/2 flex flex-col justify-center space-y-8">
        <span className="text-yellow-500 font-semibold text-lg tracking-wide">
          {t("bestDestinations")}
        </span>
        <h1 className="text-4xl sm:text-5xl lg:text-6xl font-bold text-gray-900 leading-tight">
          {t("headline")}
        </h1>
        <p className="text-gray-600 text-base leading-relaxed max-w-lg">
          {t("desc")}
        </p>
        <div className="flex flex-col sm:flex-row items-center gap-6">
          {/* Button 1 */}
          <button className="w-full sm:w-auto px-8 py-4 bg-yellow-500 text-white font-medium rounded-lg shadow-lg hover:bg-yellow-600 transition duration-300 flex items-center justify-center gap-2">
            <span>{t("explore")}</span>
            <IoArrowForwardOutline />
          </button>
          {/* Button 2 */}
          <div className="flex items-center space-x-3 group cursor-pointer">
            <div className="w-12 h-12 flex justify-center items-center text-yellow-500 bg-yellow-100 rounded-full shadow-md group-hover:bg-yellow-500 group-hover:text-white transition duration-300">
              <IoPlayOutline size={20} />
            </div>
            <span className="text-gray-800 font-medium group-hover:text-yellow-500 transition duration-300">
              {t("watchDemo")}
            </span>
          </div>
        </div>
      </div>

      {/* Right Content */}
      <div className="w-full lg:w-1/2 relative">
        <div className="absolute -top-10 -left-10 w-32 h-32 bg-yellow-500/20 rounded-full blur-2xl"></div>
        <div className="absolute -bottom-10 -right-10 w-32 h-32 bg-blue-500/20 rounded-full blur-2xl"></div>
        <Image
          src={HeroImage}
          alt="Travel Image"
          className="w-full h-auto object-cover rounded-2xl relative z-10"
        />
      </div>
    </div>
  );
};

export default HeroSection;
