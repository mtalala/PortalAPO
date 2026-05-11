// src/app/solicitacoes/page.tsx
import React, { useEffect, useState } from "react";
import {
  Alert,
  FlatList,
  Pressable,
  ScrollView,
  StyleSheet,
  Switch,
  Text,
  TextInput,
  View,
} from "react-native";

import * as DocumentPicker from "expo-document-picker";

import activitiesData from "@/packages/data/activities";
import coordenadoresData from "@/packages/data/coordenadores";
import programsData from "@/packages/data/programs";
import { Activity, Coordenador, Program } from "@/packages/types/types";

/**
 * Arquivo unificado (Web + Mobile + Desktop)
 */
type SelectedFile = DocumentPicker.DocumentPickerAsset;

export default function SolicitacoesPage() {
  // ===== FORM STATE =====
  const [program, setProgram] = useState<number | null>(null);
  const [matricula, setMatricula] = useState("");
  const [nome, setNome] = useState("");
  const [orientador, setOrientador] = useState("");
  const [coordenador, setCoordenador] = useState<number | null>(null);
  const [semestre, setSemestre] = useState("");
  const [codigoApo, setCodigoApo] = useState("");
  const [selectedActivities, setSelectedActivities] = useState<number[]>([]);
  const [selectedFiles, setSelectedFiles] = useState<SelectedFile[]>([]);
  const [isSubmitting, setIsSubmitting] = useState(false);

  // ===== DATA =====
  const [programs, setPrograms] = useState<Program[]>([]);
  const [coordenadores, setCoordenadores] = useState<Coordenador[]>([]);
  const [activities, setActivities] = useState<Activity[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setPrograms(programsData);
    setCoordenadores(coordenadoresData);
    setActivities(activitiesData);
    setLoading(false);
  }, []);

  // ===== LOGIC =====
  const toggleActivity = (id: number) => {
    setSelectedActivities((prev) =>
      prev.includes(id) ? prev.filter((a) => a !== id) : [...prev, id]
    );
  };

  const totalPoints = selectedActivities.reduce((acc, id) => {
    const activity = activities.find((a) => a.id === id);
    return acc + (activity?.points ?? 0);
  }, 0);

  // ===== FILE PICKER (ALL PLATFORMS) =====
  const handleFileUpload = async () => {
    const result = await DocumentPicker.getDocumentAsync({
      multiple: true,
    });

    if (result.canceled) return;

    setSelectedFiles((prev) => [...prev, ...result.assets]);
  };

  const removeFile = (index: number) => {
    setSelectedFiles((prev) => prev.filter((_, i) => i !== index));
  };

  const handleSubmit = () => {
    if (
      !program ||
      !coordenador ||
      !matricula ||
      !nome ||
      !orientador ||
      !semestre ||
      !codigoApo
    ) {
      Alert.alert("Erro", "Preencha todos os campos obrigatórios");
      return;
    }

    setIsSubmitting(true);

    const payload = {
      program,
      matricula,
      nome,
      orientador,
      coordenador,
      semestre,
      codigoApo,
      atividades: selectedActivities,
      totalPoints,
      files: selectedFiles,
      createdAt: new Date().toISOString(),
    };

    console.log("Payload:", payload);

    setTimeout(() => {
      setIsSubmitting(false);
      Alert.alert("Sucesso", "Formulário enviado (mock)");

      setProgram(null);
      setMatricula("");
      setNome("");
      setOrientador("");
      setCoordenador(null);
      setSemestre("");
      setCodigoApo("");
      setSelectedActivities([]);
      setSelectedFiles([]);
    }, 1000);
  };

  if (loading) {
    return <Text style={{ padding: 16 }}>Carregando dados...</Text>;
  }

  // ===== UI =====
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Enviar Nova Solicitação</Text>

      {/* PROGRAMAS */}
      <Text style={styles.label}>Selecionar programa</Text>
      {programs.map((p) => (
        <Pressable
          key={p.id}
          onPress={() => setProgram(p.id)}
          style={[
            styles.option,
            program === p.id && styles.optionActive,
          ]}
        >
          <Text>{p.name}</Text>
        </Pressable>
      ))}

      <Input label="Código de matrícula" value={matricula} onChange={setMatricula} />
      <Input label="Nome" value={nome} onChange={setNome} />
      <Input label="Nome do Orientador" value={orientador} onChange={setOrientador} />
      <Input label="Semestre" value={semestre} onChange={setSemestre} />
      <Input label="Código da APO" value={codigoApo} onChange={setCodigoApo} />

      {/* COORDENADORES */}
      <Text style={styles.label}>Selecionar Coordenador</Text>
      {coordenadores.map((c) => (
        <Pressable
          key={c.id}
          onPress={() => setCoordenador(c.id)}
          style={[
            styles.option,
            coordenador === c.id && styles.optionActive,
          ]}
        >
          <Text>{c.name}</Text>
        </Pressable>
      ))}

      {/* ATIVIDADES */}
      <Text style={styles.label}>Atividades desenvolvidas</Text>
      {activities.map((a) => {
        const selected = selectedActivities.includes(a.id);
        return (
          <Pressable
            key={a.id}
            onPress={() => toggleActivity(a.id)}
            style={[
              styles.activity,
              selected && styles.optionActive,
            ]}
          >
            <Switch value={selected} />
            <Text style={{ marginLeft: 8 }}>
              {a.label} ({a.points} pts)
            </Text>
          </Pressable>
        );
      })}

      <Text style={styles.total}>Total de pontos: {totalPoints}</Text>

      {/* UPLOAD */}
      <Pressable style={styles.upload} onPress={handleFileUpload}>
        <Text style={styles.uploadText}>Anexar arquivos</Text>
      </Pressable>

      {selectedFiles.length > 0 && (
        <FlatList
          data={selectedFiles}
          keyExtractor={(item) => item.uri}
          renderItem={({ item, index }) => (
            <View style={styles.fileItem}>
              <Text style={{ flex: 1 }} numberOfLines={1}>
                {item.name ?? item.uri.split("/").pop()}
              </Text>
              <Pressable onPress={() => removeFile(index)}>
                <Text style={styles.remove}>×</Text>
              </Pressable>
            </View>
          )}
        />
      )}

      {/* SUBMIT */}
      <Pressable
        onPress={handleSubmit}
        disabled={isSubmitting}
        style={[
          styles.submit,
          isSubmitting && { opacity: 0.6 },
        ]}
      >
        <Text style={styles.submitText}>
          {isSubmitting ? "Enviando..." : "Enviar"}
        </Text>
      </Pressable>
    </ScrollView>
  );
}

