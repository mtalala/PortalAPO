// src/types/notification.ts

export type UserRole =
  | "Aluno"
  | "Orientador"
  | "Coordenador"
  | "Comissão";

export interface Notification {
  id: string;
  role: UserRole;
  topic: string;
  requestColor: string;
  createdAt: string; // ISO date
  read: boolean;
}