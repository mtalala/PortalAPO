// src/domain/apoStatusLabel.ts
import type { ApoStatus } from "./apoStatus";

export function getApoStatusLabel(status: ApoStatus): string {
  switch (status) {
    case "PENDENTE_ORIENTADOR":
      return "Aguardando orientador";
    case "PENDENTE_COMISSAO":
      return "Aguardando comissão";
    case "PENDENTE_COORDENACAO":
      return "Aguardando coordenação";
    case "APROVADA":
      return "Aprovada";
    case "REJEITADA":
      return "Rejeitada";
    default:
      return "Status desconhecido";
  }
}