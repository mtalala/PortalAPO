import React from 'react';
import { View, Text, Button } from 'react-native';
import { useRouter } from 'expo-router';

export default function AdminPanelScreen() {
  const router = useRouter();

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Painel de Administração</Text>
      <Button title="Gerenciar Usuários" onPress={() => router.push('/admin/users')} />
      <Button title="Gerenciar Visual Colors" onPress={() => router.push('/admin/visual-colors')} />
      <Button title="Gerenciar Modalidades" onPress={() => router.push('/admin/modalidades')} />
      <Button title="Gerenciar Programas" onPress={() => router.push('/admin/programs')} />
      <Button title="Gerenciar APOs" onPress={() => router.push('/admin/apos')} />
    </View>
  );
}