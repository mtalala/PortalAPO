import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { visualColorService, VisualColor } from '@/packages/services/visualColorService';

export default function AdminVisualColorsScreen() {
  const [visualColors, setVisualColors] = useState<VisualColor[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    loadVisualColors();
  }, []);

  const loadVisualColors = async () => {
    try {
      const data = await visualColorService.getAll();
      setVisualColors(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar visual colors');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await visualColorService.delete(id);
      setVisualColors(visualColors.filter(vc => vc.id !== id));
      Alert.alert('Sucesso', 'Visual Color deletado');
    } catch (error) {
      Alert.alert('Erro', 'Falha ao deletar visual color');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gerenciar Visual Colors</Text>
      <Button title="Adicionar Visual Color" onPress={() => router.push('/admin/visual-colors/add')} />
      <FlatList
        data={visualColors}
        keyExtractor={(item) => item.id?.toString() || ''}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.name} - {item.color}</Text>
            <Button title="Editar" onPress={() => router.push(`/admin/visual-colors/${item.id}`)} />
            <Button title="Deletar" onPress={() => handleDelete(item.id!)} color="red" />
          </View>
        )}
      />
    </View>
  );
}