'use client';

import {
  UserCircle,
  LayoutDashboard,
  Hotel,
  BedDouble,
  Users,
  LogOut,
  Settings,
  Bell,
  CreditCard,
} from 'lucide-react';
import { IoAirplane } from 'react-icons/io5';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarHeader,
} from '@/components/ui/sidebar';
import { useState } from 'react';

const adminLinks = [
  { label: 'Dashboard', href: '/admin', icon: LayoutDashboard },
  { label: 'Quản lý Khách sạn', href: '/admin/hotels', icon: Hotel },
  { label: 'Quản lý Phòng', href: '/admin/rooms', icon: BedDouble },
  { label: 'Quản lý Người dùng', href: '/admin/users', icon: Users },
];

export function AppSidebar() {
  const pathname = usePathname();
  const [open, setOpen] = useState(false);
  return (
    <div className="bg-[#fffbe6] h-full">
      <Sidebar collapsible="offcanvas">
        <SidebarHeader>
          <a
            href="/admin"
            className="text-2xl font-bold text-yellow-500 flex items-center gap-2 p-2"
          >
            <IoAirplane className="text-3xl" />
            {<span>Booking Admin</span>}
          </a>
        </SidebarHeader>
        <SidebarContent>
          <SidebarGroup>
            {adminLinks.map((item) => {
              const Icon = item.icon;
              return (
                <Link
                  key={item.href}
                  href={item.href}
                  className={`flex items-center gap-2 px-4 py-2 rounded hover:bg-orange-100 transition-colors ${
                    pathname === item.href ? 'bg-orange-200 font-semibold' : ''
                  }`}
                >
                  <Icon className="w-5 h-5" />
                  {item.label}
                </Link>
              );
            })}
          </SidebarGroup>
        </SidebarContent>
        <SidebarFooter>
          <div className="relative">
            <button
              className="flex items-center gap-2 w-full px-4 py-2 rounded hover:bg-gray-100 transition-colors"
              onClick={() => setOpen((v) => !v)}
            >
              <UserCircle className="w-7 h-7 text-gray-500" />
              <div className="flex-1 text-left">
                <div className="font-semibold leading-tight">admin</div>
                <div className="text-xs text-gray-500">admin@admin.com</div>
              </div>
              <svg
                className={`w-4 h-4 ml-auto transition-transform ${open ? 'rotate-180' : ''}`}
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                viewBox="0 0 24 24"
              >
                <path strokeLinecap="round" strokeLinejoin="round" d="M19 9l-7 7-7-7" />
              </svg>
            </button>
            {open && (
              <div className="absolute left-0 bottom-12 w-60 bg-white rounded-xl shadow-xl border z-50 animate-fade-in">
                <div className="flex items-center gap-3 px-4 py-3 border-b">
                  <UserCircle className="w-10 h-10 text-gray-500" />
                  <div>
                    <div className="font-semibold">admin</div>
                    <div className="text-xs text-gray-500">admin@admin.com</div>
                  </div>
                </div>
                <ul className="py-1">
                  <li>
                    <button className="flex items-center gap-2 w-full px-4 py-2 hover:bg-gray-50">
                      <Settings className="w-4 h-4" />
                      Account
                    </button>
                  </li>
                  <li>
                    <button className="flex items-center gap-2 w-full px-4 py-2 hover:bg-gray-50">
                      <CreditCard className="w-4 h-4" />
                      Billing
                    </button>
                  </li>
                  <li>
                    <button className="flex items-center gap-2 w-full px-4 py-2 hover:bg-gray-50">
                      <Bell className="w-4 h-4" />
                      Notifications
                    </button>
                  </li>
                </ul>
                <button
                  className="flex items-center gap-2 w-full px-4 py-2 border-t text-red-600 hover:bg-red-50"
                  onClick={() => alert('Đăng xuất!')}
                >
                  <LogOut className="w-4 h-4" />
                  Log out
                </button>
              </div>
            )}
          </div>
        </SidebarFooter>
      </Sidebar>
    </div>
  );
}
