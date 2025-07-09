'use client';

import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form';
import { Dialog, DialogContent, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { Switch } from '@/components/ui/switch';
import { toast } from '@/hooks/use-toast';
import type { User } from '@/models/users';
import { usersApi } from '@/lib/api/users';

const createUserSchema = z
  .object({
    username: z.string().min(3, 'Username phải có ít nhất 3 ký tự'),
    password: z.string().min(6, 'Mật khẩu phải có ít nhất 6 ký tự'),
    rePassword: z.string().min(6, 'Xác nhận mật khẩu phải có ít nhất 6 ký tự'),
    firstName: z.string().min(1, 'Họ là bắt buộc'),
    lastName: z.string().min(1, 'Tên là bắt buộc'),
    email: z.string().email('Email không hợp lệ'),
    phoneNumber: z.string().min(10, 'Số điện thoại phải có ít nhất 10 số'),
    dateOfBirth: z.string().min(1, 'Ngày sinh là bắt buộc'),
    gender: z.boolean(),
    roleType: z.enum(['ADMIN', 'USER', 'MODERATOR']),
  })
  .refine((data) => data.password === data.rePassword, {
    message: 'Mật khẩu xác nhận không khớp',
    path: ['rePassword'],
  });

const updateUserSchema = z.object({
  username: z.string().min(3, 'Username phải có ít nhất 3 ký tự'),
  firstName: z.string().min(1, 'Họ là bắt buộc'),
  lastName: z.string().min(1, 'Tên là bắt buộc'),
  email: z.string().email('Email không hợp lệ'),
  phoneNumber: z.string().min(10, 'Số điện thoại phải có ít nhất 10 số'),
  dateOfBirth: z.string().min(1, 'Ngày sinh là bắt buộc'),
  gender: z.boolean(),
  roleType: z.enum(['ADMIN', 'USER', 'MODERATOR']),
  isActive: z.boolean(),
});

type CreateFormData = z.infer<typeof createUserSchema>;
type UpdateFormData = z.infer<typeof updateUserSchema>;

interface UserFormProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  user?: User;
  onSuccess: () => void;
}

export function UserForm({ open, onOpenChange, user, onSuccess }: UserFormProps) {
  const [loading, setLoading] = useState(false);
  const isEdit = !!user;

  const form = useForm<CreateFormData | UpdateFormData>({
    resolver: zodResolver(isEdit ? updateUserSchema : createUserSchema),
    defaultValues: isEdit
      ? {
          username: '',
          firstName: '',
          lastName: '',
          email: '',
          phoneNumber: '',
          dateOfBirth: '',
          gender: true,
          roleType: 'USER' as const,
          isActive: true,
        }
      : {
          username: '',
          password: '',
          rePassword: '',
          firstName: '',
          lastName: '',
          email: '',
          phoneNumber: '',
          dateOfBirth: '',
          gender: true,
          roleType: 'USER' as const,
        },
  });

  useEffect(() => {
    if (user) {
      form.reset({
        username: user.username,
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        phoneNumber: user.phoneNumber,
        dateOfBirth: user.dateOfBirth,
        gender: user.gender,
        roleType: user.roleType,
        isActive: user.isActive,
      });
    } else {
      form.reset({
        username: '',
        password: '',
        rePassword: '',
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        dateOfBirth: '',
        gender: true,
        roleType: 'USER',
      });
    }
  }, [user, form]);

  const onSubmit = async (data: CreateFormData | UpdateFormData) => {
    setLoading(true);
    try {
      if (isEdit) {
        const response = await usersApi.update({
          id: user.id,
          ...(data as UpdateFormData),
        });
        if (response.success) {
          toast({
            title: 'Thành công',
            description: 'Cập nhật người dùng thành công',
          });
        } else {
          throw new Error(response.message);
        }
      } else {
        const response = await usersApi.create(data as CreateFormData);
        if (response.success) {
          toast({
            title: 'Thành công',
            description: 'Tạo người dùng mới thành công',
          });
        } else {
          throw new Error(response.message);
        }
      }
      onSuccess();
      onOpenChange(false);
    } catch (error) {
      toast({
        title: 'Lỗi',
        description: error instanceof Error ? error.message : 'Có lỗi xảy ra',
        variant: 'destructive',
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>{isEdit ? 'Chỉnh sửa người dùng' : 'Tạo người dùng mới'}</DialogTitle>
        </DialogHeader>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="username"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Username</FormLabel>
                    <FormControl>
                      <Input placeholder="Nhập username" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email</FormLabel>
                    <FormControl>
                      <Input type="email" placeholder="Nhập email" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            {!isEdit && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <FormField
                  control={form.control}
                  name="password"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Mật khẩu</FormLabel>
                      <FormControl>
                        <Input type="password" placeholder="Nhập mật khẩu" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="rePassword"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Xác nhận mật khẩu</FormLabel>
                      <FormControl>
                        <Input type="password" placeholder="Nhập lại mật khẩu" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
              </div>
            )}

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="firstName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Họ</FormLabel>
                    <FormControl>
                      <Input placeholder="Nhập họ" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="lastName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Tên</FormLabel>
                    <FormControl>
                      <Input placeholder="Nhập tên" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="phoneNumber"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Số điện thoại</FormLabel>
                    <FormControl>
                      <Input placeholder="Nhập số điện thoại" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="dateOfBirth"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Ngày sinh</FormLabel>
                    <FormControl>
                      <Input type="date" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <FormField
                control={form.control}
                name="gender"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Giới tính</FormLabel>
                    <Select
                      onValueChange={(value) => field.onChange(value === 'true')}
                      value={field.value.toString()}
                    >
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="Chọn giới tính" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <SelectItem value="true">Nam</SelectItem>
                        <SelectItem value="false">Nữ</SelectItem>
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="roleType"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Vai trò</FormLabel>
                    <Select onValueChange={field.onChange} value={field.value}>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="Chọn vai trò" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <SelectItem value="USER">User</SelectItem>
                        <SelectItem value="MODERATOR">Moderator</SelectItem>
                        <SelectItem value="ADMIN">Admin</SelectItem>
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            {isEdit && (
              <FormField
                control={form.control}
                name="isActive"
                render={({ field }) => (
                  <FormItem className="flex flex-row items-center justify-between rounded-lg border p-4">
                    <div className="space-y-0.5">
                      <FormLabel className="text-base">Trạng thái hoạt động</FormLabel>
                      <div className="text-sm text-muted-foreground">
                        Bật/tắt trạng thái hoạt động của người dùng
                      </div>
                    </div>
                    <FormControl>
                      <Switch checked={field.value} onCheckedChange={field.onChange} />
                    </FormControl>
                  </FormItem>
                )}
              />
            )}

            <div className="flex justify-end space-x-2">
              <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>
                Hủy
              </Button>
              <Button type="submit" disabled={loading}>
                {loading ? 'Đang xử lý...' : isEdit ? 'Cập nhật' : 'Tạo mới'}
              </Button>
            </div>
          </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
}
