// src/data/apos.ts
import type { Apo } from "@/packages/types/apo";

export const apos: Apo[] = [
  {
    id: "1",
    codigoApo: "APO-2024-001",
    nome: "Aluno Teste",
    matricula: "123456",
    program: "Ciência da Computação",
    semestre: "2024.1",
    orientador: "Prof. João",
    coordenador: "Profa. Maria",
    status: "PENDENTE_ORIENTADOR",
    activities: [
      { label: "Iniciação científica", points: 40 },
      { label: "Monitoria", points: 20 },
    ],
    totalPoints: 60,
    files: [{ name: "relatorio.pdf", url: "#" }],
    approvals: [],
    requiredCommissionApprovals: 3,
  },

  {
    id: "2",
    codigoApo: "APO-2024-002",
    nome: "Ana Souza",
    matricula: "20230012",
    program: "Engenharia de Produção",
    semestre: "2024.1",
    orientador: "Prof. Carlos Lima",
    coordenador: "Profa. Helena Rocha",
    status: "PENDENTE_COORDENACAO",
    activities: [
      { label: "Publicação de Artigo", points: 12 },
      { label: "Apresentação em Evento Científico", points: 8 },
    ],
    totalPoints: 20,
    files: [{ name: "artigo.pdf", url: "#" }],
    approvals: [
      {
        userId: "orientador-01",
        role: "orientador",
        approved: true,
      },
    ],
    requiredCommissionApprovals: 3,
  },

  {
    id: "3",
    codigoApo: "APO-2024-003",
    nome: "Bruno Martins",
    matricula: "20221198",
    program: "Engenharia Elétrica e Computação",
    semestre: "2023.2",
    orientador: "Profa. Renata Alves",
    coordenador: "Prof. Marcos Teixeira",
    status: "PENDENTE_COMISSAO",
    activities: [
      { label: "Desenvolvimento de Projeto Tecnológico", points: 30 },
    ],
    totalPoints: 30,
    files: [{ name: "projeto.pdf", url: "#" }],
    approvals: [
      {
        userId: "orientador-02",
        role: "orientador",
        approved: true,
      },
      {
        userId: "coordenador-01",
        role: "coordenador",
        approved: true,
      },
    ],
    requiredCommissionApprovals: 3,
  },

  {
    id: "4",
    codigoApo: "APO-2024-004",
    nome: "Carla Nogueira",
    matricula: "20214567",
    program: "Administração de Empresas",
    semestre: "2023.2",
    orientador: "Prof. Eduardo Ramos",
    coordenador: "Profa. Maria Silva",
    status: "APROVADA",
    completedAt: "2025-01-20",
    activities: [
      { label: "Publicação de Livro", points: 50 },
      { label: "Participação em Comissão Acadêmica", points: 10 },
    ],
    totalPoints: 60,
    files: [{ name: "livro.pdf", url: "#" }],
    approvals: [
      {
        userId: "orientador-03",
        role: "orientador",
        approved: true,
      },
      {
        userId: "coordenador-02",
        role: "coordenador",
        approved: true,
      },
      {
        userId: "comissao-01",
        role: "comissao",
        approved: true,
      },
    ],
    requiredCommissionApprovals: 3,
  },

  {
    id: "5",
    codigoApo: "APO-2024-005",
    nome: "Diego Fernandes",
    matricula: "20235555",
    program: "Economia e Mercados",
    semestre: "2024.1",
    orientador: "Profa. Juliana Costa",
    coordenador: "Prof. Roberto Mendes",
    status: "REJEITADA",
    completedAt: "2025-02-02",
    activities: [
      { label: "Participação em Evento Interno", points: 5 },
    ],
    totalPoints: 5,
    files: [{ name: "certificado.pdf", url: "#" }],
    approvals: [
      {
        userId: "orientador-04",
        role: "orientador",
        approved: false,
      },
    ],
    requiredCommissionApprovals: 3,
  },
];