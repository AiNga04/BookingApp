"use client";
import "@/assets/globals.css";
import React, { useState } from "react";
import { useTranslations } from "next-intl";

const RegisterPage = () => {
  const t = useTranslations("register");
  const [form, setForm] = useState({
    username: "",
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    rePassword: "",
  });
  const [errors, setErrors] = useState<{ [key: string]: string }>({});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors: { [key: string]: string } = {};
    if (!form.username) newErrors.username = t("username_required");
    if (!form.email) newErrors.email = t("email_required");
    else if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(form.email))
      newErrors.email = t("email_invalid");
    if (!form.firstName) newErrors.firstName = t("firstName_required");
    if (!form.lastName) newErrors.lastName = t("lastName_required");
    if (!form.password) newErrors.password = t("password_required");
    else if (form.password.length < 6) newErrors.password = t("password_min");
    if (!form.rePassword) newErrors.rePassword = t("rePassword_required");
    else if (form.password !== form.rePassword)
      newErrors.rePassword = t("rePassword_not_match");
    return newErrors;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const validationErrors = validate();
    setErrors(validationErrors);
    if (Object.keys(validationErrors).length === 0) {
      // Submit logic here
      alert("Register success! (Demo only)");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8 bg-white rounded-xl shadow-lg p-8">
        <div className="flex flex-col items-center mb-6">
          <span className="text-yellow-500 text-4xl font-bold mb-2">
            Booking
          </span>
          <h1 className="text-2xl font-bold text-gray-900">{t("title")}</h1>
        </div>
        <form className="space-y-5" onSubmit={handleSubmit} noValidate>
          <div>
            <label
              htmlFor="username"
              className="block text-sm font-medium text-gray-700"
            >
              {t("username")}
            </label>
            <input
              type="text"
              id="username"
              name="username"
              value={form.username}
              onChange={handleChange}
              autoComplete="username"
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.username ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.username && (
              <p className="text-red-500 text-xs mt-1">{errors.username}</p>
            )}
          </div>
          <div className="flex gap-3">
            <div className="w-1/2">
              <label
                htmlFor="firstName"
                className="block text-sm font-medium text-gray-700"
              >
                {t("firstName")}
              </label>
              <input
                type="text"
                id="firstName"
                name="firstName"
                value={form.firstName}
                onChange={handleChange}
                autoComplete="given-name"
                required
                className={`mt-1 block w-full px-3 py-2 border ${
                  errors.firstName ? "border-red-400" : "border-gray-300"
                } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
              />
              {errors.firstName && (
                <p className="text-red-500 text-xs mt-1">{errors.firstName}</p>
              )}
            </div>
            <div className="w-1/2">
              <label
                htmlFor="lastName"
                className="block text-sm font-medium text-gray-700"
              >
                {t("lastName")}
              </label>
              <input
                type="text"
                id="lastName"
                name="lastName"
                value={form.lastName}
                onChange={handleChange}
                autoComplete="family-name"
                required
                className={`mt-1 block w-full px-3 py-2 border ${
                  errors.lastName ? "border-red-400" : "border-gray-300"
                } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
              />
              {errors.lastName && (
                <p className="text-red-500 text-xs mt-1">{errors.lastName}</p>
              )}
            </div>
          </div>
          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium text-gray-700"
            >
              {t("email")}
            </label>
            <input
              type="email"
              id="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              autoComplete="email"
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.email ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.email && (
              <p className="text-red-500 text-xs mt-1">{errors.email}</p>
            )}
          </div>
          <div>
            <label
              htmlFor="password"
              className="block text-sm font-medium text-gray-700"
            >
              {t("password")}
            </label>
            <input
              type="password"
              id="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              autoComplete="new-password"
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.password ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.password && (
              <p className="text-red-500 text-xs mt-1">{errors.password}</p>
            )}
          </div>
          <div>
            <label
              htmlFor="rePassword"
              className="block text-sm font-medium text-gray-700"
            >
              {t("rePassword")}
            </label>
            <input
              type="password"
              id="rePassword"
              name="rePassword"
              value={form.rePassword}
              onChange={handleChange}
              autoComplete="new-password"
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.rePassword ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.rePassword && (
              <p className="text-red-500 text-xs mt-1">{errors.rePassword}</p>
            )}
          </div>
          <button
            type="submit"
            className="w-full bg-yellow-500 text-white font-semibold py-2 px-4 rounded-md hover:bg-yellow-600 transition duration-200 shadow"
          >
            {t("submit")}
          </button>
        </form>
        <div className="text-center text-sm text-gray-500 mt-4">
          {t("already_have_account")}{" "}
          <a
            href="/auth/login"
            className="text-yellow-500 hover:text-yellow-600 font-medium"
          >
            {t("login")}
          </a>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
