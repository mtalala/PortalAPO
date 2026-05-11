// src/domain/apoStatusColor.ts
import type { ApoStatus } from "./apoStatus";

export function getApoStatusColor(status: ApoStatus): string {
  switch (status) {
    case "APROVADA":
      return "#16a34a"; // verde
    case "REJEITADA":
      return "#dc2626"; // vermelho
    case "PENDENTE_ORIENTADOR":
    case "PENDENTE_COMISSAO":
    case "PENDENTE_COORDENACAO":
      return "#ca8a04"; // amarelo
    default:
      return "#9ca3af"; // cinza
  }
}