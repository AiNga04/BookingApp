'use client';

import { Separator } from '@/components/ui/separator';
import { SidebarTrigger } from '@/components/ui/sidebar';
import Link from 'next/link';
import { usePathname } from 'next/navigation';

function Breadcrumbs() {
  const pathname = usePathname();
  // Bỏ các segment là 'en' hoặc 'vi'
  const segments = pathname.split('/').filter((seg) => seg && seg !== 'en' && seg !== 'vi');
  let path = '';
  return (
    <nav className="flex items-center text-sm text-gray-500 gap-1" aria-label="Breadcrumb">
      <Link href="/" className="hover:underline">
        Home
      </Link>
      {segments.map((seg, idx) => {
        path += '/' + seg;
        const isLast = idx === segments.length - 1;
        return (
          <span key={path} className="flex items-center gap-1">
            <span className="mx-1">/</span>
            {isLast ? (
              <span className="font-semibold text-gray-700">{decodeURIComponent(seg)}</span>
            ) : (
              <Link href={path} className="hover:underline">
                {decodeURIComponent(seg)}
              </Link>
            )}
          </span>
        );
      })}
    </nav>
  );
}

export function SiteHeader() {
  return (
    <header className="flex h-(--header-height) shrink-0 items-center gap-2 border-b transition-[width,height] ease-linear group-has-data-[collapsible=icon]/sidebar-wrapper:h-(--header-height)">
      <div className="flex w-full items-center gap-1 px-4 lg:gap-2 lg:px-6">
        <SidebarTrigger className="-ml-1" />
        <Separator orientation="vertical" className="mx-2 data-[orientation=vertical]:h-4" />
        <Breadcrumbs />
      </div>
    </header>
  );
}
