"use client";

import React, { useRef } from "react";
import { IoAirplane, IoMenu } from "react-icons/io5";
import { usePathname, useRouter } from "next/navigation";
import { useLocale } from "next-intl";
import { useTranslations } from "next-intl";

const SUPPORTED_LOCALES = ["en", "vi"];

const Header: React.FC = () => {
  const t = useTranslations("header");

  const router = useRouter();
  const locale = useLocale();
  const pathname = usePathname();
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

  // Only keep the first locale in the path
  const handleSwitchLocale = (newLocale: string) => {
    const segments = pathname.split("/").filter(Boolean);
    // Nếu segment đầu là locale hợp lệ thì thay thế, nếu không thì thêm vào đầu
    if (SUPPORTED_LOCALES.includes(segments[0])) {
      segments[0] = newLocale;
    } else {
      segments.unshift(newLocale);
    }
    // Nếu sau locale lại là 1 locale nữa (bị lặp), thì xóa
    if (SUPPORTED_LOCALES.includes(segments[1])) {
      segments.splice(1, 1);
    }
    router.push("/" + segments.join("/"));
  };

  return (
    <header className="sticky top-0 z-50 bg-white/90 backdrop-blur shadow-sm">
      <div className="max-w-7xl mx-auto flex justify-between items-center px-4 py-3 sm:px-6 lg:px-8">
        {/* Logo */}
        <a
          href="/"
          className="text-2xl font-bold text-yellow-500 flex items-center gap-2"
        >
          <IoAirplane className="text-3xl" />
          <span>Booking</span>
        </a>

        {/* Menu */}
        <ul className="hidden lg:flex items-center space-x-8">
          {/* Language Switcher */}
          <div className="relative flex items-center gap-2 mr-4 group">
            <button
              className="flex items-center gap-1 px-2 py-1 rounded text-sm font-semibold text-gray-700 hover:bg-gray-100 min-w-[110px]"
              tabIndex={0}
            >
              <span className="mr-1">
                {locale === "vi" ? (
                  <span className="inline-block w-5 align-middle">
                    <img
                      src="/flags/vn.svg"
                      alt="vi"
                      className="inline w-5 h-5 mr-1"
                      onError={(e) => {
                        (e.target as HTMLImageElement).src =
                          "https://upload.wikimedia.org/wikipedia/commons/2/21/Flag_of_Vietnam.svg";
                      }}
                    />
                  </span>
                ) : (
                  <span className="inline-block w-5 align-middle">
                    <img
                      src="/flags/gb.svg"
                      alt="en"
                      className="inline w-5 h-5 mr-1"
                      onError={(e) => {
                        (e.target as HTMLImageElement).src =
                          "https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg";
                      }}
                    />
                  </span>
                )}
              </span>
              <span>{locale === "vi" ? "Tiếng Việt" : "English"}</span>
              <svg
                className="w-4 h-4 ml-1"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M19 9l-7 7-7-7"
                />
              </svg>
            </button>

            <div className="absolute left-0 top-full mt-2 bg-white border border-gray-200 rounded shadow-lg min-w-[140px] z-50 hidden group-hover:block group-focus-within:block">
              <button
                onClick={() => handleSwitchLocale("vi")}
                className={`flex items-center w-full px-3 py-2 text-left hover:bg-gray-100 ${
                  locale === "vi" ? "font-bold bg-gray-100" : ""
                }`}
              >
                <img
                  src="/flags/vn.svg"
                  alt="vi"
                  className="inline w-5 h-5 mr-2"
                  onError={(e) => {
                    (e.target as HTMLImageElement).src =
                      "https://upload.wikimedia.org/wikipedia/commons/2/21/Flag_of_Vietnam.svg";
                  }}
                />
                Tiếng Việt
              </button>
              <button
                onClick={() => handleSwitchLocale("en")}
                className={`flex items-center w-full px-3 py-2 text-left hover:bg-gray-100 ${
                  locale === "en" ? "font-bold bg-gray-100" : ""
                }`}
              >
                <img
                  src="/flags/gb.svg"
                  alt="en"
                  className="inline w-5 h-5 mr-2"
                  onError={(e) => {
                    (e.target as HTMLImageElement).src =
                      "https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg";
                  }}
                />
                English
              </button>
            </div>
          </div>

          <li>
            <a
              href="#destinations"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              {t("destinations")}
            </a>
          </li>
          <li>
            <a
              href="#services"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              {t("services")}
            </a>
          </li>
          <li>
            <a
              href="#booking"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              {t("booking")}
            </a>
          </li>
          <li>
            <a
              href="#testimonials"
              className="text-gray-700 hover:text-yellow-500 transition"
            >
              {t("testimonials")}
            </a>
          </li>
        </ul>

        {/* Buttons */}
        <div className="hidden md:flex space-x-4">
          <button
            className="text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-100 transition"
            onClick={handleClickLogin}
          >
            {t("login")}
          </button>
          <button
            className="bg-yellow-500 text-white px-6 py-2 rounded-lg hover:bg-yellow-600 transition shadow-md"
            onClick={handleClickRegister}
          >
            {t("register")}
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
          {t("destinations")}
        </a>
        <a
          href="#services"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          {t("services")}
        </a>
        <a
          href="#booking"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          {t("booking")}
        </a>
        <a
          href="#testimonials"
          className="text-gray-700 block hover:text-yellow-500 transition py-2"
        >
          {t("testimonials")}
        </a>
        <div className="flex flex-col gap-y-4 pt-2">
          <button
            className="text-gray-700 py-2 rounded-lg hover:bg-gray-100 transition"
            onClick={handleClickLogin}
          >
            {t("login")}
          </button>
          <button
            className="bg-yellow-500 text-white py-2 rounded-lg hover:bg-yellow-600 transition shadow-md"
            onClick={handleClickRegister}
          >
            {t("register")}
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
