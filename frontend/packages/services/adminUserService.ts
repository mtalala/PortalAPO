import { request } from './api';

export interface AdminUser {
  id?: number;
  username: string;
  password: string;
  role: string;
  ativo: boolean;
  pessoa?: any; // Assuming Pessoa type
}

export const adminUserService = {
  getAll: async (): Promise<AdminUser[]> => {
    return request<AdminUser[]>('/users');
  },
  create: async (user: Omit<AdminUser, 'id'>): Promise<AdminUser> => {
    return request<AdminUser>('/users', {
      method: 'POST',
      body: JSON.stringify(user),
    });
  },
  update: async (id: number, user: AdminUser): Promise<AdminUser> => {
    return request<AdminUser>(`/users/${id}`, {
      method: 'PUT',
      body: JSON.stringify(user),
    });
  },
  delete: async (id: number): Promise<void> => {
    return request<void>(`/users/${id}`, {
      method: 'DELETE',
    });
  },
};