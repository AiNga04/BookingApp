import * as React from "react";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import HeroSection from "@/components/HeroSection";
import "@/assets/globals.css";

export default function Home() {
  return (
    <>
      <Header />
      <main className="max-w-7xl mx-auto sm:px-6 lg:px-8 relative">
        <HeroSection />
      </main>
      <Footer />
    </>
  );
}