/* ===== COMPONENTS ===== */

function Input({
  label,
  value,
  onChange,
}: {
  label: string;
  value: string;
  onChange: (v: string) => void;
}) {
  return (
    <View style={{ marginTop: 12 }}>
      <Text style={styles.label}>{label}</Text>
      <TextInput value={value} onChangeText={onChange} style={styles.input} />
    </View>
  );
}

/* ===== STYLES ===== */

const styles = StyleSheet.create({
  container: { padding: 16 },
  title: { fontSize: 28, fontWeight: "bold", marginBottom: 16 },
  label: { fontWeight: "600", marginTop: 12 },
  input: {
    borderWidth: 1,
    borderColor: "#d1d5db",
    borderRadius: 6,
    padding: 8,
    marginTop: 4,
  },
  option: {
    padding: 10,
    borderWidth: 1,
    borderColor: "#d1d5db",
    borderRadius: 6,
    marginBottom: 6,
  },
  optionActive: {
    borderColor: "#dc2626",
  },
  activity: {
    flexDirection: "row",
    alignItems: "center",
    padding: 10,
    borderWidth: 1,
    borderColor: "#d1d5db",
    borderRadius: 6,
    marginBottom: 6,
  },
  total: { marginTop: 8, fontWeight: "600" },
  upload: {
    marginTop: 16,
    padding: 14,
    borderWidth: 2,
    borderStyle: "dashed",
    borderColor: "#9ca3af",
    borderRadius: 8,
    alignItems: "center",
  },
  uploadText: {
    color: "#374151",
    fontWeight: "500",
  },
  fileItem: {
    flexDirection: "row",
    alignItems: "center",
    marginTop: 8,
    padding: 8,
    borderWidth: 1,
    borderColor: "#d1d5db",
    borderRadius: 6,
  },
  remove: {
    color: "#dc2626",
    fontWeight: "bold",
    marginLeft: 8,
  },
  submit: {
    marginTop: 24,
    backgroundColor: "#dc2626",
    padding: 14,
    borderRadius: 8,
    alignItems: "center",
  },
  submitText: { color: "#fff", fontWeight: "600" },
});