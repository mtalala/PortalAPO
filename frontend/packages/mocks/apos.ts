import type { Role } from "@/packages/types/user";

export type ApoStatus =
  | "PENDENTE_ORIENTADOR"
  | "PENDENTE_COMISSAO"
  | "PENDENTE_COORDENACAO"
  | "CONCLUIDA"
  | "REJEITADA";

export interface ApoApproval {
  userId: string;
  role: Role;
  approved: boolean;
}

export interface Apo {
  id: string;
  alunoId: string;

  // dados do formulário (read-only)
  program: string;
  matricula: string;
  nome: string;
  orientador: string;
  coordenador: string;
  semestre: string;
  codigoApo: string;

  activities: {
    label: string;
    points: number;
  }[];

  totalPoints: number;

  files: {
    name: string;
    url: string;
  }[];

  status: ApoStatus;

  approvals: ApoApproval[];
}