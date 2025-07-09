export interface User {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  dateOfBirth: string;
  gender: boolean;
  roleType: 'ADMIN' | 'USER' | 'MODERATOR';
  isActive: boolean;
  avatar?: string;
  createdAt: string;
  updatedAt: string;
}

export interface CreateUserRequest {
  username: string;
  password: string;
  rePassword: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  dateOfBirth: string;
  gender: boolean;
  roleType: 'ADMIN' | 'USER' | 'MODERATOR';
}

export interface UpdateUserRequest {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  dateOfBirth: string;
  gender: boolean;
  roleType: 'ADMIN' | 'USER' | 'MODERATOR';
  isActive: boolean;
}

export interface ApiResponse<T> {
  success: boolean;
  data: T;
  message: string;
}

export interface UserStats {
  total: number;
  active: number;
  inactive: number;
  admins: number;
  users: number;
  moderators: number;
}
