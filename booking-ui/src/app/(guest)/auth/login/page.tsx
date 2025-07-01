"use client";
import "@/assets/globals.css";
import React, { useState } from "react";
import { postWithHeader } from "@/lib/axiosInstance";

const LoginPage = () => {
  const [form, setForm] = useState({ username: "", password: "" });
  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors: { [key: string]: string } = {};
    if (!form.username) newErrors.username = "Username is required";
    if (!form.password) newErrors.password = "Password is required";
    return newErrors;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const validationErrors = validate();
    setErrors(validationErrors);
    if (Object.keys(validationErrors).length === 0) {
      try {
        const res = await postWithHeader("/api/v1/auth/login", form);
        console.log("[Login Success]", res);
        setSuccess("Đăng nhập thành công!");
      } catch (err: any) {
        console.error("[Login Error]", err);
        if (err.response) {
          setError(err.response?.data?.message || "Đăng nhập thất bại");
        } else if (err.request) {
          setError(
            "Không nhận được phản hồi từ server. Vui lòng kiểm tra kết nối hoặc API."
          );
        } else {
          setError("Lỗi không xác định: " + err.message);
        }
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="w-full max-w-md space-y-8 bg-white rounded-xl shadow-lg p-8">
        <div className="flex flex-col items-center mb-6">
          <span className="text-yellow-500 text-4xl font-bold mb-2">
            Booking
          </span>
          <h1 className="text-2xl font-bold text-gray-900">
            Sign in to your account
          </h1>
        </div>
        <form className="space-y-6" onSubmit={handleSubmit} noValidate>
          <div>
            <label
              htmlFor="username"
              className="block text-sm font-medium text-gray-700"
            >
              Username
            </label>
            <input
              type="text"
              id="username"
              name="username"
              autoComplete="username"
              value={form.username}
              onChange={handleChange}
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.username ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.username && (
              <p className="text-red-500 text-xs mt-1">{errors.username}</p>
            )}
          </div>
          <div>
            <label
              htmlFor="password"
              className="block text-sm font-medium text-gray-700"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              name="password"
              autoComplete="current-password"
              value={form.password}
              onChange={handleChange}
              required
              className={`mt-1 block w-full px-3 py-2 border ${
                errors.password ? "border-red-400" : "border-gray-300"
              } rounded-md shadow-sm focus:outline-none focus:ring-yellow-500 focus:border-yellow-500 sm:text-sm`}
            />
            {errors.password && (
              <p className="text-red-500 text-xs mt-1">{errors.password}</p>
            )}
          </div>
          <div className="flex items-center justify-between">
            <div className="flex items-center">
              <input
                id="remember-me"
                name="remember-me"
                type="checkbox"
                className="h-4 w-4 text-yellow-500 focus:ring-yellow-400 border-gray-300 rounded"
              />
              <label
                htmlFor="remember-me"
                className="ml-2 block text-sm text-gray-600"
              >
                Remember me
              </label>
            </div>
            <div className="text-sm">
              <a
                href="#"
                className="font-medium text-yellow-500 hover:text-yellow-600"
              >
                Forgot your password?
              </a>
            </div>
          </div>
          {error && <div className="text-red-500 text-sm">{error}</div>}
          {success && <div className="text-green-600 text-sm">{success}</div>}
          <button
            disabled={loading}
            type="submit"
            className="w-full bg-yellow-500 text-white font-semibold py-2 px-4 rounded-md hover:bg-yellow-600 transition duration-200 shadow"
          >
            {loading ? "Looding..." : "Sign In"}
          </button>
        </form>
        <div className="text-center text-sm text-gray-500 mt-4">
          Don't have an account?{" "}
          <a
            href="/auth/register"
            className="text-yellow-500 hover:text-yellow-600 font-medium"
          >
            Sign up
          </a>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
