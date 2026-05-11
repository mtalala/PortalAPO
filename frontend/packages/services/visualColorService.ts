import { request } from './api';

export interface VisualColor {
  id?: number;
  name: string;
  color: string;
  description?: string;
}

export const visualColorService = {
  getAll: async (): Promise<VisualColor[]> => {
    return request<VisualColor[]>('/visual-colors');
  },
  create: async (visualColor: Omit<VisualColor, 'id'>): Promise<VisualColor> => {
    return request<VisualColor>('/visual-colors', {
      method: 'POST',
      body: JSON.stringify(visualColor),
    });
  },
  update: async (id: number, visualColor: VisualColor): Promise<VisualColor> => {
    return request<VisualColor>(`/visual-colors/${id}`, {
      method: 'PUT',
      body: JSON.stringify(visualColor),
    });
  },
  delete: async (id: number): Promise<void> => {
    return request<void>(`/visual-colors/${id}`, {
      method: 'DELETE',
    });
  },
};