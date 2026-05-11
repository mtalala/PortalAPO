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
    } catch {
      Alert.alert('Erro', 'Falha ao carregar visual colors');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: string) => {
    try {
      await visualColorService.delete(id);

      setVisualColors((prev) =>
        prev.filter((vc) => vc.id !== id)
      );

      Alert.alert('Sucesso', 'Visual Color deletado');
    } catch {
      Alert.alert('Erro', 'Falha ao deletar visual color');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>
        Gerenciar Visual Colors
      </Text>

      <Button
        title="Adicionar Visual Color"
        onPress={() => router.push('/admin/visual-colors/add')}
      />

      <FlatList
        data={visualColors}
        keyExtractor={(item, index) => item.id ?? String(index)}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>
              {item.name} - {item.color}
            </Text>

            <View style={{ flexDirection: 'row', gap: 10 }}>
              <Button
                title="Editar"
                onPress={() =>
                  router.push(`/admin/visual-colors/${item.id}`)
                }
              />

              <Button
                title="Deletar"
                color="red"
                onPress={() => {
                  if (!item.id) return;
                  handleDelete(item.id);
                }}
              />
            </View>
          </View>
        )}
      />
    </View>
  );
}