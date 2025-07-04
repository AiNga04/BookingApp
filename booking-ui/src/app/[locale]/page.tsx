import * as React from 'react';
import HeroSection from '@/components/HeroSection';
import '@/assets/globals.css';

export default function Home() {
  return (
    <>
      <main className="max-w-7xl mx-auto sm:px-6 lg:px-8 relative">
        <HeroSection />
      </main>
    </>
  );
}
