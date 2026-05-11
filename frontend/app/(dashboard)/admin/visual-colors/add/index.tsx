import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import { visualColorService, VisualColor } from '@/packages/services/visualColorService';

export default function AddVisualColorScreen() {
  const [name, setName] = useState('');
  const [color, setColor] = useState('');
  const [description, setDescription] = useState('');
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  const handleSubmit = async () => {
    if (!name || !color) {
      Alert.alert('Erro', 'Preencha nome e cor');
      return;
    }

    setLoading(true);
    try {
      await visualColorService.create({ name, color, description });
      Alert.alert('Sucesso', 'Visual Color criado');
      router.back();
    } catch (error) {
      Alert.alert('Erro', 'Falha ao criar visual color');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Adicionar Visual Color</Text>
      <TextInput
        placeholder="Nome"
        value={name}
        onChangeText={setName}
        style={{ borderWidth: 1, padding: 10, marginBottom: 10 }}
      />
      <TextInput
        placeholder="Cor (ex: #FF0000)"
        value={color}
        onChangeText={setColor}
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