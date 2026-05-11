// src/domain/apoVisualStatus.ts
import type { ApoStatus } from "./apoStatus";

export type ApoVisualStatus =
  | "PENDENTE"
  | "EM_ANDAMENTO"
  | "CONCLUIDA";

export function getApoVisualStatus(
  status: ApoStatus
): ApoVisualStatus {
  switch (status) {
    case "PENDENTE_ORIENTADOR":
      return "PENDENTE";

    case "PENDENTE_COMISSAO":
    case "PENDENTE_COORDENACAO":
      return "EM_ANDAMENTO";

    case "APROVADA":
    case "REJEITADA":
      return "CONCLUIDA";
  }
}