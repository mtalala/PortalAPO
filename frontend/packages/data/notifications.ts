// src/data/notifications.ts
import { Notification } from "@/packages/types/notification";

export const notifications: Notification[] = [
  {
    id: "1",
    role: "Orientador",
    topic: "Publicação de Livro",
    requestColor: "bg-[#1E99A7]",
    createdAt: "2025-03-20T10:30:00Z",
    read: false,
  },
  {
    id: "2",
    role: "Aluno",
    topic: "Depósito de Patente",
    requestColor: "bg-[#C18B2F]",
    createdAt: "2025-03-19T08:15:00Z",
    read: false,
  },
  {
    id: "3",
    role: "Comissão",
    topic: "Registro de Software",
    requestColor: "bg-[#2F5DA8]",
    createdAt: "2025-03-17T14:00:00Z",
    read: true,
  },
];