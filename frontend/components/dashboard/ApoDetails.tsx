import React from "react";
import { ScrollView, StyleSheet, Text, View } from "react-native";
import Feather from "react-native-vector-icons/Feather";

interface ApoDetailsProps {
  data: {
    programName: string;
    matricula: string;
    nome: string;
    orientador: string;
    coordenadorName: string;
    semestre: string;
    codigoApo: string;
    activities: { label: string; points: number }[];
    files: { name: string }[];
  };
}

export default function ApoDetails({ data }: ApoDetailsProps) {
  const totalPoints = data.activities.reduce((acc, a) => acc + a.points, 0);

  return (
    <ScrollView contentContainerStyle={styles.container}>
      {/* Dados gerais */}
      <View style={styles.section}>
        <Field label="Programa" value={data.programName} />
        <Field label="Matrícula" value={data.matricula} />
        <Field label="Nome" value={data.nome} />
        <Field label="Orientador" value={data.orientador} />
        <Field label="Coordenador" value={data.coordenadorName} />
        <Field label="Semestre" value={data.semestre} />
        <Field label="Código da APO" value={data.codigoApo} />
      </View>

      {/* Atividades */}
      <View style={[styles.section, { marginTop: 16 }]}>
        <Text style={styles.sectionTitle}>Atividades desenvolvidas</Text>
        <View style={styles.box}>
          {data.activities.map((a, i) => (
            <Text key={i} style={styles.itemText}>
              • {a.label} ({a.points} pts)
            </Text>
          ))}
        </View>
        <Text style={[styles.itemText, { marginTop: 8, fontWeight: "600" }]}>
          Total de pontos: {totalPoints}
        </Text>
      </View>

      {/* Arquivos */}
      <View style={[styles.section, { marginTop: 16 }]}>
        <Text style={styles.sectionTitle}>Arquivos anexados</Text>
        <View style={{ marginTop: 4 }}>
          {data.files.map((file, i) => (
            <View key={i} style={styles.fileRow}>
              <Feather name="file-text" size={16} color="#6B7280" />
              <Text style={styles.itemText}>{file.name}</Text>
            </View>
          ))}
        </View>
      </View>
    </ScrollView>
  );
}

function Field({ label, value }: { label: string; value: string }) {
  return (
    <View style={styles.field}>
      <Text style={styles.fieldLabel}>{label}</Text>
      <Text style={styles.fieldValue}>{value}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
  section: {
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-between",
  },
  sectionTitle: {
    fontWeight: "600",
    marginBottom: 4,
    fontSize: 16,
  },
  box: {
    width: "100%",
    backgroundColor: "#F9FAFB",
    borderRadius: 8,
    padding: 8,
  },
  itemText: {
    fontSize: 14,
    color: "#111827",
  },
  fileRow: {
    flexDirection: "row",
    alignItems: "center",
    padding: 8,
    backgroundColor: "#FFFFFF",
    borderRadius: 6,
    borderWidth: 1,
    borderColor: "#D1D5DB",
    marginBottom: 4,
    gap: 4,
  },
  field: {
    width: "48%",
    marginBottom: 12,
  },
  fieldLabel: {
    fontSize: 12,
    color: "#6B7280",
  },
  fieldValue: {
    fontSize: 14,
    fontWeight: "500",
    color: "#111827",
  },
});