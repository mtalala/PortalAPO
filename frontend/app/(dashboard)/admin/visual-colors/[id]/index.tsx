import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';
import { visualColorService, VisualColor } from '@/packages/services/visualColorService';

export default function EditVisualColorScreen() {
  const { id } = useLocalSearchParams();
  const [visualColor, setVisualColor] = useState<VisualColor | null>(null);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  useEffect(() => {
    loadVisualColor();
  }, [id]);

  const loadVisualColor = async () => {
    if (!id) return;
    try {
      const data = await visualColorService.getAll();
      const found = data.find(vc => vc.id === parseInt(id));
      if (found) setVisualColor(found);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar visual color');
    }
  };

  const handleSubmit = async () => {
    if (!visualColor) return;

    setLoading(true);
    try {
      await visualColorService.update(visualColor.id!, visualColor);
      Alert.alert('Sucesso', 'Visual Color atualizado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao atualizar visual color');
    } finally {
      setLoading(false);
    }
  };

  if (!visualColor) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Editar Visual Color</Text>
      <TextInput
        placeholder="Nome"
        value={visualColor.name}
        onChangeText={(text) => setVisualColor({ ...visualColor, name: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Cor"
        value={visualColor.color}
        onChangeText={(text) => setVisualColor({ ...visualColor, color: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Descrição"
        value={visualColor.description || ''}
        onChangeText={(text) => setVisualColor({ ...visualColor, description: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 20 }}
      />
      <Button title={loading ? 'Atualizando...' : 'Atualizar'} onPress={handleSubmit} disabled={loading} />
    </View>
  );
}