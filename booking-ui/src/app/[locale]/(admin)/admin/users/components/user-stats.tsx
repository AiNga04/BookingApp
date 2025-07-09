import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import type { UserStats } from '@/models/users';
import { Users, UserCheck, UserX, Shield, User, Crown } from 'lucide-react';

interface UserStatsCardsProps {
  stats: UserStats;
}

export function UserStatsCards({ stats }: UserStatsCardsProps) {
  const statsData = [
    {
      title: 'Tổng Users',
      value: stats.total,
      icon: Users,
      color: 'text-blue-600',
    },
    {
      title: 'Đang Hoạt Động',
      value: stats.active,
      icon: UserCheck,
      color: 'text-green-600',
    },
    {
      title: 'Tạm Khóa',
      value: stats.inactive,
      icon: UserX,
      color: 'text-red-600',
    },
    {
      title: 'Admins',
      value: stats.admins,
      icon: Crown,
      color: 'text-purple-600',
    },
    {
      title: 'Moderators',
      value: stats.moderators,
      icon: Shield,
      color: 'text-orange-600',
    },
    {
      title: 'Users',
      value: stats.users,
      icon: User,
      color: 'text-gray-600',
    },
  ];

  return (
    <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-6">
      {statsData.map((stat, index) => (
        <Card key={index}>
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle className="text-sm font-medium">{stat.title}</CardTitle>
            <stat.icon className={`h-4 w-4 ${stat.color}`} />
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold">{stat.value}</div>
          </CardContent>
        </Card>
      ))}
    </div>
  );
}
