// src/domain/apoVisualStatusColor.ts
import type { ApoVisualStatus } from "./apoVisualStatus";

export function getApoVisualStatusColor(
  status: ApoVisualStatus
): string {
  switch (status) {
    case "PENDENTE":
      return "#DC2626"
    case "EM_ANDAMENTO":
      return "#CA8A04"; 
    case "CONCLUIDA":
      return "#16A34A";
  }
}