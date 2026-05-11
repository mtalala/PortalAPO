// app/_layout.tsx
import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import { Stack } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import 'react-native-reanimated';

import { useColorScheme } from '@/hooks/use-color-scheme';
import { UserProvider } from '@/packages/context/UserContext';

export default function RootLayout() {
  const colorScheme = useColorScheme();

  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <UserProvider>
        <Stack>
          {/* Aqui você registra apenas telas reais, não layouts */}
          {/* Exemplo: <Stack.Screen name="(dashboard)/pendentes/page" /> */}
        </Stack>
        <StatusBar style="auto" />
      </UserProvider>
    </ThemeProvider>
  );
}