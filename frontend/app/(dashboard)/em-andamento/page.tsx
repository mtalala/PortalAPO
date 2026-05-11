import { useUser } from "@/packages/context/UserContext";
import React, { useEffect, useState } from "react";
import { ScrollView, Text, useWindowDimensions, View } from "react-native";

import RequestCard from "@/components/dashboard/RequestCard";
import RequestCardSkeleton from "@/components/dashboard/RequestCardSkeleton";

import { apos } from "@/packages/data/apos";
import { getApoVisualStatus } from "@/packages/domain/apoVisualStatus";

export default function EmAndamentoScreen() {
  const [isLoading, setIsLoading] = useState(true);
  const { width } = useWindowDimensions();
  const { user } = useUser();

  // Responsivo: 1, 2 ou 3 colunas
  const getColumns = () => {
    if (width >= 1024) return 4; // desktop / macOS / Windows
    if (width >= 768) return 4;  // tablets / web médio
    return 1;                     // mobile
  };

  useEffect(() => {
    // simula loading (remover quando usar API real)
    const timer = setTimeout(() => setIsLoading(false), 700);
    return () => clearTimeout(timer);
  }, []);

  if (!user) return null;

  const emAndamento = apos.filter(
    (apo) => getApoVisualStatus(apo.status) === "EM_ANDAMENTO"
  );

  return (
    <ScrollView contentContainerStyle={{ padding: 16 }}>
      {/* Header */}
      <View style={{ marginBottom: 24 }}>
        <Text style={{ fontSize: 32, fontWeight: "700" }}>Em andamento</Text>
        <Text style={{ color: "#6b7280" }}>{emAndamento.length} APOs em andamento</Text>
      </View>

      {/* Grid de cards */}
      <View style={{ flexDirection: "row", flexWrap: "wrap", gap: 16 }}>
        {isLoading
          ? Array.from({ length: 6 }).map((_, i) => (
              <View key={i} style={{ flexBasis: `${100 / getColumns()}%` }}>
                <RequestCardSkeleton />
              </View>
            ))
          : emAndamento.length === 0
          ? (
            <Text style={{ color: "#9ca3af" }}>Nenhuma APO em andamento.</Text>
          )
          : emAndamento.map((apo) => (
              <View key={apo.id} style={{ flexBasis: `${100 / getColumns()}%` }}>
                <RequestCard apo={apo} />
              </View>
            ))
        }
      </View>
    </ScrollView>
  );
}