import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { modalidadeService, Modalidade } from '@/packages/services/modalidadeService';

export default function AdminModalidadesScreen() {
  const [modalidades, setModalidades] = useState<Modalidade[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    loadModalidades();
  }, []);

  const loadModalidades = async () => {
    try {
      const data = await modalidadeService.getAll();
      setModalidades(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar modalidades');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await modalidadeService.delete(id);
      setModalidades(modalidades.filter(m => m.id !== id));
      Alert.alert('Sucesso', 'Modalidade deletada');
    } catch (error) {
      Alert.alert('Erro', 'Falha ao deletar modalidade');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gerenciar Modalidades</Text>
      <Button title="Adicionar Modalidade" onPress={() => router.push('/admin/modalidades/add')} />
      <FlatList
        data={modalidades}
        keyExtractor={(item) => item.id?.toString() || ''}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.name}</Text>
            <Button title="Editar" onPress={() => router.push(`/admin/modalidades/${item.id}`)} />
            <Button title="Deletar" onPress={() => handleDelete(item.id!)} color="red" />
          </View>
        )}
      />
    </View>
  );
}