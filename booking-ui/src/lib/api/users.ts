import type {
  User,
  CreateUserRequest,
  UpdateUserRequest,
  ApiResponse,
  UserStats,
} from '@/models/users';

const API_BASE_URL = process.env.NEXT_BASE_BE_URL || 'http://localhost:8080/api';

export const usersApi = {
  // Get all users
  getAll: async (): Promise<ApiResponse<User[]>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users`);
    return response.json();
  },

  // Get user by ID
  getById: async (id: string): Promise<ApiResponse<User>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users/${id}`);
    return response.json();
  },

  // Create new user
  create: async (data: CreateUserRequest): Promise<ApiResponse<User>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    return response.json();
  },

  // Update user
  update: async (data: UpdateUserRequest): Promise<ApiResponse<User>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users/${data.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    return response.json();
  },

  // Delete user
  delete: async (id: string): Promise<ApiResponse<void>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users/${id}`, {
      method: 'DELETE',
    });
    return response.json();
  },

  // Toggle user status
  toggleStatus: async (id: string): Promise<ApiResponse<User>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users/${id}/toggle-status`, {
      method: 'PATCH',
    });
    return response.json();
  },

  // Get user statistics
  getStats: async (): Promise<ApiResponse<UserStats>> => {
    const response = await fetch(`${API_BASE_URL}/admin/users/stats`);
    return response.json();
  },
};
