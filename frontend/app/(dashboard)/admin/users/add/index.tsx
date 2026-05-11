import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { adminUserService, AdminUser } from '@/packages/services/adminUserService';

export default function AddUserScreen() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('ALUNO');
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  const handleSubmit = async () => {
    if (!username || !password) {
      Alert.alert('Erro', 'Preencha todos os campos');
      return;
    }

    setLoading(true);
    try {
      await adminUserService.create({ username, password, role, ativo: true });
      Alert.alert('Sucesso', 'Usuário criado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao criar usuário');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Adicionar Usuário</Text>
      <TextInput
        placeholder="Username"
        value={username}
        onChangeText={setUsername}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Role (ADMIN, COORDENADOR, ALUNO)"
        value={role}
        onChangeText={setRole}
        style={{ borderWidth: 1, padding: 10, marginBottom: 20 }}
      />
      <Button title={loading ? 'Criando...' : 'Criar'} onPress={handleSubmit} disabled={loading} />
    </View>
  );
}