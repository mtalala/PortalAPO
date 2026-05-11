// src/data/requests.ts

export type RequestStatus = "Pendente" | "Em andamento" | "Concluída";

export interface Request {
  id: number;
  category: string;
  title: string;
  level: string;
  color: string;
  status: RequestStatus;
  createdAt: string;
  updatedAt?: string;
  completedAt?: string;
}

export const requests: Request[] = [
  {
    id: 1,
    category: "APOs",
    title: "Publicação de Livro",
    level: "Pós Graduação",
    color: "bg-[#1E99A7]",
    status: "Pendente",
    createdAt: "2025-02-01",
  },
  {
    id: 2,
    category: "APOs",
    title: "Publicação de Artigo em Revista sobre tema tecnológico",
    level: "Pós Graduação",
    color: "bg-[#91277F]",
    status: "Em andamento",
    createdAt: "2025-01-10",
    updatedAt: "2025-02-05",
  },
  {
    id: 3,
    category: "APOs",
    title: "Formalização de norma ou marco regulatório",
    level: "Pós Graduação",
    color: "bg-[#8C4444]",
    status: "Concluída",
    createdAt: "2024-12-01",
    completedAt: "2025-01-20",
  },
  {
    id: 4,
    category: "APOs",
    title: "Desenvolvimento de Serviços Técnicos",
    level: "Pós Graduação",
    color: "bg-[#49754D]",
    status: "Pendente",
    createdAt: "2025-02-15",
  },
  {
    id: 5,
    category: "APOs",
    title: "Registro de Software",
    level: "Graduação",
    color: "bg-[#2F5DA8]",
    status: "Em andamento",
    createdAt: "2025-01-25",
    updatedAt: "2025-02-18",
  },
  {
    id: 6,
    category: "APOs",
    title: "Depósito de Patente",
    level: "Pós Graduação",
    color: "bg-[#C18B2F]",
    status: "Concluída",
    createdAt: "2024-11-05",
    completedAt: "2025-02-02",
  },
];