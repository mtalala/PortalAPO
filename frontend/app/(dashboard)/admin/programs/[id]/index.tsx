import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';
import { getPrograms, updateProgram } from '@/packages/services/programService';
import type { Program } from '@/packages/types/types';

export default function EditProgramScreen() {
  const rawId = useLocalSearchParams().id;
  const id = Array.isArray(rawId) ? rawId[0] : rawId;

  const [program, setProgram] = useState<Program | null>(null);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  useEffect(() => {
    loadProgram();
  }, [id]);

  const loadProgram = async () => {
    if (!id) return;

    try {
      const data = await getPrograms();
      const found = data.find(p => String(p.id) === id);
      if (found) setProgram(found);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar programa');
    }
  };

  const handleSubmit = async () => {
    if (!program) return;

    setLoading(true);
    try {
      await updateProgram(program.id, program);
      Alert.alert('Sucesso', 'Programa atualizado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao atualizar programa');
    } finally {
      setLoading(false);
    }
  };

  if (!program) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>
        Editar Programa
      </Text>

      <TextInput
        placeholder="Nome"
        value={program.name}
        onChangeText={(text) =>
          setProgram({ ...program, name: text })
        }
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />

      <Button
        title={loading ? 'Atualizando...' : 'Atualizar'}
        onPress={handleSubmit}
        disabled={loading}
      />
    </View>
  );
}