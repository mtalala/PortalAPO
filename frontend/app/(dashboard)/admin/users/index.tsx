import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { adminUserService, AdminUser } from '@/packages/services/adminUserService';

export default function AdminUsersScreen() {
  const [users, setUsers] = useState<AdminUser[]>([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    try {
      const data = await adminUserService.getAll();
      setUsers(data);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar usuários');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await adminUserService.delete(id);
      setUsers(users.filter(u => u.id !== id));
      Alert.alert('Sucesso', 'Usuário deletado');
    } catch (error) {
      Alert.alert('Erro', 'Falha ao deletar usuário');
    }
  };

  if (loading) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Gerenciar Usuários</Text>
      <Button title="Adicionar Usuário" onPress={() => router.push('/admin/users/add')} />
      <FlatList
        data={users}
        keyExtractor={(item) => item.id?.toString() || ''}
        renderItem={({ item }) => (
          <View style={{ padding: 10, borderBottomWidth: 1 }}>
            <Text>{item.username} - {item.role}</Text>
            <Button title="Editar" onPress={() => router.push(`/admin/users/${item.id}`)} />
            <Button title="Deletar" onPress={() => handleDelete(item.id!)} color="red" />
          </View>
        )}
      />
    </View>
  );
}