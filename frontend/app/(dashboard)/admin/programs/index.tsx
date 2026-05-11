import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { getPrograms, deleteProgram } from '@/packages/services/programService';
import type { Program } from '@/packages/types/types';

export default function AdminProgramsScreen() {
  const [programs, setPrograms] = useState<Program[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    loadPrograms();
  }, []);

  const loadPrograms = async () => {
    try {
      const data = await getPrograms();
      setPrograms(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar programas');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteProgram(id);
      setPrograms(programs.filter(p => p.id !== id));
      Alert.alert('Sucesso', 'Programa deletado');
    } catch (error) {
      Alert.alert('Erro', 'Falha ao deletar programa');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gerenciar Programas</Text>
      <Button title="Adicionar Programa" onPress={() => router.push('/admin/programs/add')} />
      <FlatList
        data={programs}
        keyExtractor={(item) => item.id?.toString() || ''}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.name}</Text>
            <Button title="Editar" onPress={() => router.push(`/admin/programs/${item.id}`)} />
            <Button title="Deletar" onPress={() => handleDelete(item.id!)} color="red" />
          </View>
        )}
      />
    </View>
  );
}