import { request } from './api';

export interface Modalidade {
  id?: number;
  name: string;
  description?: string;
}

export const modalidadeService = {
  getAll: async (): Promise<Modalidade[]> => {
    return request<Modalidade[]>('/modalidades');
  },
  create: async (modalidade: Omit<Modalidade, 'id'>): Promise<Modalidade> => {
    return request<Modalidade>('/modalidades', {
      method: 'POST',
      body: JSON.stringify(modalidade),
    });
  },
  update: async (id: number, modalidade: Modalidade): Promise<Modalidade> => {
    return request<Modalidade>(`/modalidades/${id}`, {
      method: 'PUT',
      body: JSON.stringify(modalidade),
    });
  },
  delete: async (id: number): Promise<void> => {
    return request<void>(`/modalidades/${id}`, {
      method: 'DELETE',
    });
  },
};