import AsyncStorage from '@react-native-async-storage/async-storage';
import request from './api';

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  role: string;
}

export const authService = {
  login: async (credentials: LoginRequest): Promise<LoginResponse> => {
    const response = await request<LoginResponse>('/auth/login', {
      method: 'POST',
      body: JSON.stringify(credentials),
    });

    await AsyncStorage.setItem('token', response.token);
    await AsyncStorage.setItem('role', response.role);

    return response;
  },

  logout: async () => {
    await AsyncStorage.removeItem('token');
    await AsyncStorage.removeItem('role');
  },

  getToken: async () => {
    return AsyncStorage.getItem('token');
  },
};