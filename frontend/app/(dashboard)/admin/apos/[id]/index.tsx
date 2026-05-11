import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';
import { getApoById, updateApo } from '@/packages/services/apoService';
import { getPrograms } from '@/packages/services/programService';
import type { Apo } from '@/packages/types/apo';
import type { Program } from '@/packages/types/types';

export default function EditApoScreen() {
  const { id } = useLocalSearchParams();
  const [apo, setApo] = useState<Apo | null>(null);
  const [programs, setPrograms] = useState<Program[]>([]);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  useEffect(() => {
    loadApo();
    loadPrograms();
  }, [id]);

  const loadApo = async () => {
    if (!id) return;
    try {
      const data = await getApoById(id as string);
      setApo(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar APO');
    }
  };

  const loadPrograms = async () => {
    try {
      const data = await getPrograms();
      setPrograms(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar programas');
    }
  };

  const handleSubmit = async () => {
    if (!apo) return;

    setLoading(true);
    try {
      await updateApo(apo);
      Alert.alert('Sucesso', 'APO atualizado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao atualizar APO');
    } finally {
      setLoading(false);
    }
  };

  if (!apo) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Editar APO</Text>
      <TextInput
        placeholder="Título"
        value={apo.title}
        onChangeText={(text) => setApo({ ...apo, title: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Descrição"
        value={apo.description}
        onChangeText={(text) => setApo({ ...apo, description: text })}
        multiline
        style={{ borderWidth: 1, padding: 10, marginBottom: 10, height: 100 }}
      />
      {/* Adicionar mais campos conforme necessário */}
      <Button title={loading ? 'Atualizando...' : 'Atualizar'} onPress={handleSubmit} disabled={loading} />
    </View>
  );
}