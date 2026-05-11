import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, Alert, Switch } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';
import { adminUserService, AdminUser } from '@/packages/services/adminUserService';

export default function EditUserScreen() {
  const { id } = useLocalSearchParams();
  const [user, setUser] = useState<AdminUser | null>(null);
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  useEffect(() => {
    loadUser();
  }, [id]);

  const loadUser = async () => {
    if (!id) return;
    try {
      const data = await adminUserService.getAll();
      const foundUser = data.find(u => u.id === parseInt(id));
      if (foundUser) setUser(foundUser);
    } catch (error) {
      Alert.alert('Erro', 'Falha ao carregar usuário');
    }
  };

  const handleSubmit = async () => {
    if (!user) return;

    setLoading(true);
    try {
      await adminUserService.update(user.id!, user);
      Alert.alert('Sucesso', 'Usuário atualizado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao atualizar usuário');
    } finally {
      setLoading(false);
    }
  };

  if (!user) return <Text>Carregando...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Editar Usuário</Text>
      <TextInput
        placeholder="Username"
        value={user.username}
        onChangeText={(text) => setUser({ ...user, username: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Password (deixe vazio para manter)"
        value={user.password}
        onChangeText={(text) => setUser({ ...user, password: text })}
        secureTextEntry
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Role"
        value={user.role}
        onChangeText={(text) => setUser({ ...user, role: text })}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <View style={{ flexDirection: 'row', alignItems: 'center', marginBottom: 20 }}>
        <Text>Ativo: </Text>
        <Switch value={user.ativo} onValueChange={(value) => setUser({ ...user, ativo: value })} />
      </View>
      <Button title={loading ? 'Atualizando...' : 'Atualizar'} onPress={handleSubmit} disabled={loading} />
    </View>
  );
}