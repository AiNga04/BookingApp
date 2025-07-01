"use client";

import {
  IoAirplane,
  IoLogoFacebook,
  IoLogoInstagram,
  IoLogoTwitter,
} from "react-icons/io5";
import { useTranslations } from "next-intl";

const Footer = () => {
  const t = useTranslations("footer");

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
              <span>{t("brand")}</span>
            </a>
            <p className="text-gray-600 mb-6">{t("slogan")}</p>
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
            <h3 className="font-bold text-lg mb-6">{t("company")}</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("about")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("careers")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("mobile")}
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-6">{t("contact")}</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("help")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("press")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("partners")}
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-6">{t("more")}</h3>
            <ul className="space-y-4">
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("destinations")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("blog")}
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="text-gray-600 hover:text-yellow-500 transition duration-300"
                >
                  {t("sustainability")}
                </a>
              </li>
            </ul>
          </div>
        </div>

        <div className="border-t border-gray-200 mt-16 pt-8 flex flex-col md:flex-row justify-between items-center">
          <p className="text-gray-600 text-sm">{t("copyright")}</p>
          <div className="flex space-x-6 mt-4 md:mt-0">
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              {t("terms")}
            </a>
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              {t("privacy")}
            </a>
            <a href="#" className="text-gray-600 hover:text-yellow-500 text-sm">
              {t("cookie")}
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
