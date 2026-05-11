// src/app/apo/[id]/page.tsx
import { router, useLocalSearchParams } from "expo-router";
import React from "react";
import {
  Linking,
  Platform,
  Pressable,
  SafeAreaView,
  ScrollView,
  Text,
  useWindowDimensions,
  View,
} from "react-native";

import { useUser } from "@/packages/context/UserContext";
import { canApproveApo } from "@/packages/domain/apoRules";
import { getApoStatusColor } from "@/packages/domain/apoStatusColor";
import { getApoStatusLabel } from "@/packages/domain/apoStatusLabel";
import { approveApo, getApoById, rejectApo } from "@/packages/services/apoService";

export default function ApoViewScreen() {
  const { id } = useLocalSearchParams<{ id: string }>();
  const { user } = useUser();
  const { width } = useWindowDimensions();

  const isDesktop =
    (Platform.OS === "web" && width >= 768) || Platform.OS === "windows" || Platform.OS === "macos";

  if (!user) return null;
  if (!id) return <Text style={{ padding: 24 }}>ID inválido.</Text>;

  const apo = getApoById(id);
  if (!apo) return <Text style={{ padding: 24 }}>APO não encontrada.</Text>;

  const allowedToApprove = canApproveApo(apo, user);

  const handleApprove = () => {
    approveApo(apo.id, user.id, user.role);
    router.replace({ pathname: "/apos/[id]", params: { id: apo.id } });
  };

  const handleReject = () => {
    rejectApo(apo.id);
    router.replace({ pathname: "/apos/[id]", params: { id: apo.id } });
  };

  const Card: React.FC<{ children: React.ReactNode }> = ({ children }) => (
    <View
      style={{
        backgroundColor: "#fff",
        padding: 20,
        marginBottom: 24,
        borderRadius: 12,
        shadowColor: "#000",
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.05,
        shadowRadius: 4,
        elevation: 3,
        width: "100%",
      }}
    >
      {children}
    </View>
  );

  const InfoRow: React.FC<{ label: string; value: string | number }> = ({ label, value }) => (
    <View
      style={{
        width: isDesktop ? "48%" : "100%",
        marginBottom: 12,
      }}
    >
      <Text style={{ fontSize: 12, color: "#6b7280" }}>{label}</Text>
      <Text style={{ fontWeight: "500", color: "#111827", fontSize: 14 }}>{value}</Text>
    </View>
  );

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <ScrollView
        contentContainerStyle={{
          paddingHorizontal: isDesktop ? 48 : 16,
          paddingTop: 24,
          paddingBottom: 40,
        }}
        showsHorizontalScrollIndicator={false}
      >
        {/* Header */}
        <View
          style={{
            flexDirection: isDesktop ? "row" : "column",
            justifyContent: "space-between",
            alignItems: isDesktop ? "center" : "flex-start",
            marginBottom: 24,
          }}
        >
          <Text
            style={{
              fontSize: isDesktop ? 32 : 22,
              fontWeight: "700",
              color: "#111827",
              flexShrink: 1,
              marginBottom: isDesktop ? 0 : 8,
            }}
            numberOfLines={2}
            ellipsizeMode="tail"
          >
            APO {apo.codigoApo}
          </Text>

          <View
            style={{
              backgroundColor: getApoStatusColor(apo.status),
              paddingHorizontal: 14,
              paddingVertical: 6,
              borderRadius: 999,
            }}
          >
            <Text style={{ color: "#fff", fontSize: 12, fontWeight: "600" }}>
              {getApoStatusLabel(apo.status)}
            </Text>
          </View>
        </View>

        {/* Dados principais */}
        <Card>
          <Text style={{ fontWeight: "700", fontSize: 16, marginBottom: 16, color: "#111827" }}>
            Dados principais
          </Text>
          <View
            style={{
              flexDirection: "row",
              flexWrap: "wrap",
              justifyContent: "space-between",
            }}
          >
            <InfoRow label="Aluno" value={apo.nome} />
            <InfoRow label="Matrícula" value={apo.matricula} />
            <InfoRow label="Programa" value={apo.program} />
            <InfoRow label="Semestre" value={apo.semestre} />
            <InfoRow label="Orientador" value={apo.orientador} />
            <InfoRow label="Coordenador" value={apo.coordenador} />
          </View>
        </Card>

        {/* Atividades */}
        <Card>
          <Text style={{ fontWeight: "700", fontSize: 16, marginBottom: 16, color: "#111827" }}>
            Atividades
          </Text>
          {apo.activities.map((a, i) => (
            <Text
              key={i}
              style={{ marginBottom: 8, fontSize: 14, color: "#374151" }}
              numberOfLines={1}
              ellipsizeMode="tail"
            >
              • {a.label} — {a.points} pts
            </Text>
          ))}
          <Text style={{ marginTop: 12, fontWeight: "700", fontSize: 14, color: "#111827" }}>
            Total: {apo.totalPoints} pts
          </Text>
        </Card>

        {/* Arquivos */}
        <Card>
          <Text style={{ fontWeight: "700", fontSize: 16, marginBottom: 16, color: "#111827" }}>
            Arquivos
          </Text>
          {apo.files.map((f, i) => (
            <Pressable
              key={i}
              onPress={() => Linking.openURL(f.url)}
              style={{ marginBottom: 8 }}
            >
              <Text
                style={{
                  color: "#2563eb",
                  textDecorationLine: "underline",
                  fontSize: 14,
                }}
                numberOfLines={1}
                ellipsizeMode="tail"
              >
                {f.name}
              </Text>
            </Pressable>
          ))}
        </Card>

        {/* Ações */}
        {allowedToApprove && (
          <View
            style={{
              flexDirection: isDesktop ? "row" : "column",
              marginTop: 8,
              gap: 12,
            }}
          >
            <Pressable
              onPress={handleApprove}
              style={{
                backgroundColor: "#16a34a",
                paddingVertical: 14,
                borderRadius: 8,
                flex: isDesktop ? 0 : 1,
                alignItems: "center",
              }}
            >
              <Text style={{ color: "#fff", fontWeight: "600", fontSize: 16 }}>Aprovar</Text>
            </Pressable>

            <Pressable
              onPress={handleReject}
              style={{
                backgroundColor: "#dc2626",
                paddingVertical: 14,
                borderRadius: 8,
                flex: isDesktop ? 0 : 1,
                alignItems: "center",
              }}
            >
              <Text style={{ color: "#fff", fontWeight: "600", fontSize: 16 }}>Rejeitar</Text>
            </Pressable>
          </View>
        )}
      </ScrollView>
    </SafeAreaView>
  );
}