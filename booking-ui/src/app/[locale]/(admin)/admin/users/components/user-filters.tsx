'use client';

import { useState } from 'react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import { Search, Download, UserPlus } from 'lucide-react';

interface UserFiltersProps {
  onSearch: (query: string) => void;
  onRoleFilter: (role: string) => void;
  onStatusFilter: (status: string) => void;
  onExport: () => void;
  onCreateUser: () => void;
}

export function UserFilters({
  onSearch,
  onRoleFilter,
  onStatusFilter,
  onExport,
  onCreateUser,
}: UserFiltersProps) {
  const [searchQuery, setSearchQuery] = useState('');

  const handleSearch = (value: string) => {
    setSearchQuery(value);
    onSearch(value);
  };

  return (
    <div className="flex flex-col gap-4 md:flex-row md:items-center md:justify-between">
      <div className="flex flex-1 gap-2">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-2 top-2.5 h-4 w-4 text-muted-foreground" />
          <Input
            placeholder="Tìm kiếm theo tên, email, username..."
            value={searchQuery}
            onChange={(e) => handleSearch(e.target.value)}
            className="pl-8"
          />
        </div>
        <Select onValueChange={onRoleFilter}>
          <SelectTrigger className="w-[150px]">
            <SelectValue placeholder="Vai trò" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả</SelectItem>
            <SelectItem value="ADMIN">Admin</SelectItem>
            <SelectItem value="MODERATOR">Moderator</SelectItem>
            <SelectItem value="USER">User</SelectItem>
          </SelectContent>
        </Select>
        <Select onValueChange={onStatusFilter}>
          <SelectTrigger className="w-[150px]">
            <SelectValue placeholder="Trạng thái" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">Tất cả</SelectItem>
            <SelectItem value="active">Hoạt động</SelectItem>
            <SelectItem value="inactive">Tạm khóa</SelectItem>
          </SelectContent>
        </Select>
      </div>
      <div className="flex gap-2">
        <Button variant="outline" onClick={onExport}>
          <Download className="mr-2 h-4 w-4" />
          Xuất Excel
        </Button>
        <Button onClick={onCreateUser}>
          <UserPlus className="mr-2 h-4 w-4" />
          Tạo User Mới
        </Button>
      </div>
    </div>
  );
}
