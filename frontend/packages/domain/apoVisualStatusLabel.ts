// src/domain/apoVisualStatusLabel.ts
import type { ApoVisualStatus } from "./apoVisualStatus";

export function getApoVisualStatusLabel(
  status: ApoVisualStatus
): string {
  switch (status) {
    case "PENDENTE":
      return "Pendente";

    case "EM_ANDAMENTO":
      return "Em andamento";

    case "CONCLUIDA":
      return "Concluída";
  }
}