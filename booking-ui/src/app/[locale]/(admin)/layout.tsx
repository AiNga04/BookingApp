import { SidebarProvider } from '@/components/ui/sidebar';
import { AppSidebar } from '@/components/admin/app-sidebar';
import { SiteHeader } from '@/components/admin/site-header';

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <SidebarProvider>
      <AppSidebar />
      <main>
        <SiteHeader />
        {children}
      </main>
    </SidebarProvider>
  );
}
