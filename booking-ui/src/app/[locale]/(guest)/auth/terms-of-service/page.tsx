"use client";
import "@/assets/globals.css";
import React, { useState } from "react";

const terms = [
  {
    title: "Tài khoản và đăng ký",
    content:
      "Bạn phải cung cấp thông tin cá nhân chính xác khi đăng ký tài khoản. Không sử dụng thông tin giả mạo hoặc của người khác.",
  },
  {
    title: "Bảo mật tài khoản",
    content:
      "Bạn chịu trách nhiệm bảo mật thông tin đăng nhập và mọi hoạt động dưới tài khoản của mình.",
  },
  {
    title: "Quyền riêng tư",
    content:
      "Thông tin cá nhân của bạn sẽ được bảo mật và chỉ sử dụng cho mục đích liên quan đến dịch vụ.",
  },
  {
    title: "Chính sách sử dụng",
    content:
      "Không sử dụng tài khoản để thực hiện các hành vi vi phạm pháp luật hoặc gây ảnh hưởng xấu đến hệ thống.",
  },
  {
    title: "Hủy và khóa tài khoản",
    content:
      "Chúng tôi có quyền tạm ngưng hoặc khóa tài khoản nếu phát hiện hành vi vi phạm điều khoản sử dụng.",
  },
];

const TermsAccordion = () => {
  const [openIndex, setOpenIndex] = useState<number | null>(0);

  const toggle = (index: number) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  return (
    <div className="w-full max-w-2xl mx-auto bg-white my-10 px-8 divide-y divide-gray-200">
      {terms.map((term, index) => (
        <div key={index}>
          <button
            onClick={() => toggle(index)}
            className="w-full flex justify-between items-center py-5 px-2 text-left focus:outline-none"
          >
            <div className="flex-1">
              <div className="font-semibold text-gray-900">{term.title}</div>
              {openIndex === index && (
                <div className="mt-2 text-gray-600 font-normal">
                  {term.content}
                </div>
              )}
            </div>
            <span className="text-2xl text-yellow-600 font-bold ml-4 select-none">
              {openIndex === index ? "−" : "+"}
            </span>
          </button>
        </div>
      ))}
    </div>
  );
};

export default TermsAccordion;
