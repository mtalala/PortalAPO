import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { createProgram } from '@/packages/services/programService';
import type { Program } from '@/packages/types/types';

export default function AddProgramScreen() {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  const handleSubmit = async () => {
    if (!name) {
      Alert.alert('Erro', 'Preencha o nome');
      return;
    }

    setLoading(true);
    try {
      await createProgram({ name, description });
      Alert.alert('Sucesso', 'Programa criado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao criar programa');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Adicionar Programa</Text>
      <TextInput
        placeholder="Nome"
        value={name}
        onChangeText={setName}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Descrição"
        value={description}
        onChangeText={setDescription}
        style={{ borderWidth: 1, padding: 10, marginBottom: 20 }}
      />
      <Button title={loading ? 'Criando...' : 'Criar'} onPress={handleSubmit} disabled={loading} />
    </View>
  );
}