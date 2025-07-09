'use client';

import { useState, useEffect, useMemo } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { RefreshCw } from 'lucide-react';
import { UserStatsCards } from './components/user-stats';
import { UsersTable } from './components/users-table';
import { UserFilters } from './components/user-filters';
import { UserForm } from './components/user-form';
import { toast } from '@/hooks/use-toast';
import { Toaster } from '@/components/ui/sonner';
import type { User } from '@/models/users';
import { mockUsers, mockUserStats } from './data/mock-users';

export default function ManageUsersPage() {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [roleFilter, setRoleFilter] = useState('all');
  const [statusFilter, setStatusFilter] = useState('all');
  const [formOpen, setFormOpen] = useState(false);
  const [editingUser, setEditingUser] = useState<User | undefined>();

  // Filter users based on search and filters
  const filteredUsers = useMemo(() => {
    return users.filter((user) => {
      const matchesSearch =
        user.firstName.toLowerCase().includes(searchQuery.toLowerCase()) ||
        user.lastName.toLowerCase().includes(searchQuery.toLowerCase()) ||
        user.email.toLowerCase().includes(searchQuery.toLowerCase()) ||
        user.username.toLowerCase().includes(searchQuery.toLowerCase()) ||
        user.phoneNumber.includes(searchQuery);

      const matchesRole = roleFilter === 'all' || user.roleType === roleFilter;
      const matchesStatus =
        statusFilter === 'all' ||
        (statusFilter === 'active' && user.isActive) ||
        (statusFilter === 'inactive' && !user.isActive);

      return matchesSearch && matchesRole && matchesStatus;
    });
  }, [users, searchQuery, roleFilter, statusFilter]);

  // Load data
  const loadData = async () => {
    setLoading(true);
    try {
      // In production, use real API calls
      // const response = await usersApi.getAll()

      // For demo, use mock data
      await new Promise((resolve) => setTimeout(resolve, 1000)); // Simulate API delay
      setUsers(mockUsers);

      toast({
        title: 'Thành công',
        description: 'Tải dữ liệu thành công',
      });
    } catch (error) {
      toast({
        title: 'Lỗi',
        description: 'Không thể tải dữ liệu',
        variant: 'destructive',
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleEdit = (user: User) => {
    setEditingUser(user);
    setFormOpen(true);
  };

  const handleCreate = () => {
    setEditingUser(undefined);
    setFormOpen(true);
  };

  const handleFormSuccess = () => {
    loadData();
  };

  const handleExport = () => {
    // Implement export functionality
    toast({
      title: 'Thông báo',
      description: 'Tính năng xuất Excel đang được phát triển',
    });
  };

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <div className="border-b">
        <div className="flex h-16 items-center px-4 md:px-6">
          <div className="flex items-center space-x-4">
            <h1 className="text-xl font-semibold">Quản lý Users</h1>
          </div>
          <div className="ml-auto"></div>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 space-y-4 p-4 md:p-6">
        {/* Stats Cards */}
        <UserStatsCards stats={mockUserStats} />

        {/* Users Management */}
        <Card>
          <CardHeader>
            <CardTitle>Danh sách Users</CardTitle>
            <CardDescription>Quản lý tài khoản người dùng trong hệ thống</CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            {/* Filters */}
            <UserFilters
              onSearch={setSearchQuery}
              onRoleFilter={setRoleFilter}
              onStatusFilter={setStatusFilter}
              onExport={handleExport}
              onCreateUser={handleCreate}
            />

            {/* Actions */}
            <div className="flex justify-between items-center">
              <div className="text-sm text-muted-foreground">
                Hiển thị {filteredUsers.length} / {users.length} users
              </div>
              <Button variant="outline" onClick={loadData} disabled={loading}>
                <RefreshCw className={`mr-2 h-4 w-4 ${loading ? 'animate-spin' : ''}`} />
                Làm mới
              </Button>
            </div>

            {/* Table */}
            {loading ? (
              <div className="flex items-center justify-center py-8">
                <RefreshCw className="h-6 w-6 animate-spin" />
                <span className="ml-2">Đang tải dữ liệu...</span>
              </div>
            ) : (
              <UsersTable users={filteredUsers} onEdit={handleEdit} onRefresh={loadData} />
            )}
          </CardContent>
        </Card>
      </div>

      {/* Form Dialog */}
      <UserForm
        open={formOpen}
        onOpenChange={setFormOpen}
        user={editingUser}
        onSuccess={handleFormSuccess}
      />

      <Toaster />
    </div>
  );
}
