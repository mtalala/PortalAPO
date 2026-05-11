import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { getApos, deleteApo } from '@/packages/services/apoService';
import type { Apo } from '@/packages/types/apo';

export default function AdminAposScreen() {
  const [apos, setApos] = useState<Apo[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    loadApos();
  }, []);

  const loadApos = async () => {
    try {
      const data = await getApos();
      setApos(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar APOs');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteApo(id);
      setApos(apos.filter(a => a.id !== id));
      Alert.alert('Sucesso', 'APO deletado');
    } catch (error) {
      Alert.alert('Erro', 'Falha ao deletar APO');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gerenciar APOs</Text>
      <Button title="Adicionar APO" onPress={() => router.push('/formulario')} />
      <FlatList
        data={apos}
        keyExtractor={(item) => item.id?.toString() || ''}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.title}</Text>
            <Button title="Editar" onPress={() => router.push(`/admin/apos/${item.id}`)} />
            <Button title="Deletar" onPress={() => handleDelete(item.id!)} color="red" />
          </View>
        )}
      />
    </View>
  );
}