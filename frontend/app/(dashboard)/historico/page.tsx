import { useUser } from "@/packages/context/UserContext";
import React, { useEffect, useState } from "react";
import {
  Pressable,
  ScrollView,
  Text,
  useWindowDimensions,
  View,
} from "react-native";

import RequestCardSkeleton from "@/components/dashboard/RequestCardSkeleton";
import { apos } from "@/packages/data/apos";

function hasCompletedAt(
  apo: typeof apos[number]
): apo is typeof apos[number] & { completedAt: string } {
  return Boolean(apo.completedAt);
}

export default function HistoricoScreen() {
  const [isLoading, setIsLoading] = useState(true);
  const { width } = useWindowDimensions();
  const { user } = useUser();

  const getColumns = () => {
    if (width >= 1024) return 4;
    if (width >= 768) return 4;
    return 1;
  };

  useEffect(() => {
    const timer = setTimeout(() => setIsLoading(false), 700);
    return () => clearTimeout(timer);
  }, []);

  if (!user) return null;

  const completedApos = apos
    .filter(
      (apo) =>
        (apo.status === "APROVADA" || apo.status === "REJEITADA")
    )
    .filter(hasCompletedAt)
    .sort(
      (a, b) =>
        new Date(b.completedAt).getTime() -
        new Date(a.completedAt).getTime()
    );

  return (
    <ScrollView contentContainerStyle={{ padding: 16 }}>
      <View style={{ marginBottom: 24 }}>
        <Text style={{ fontSize: 32, fontWeight: "600" }}>
          Histórico
        </Text>
      </View>

      <View style={{ flexDirection: "row", flexWrap: "wrap", gap: 16 }}>
        {isLoading ? (
          Array.from({ length: 6 }).map((_, i) => (
            <View key={i} style={{ flexBasis: `${100 / getColumns()}%` }}>
              <RequestCardSkeleton />
            </View>
          ))
        ) : completedApos.length === 0 ? (
          <Text style={{ color: "#9ca3af" }}>
            Nenhuma APO concluída.
          </Text>
        ) : (
          completedApos.map((apo) => (
            <Pressable
              key={apo.id}
              style={{
                flexBasis: `${100 / getColumns()}%`,
                backgroundColor: "#fff",
                borderRadius: 12,
                borderWidth: 1,
                borderColor: "#e5e7eb",
                padding: 12,
                marginBottom: 16,
              }}
            >
              <Text
                style={{
                  fontSize: 12,
                  fontWeight: "600",
                  color: "#4b5563",
                }}
              >
                {apo.codigoApo}
              </Text>

              <Text
                style={{
                  marginTop: 4,
                  fontSize: 14,
                  fontWeight: "500",
                  color: "#111827",
                }}
              >
                {apo.nome}
              </Text>

              <Text
                style={{
                  marginTop: 2,
                  fontSize: 12,
                  color: "#6b7280",
                }}
              >
                {apo.program} — {apo.semestre}
              </Text>

              <Text
                style={{
                  marginTop: 4,
                  fontSize: 12,
                  fontWeight: "600",
                  color:
                    apo.status === "APROVADA"
                      ? "#16a34a"
                      : "#dc2626",
                }}
              >
                {apo.status === "APROVADA"
                  ? "Aprovada"
                  : "Rejeitada"}
              </Text>

              <Text
                style={{
                  marginTop: 6,
                  fontSize: 10,
                  color: "#9ca3af",
                }}
              >
                Concluída em{" "}
                {new Date(apo.completedAt).toLocaleDateString("pt-BR")}
              </Text>
            </Pressable>
          ))
        )}
      </View>
    </ScrollView>
  );
}