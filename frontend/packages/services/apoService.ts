// src/services/apoService.ts
import { apos } from "@/packages/data/apos";
import type { Apo } from "@/packages/types/apo";
import type { Role } from "@/packages/types/user";

let apoState: Apo[] = [...apos];

export function getApos(): Apo[] {
  return apoState;
}

export function getApoById(id: string): Apo | null {
  return apoState.find((a) => a.id === id) ?? null;
}

export function approveApo(
  apoId: string,
  userId: string,
  role: Role
): void {
  const apo = getApoById(apoId);
  if (!apo) return;

  // evita aprovação duplicada por mesmo usuário/papel
  const alreadyApproved = apo.approvals.some(
    (a) => a.userId === userId && a.role === role
  );
  if (alreadyApproved) return;

  apo.approvals.push({ userId, role, approved: true });

  switch (role) {
    case "orientador": {
      apo.status = "PENDENTE_COMISSAO";
      return;
    }

    case "comissao": {
      const approvedByCommission = new Set(
        apo.approvals
          .filter((a) => a.role === "comissao" && a.approved)
          .map((a) => a.userId)
      );

      if (
        approvedByCommission.size >=
        apo.requiredCommissionApprovals
      ) {
        apo.status = "PENDENTE_COORDENACAO";
      }
      return;
    }

    case "coordenador": {
      apo.status = "APROVADA";
      return;
    }

    default:
      return;
  }
}

export function rejectApo(apoId: string): void {
  const apo = getApoById(apoId);
  if (!apo) return;

  apo.status = "REJEITADA";
}