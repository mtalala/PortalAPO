import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';
import { modalidadeService, Modalidade } from '@/packages/services/modalidadeService';

export default function EditModalidadeScreen() {
  const { id: rawId } = useLocalSearchParams();

  const id = Array.isArray(rawId) ? rawId[0] : rawId;

  const [modalidade, setModalidade] = useState<Modalidade | null>(null);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  useEffect(() => {
    loadModalidade();
  }, [id]);

  const loadModalidade = async () => {
    if (!id) return;

    try {
      const data = await modalidadeService.getAll();
      const found = data.find(m => String(m.id) === id); // <- importante
      if (found) setModalidade(found);
    } catch {
      Alert.alert('Erro', 'Falha ao carregar modalidade');
    }
  };

  const handleSubmit = async () => {
    if (!modalidade) return;

    setLoading(true);
    try {
      await modalidadeService.update(modalidade.id!, modalidade);
      Alert.alert('Sucesso', 'Modalidade atualizada');
      router.back();
    } catch {
      Alert.alert('Erro', 'Falha ao atualizar modalidade');
    } finally {
      setLoading(false);
    }
  };

  if (!modalidade) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Editar Modalidade</Text>

      <TextInput
        placeholder="Nome"
        value={modalidade.name}
        onChangeText={(text) => setModalidade({ ...modalidade, name: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />

      <TextInput
        placeholder="Descrição"
        value={modalidade.description || ''}
        onChangeText={(text) =>
          setModalidade({ ...modalidade, description: text })
        }
        style={{ borderWidth: 1, padding: 10, marginBottom: 20 }}
      />

      <Button
        title={loading ? 'Atualizando...' : 'Atualizar'}
        onPress={handleSubmit}
        disabled={loading}
      />
    </View>
  );
}