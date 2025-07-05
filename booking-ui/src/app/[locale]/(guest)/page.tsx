import * as React from 'react';
import Header from '@/components/Header';
import HeroSection from '@/components/HeroSection';
import Footer from '@/components/Footer';
import '@/assets/globals.css';

export default function Home() {
  return (
    <>
      <header>
        <Header />
      </header>
      <main className="max-w-7xl mx-auto sm:px-6 lg:px-8 relative">
        <HeroSection />
      </main>
      <footer>
        <Footer />
      </footer>
    </>
  );
}
