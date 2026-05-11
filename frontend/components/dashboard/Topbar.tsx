import React from "react";
import { Image, SafeAreaView, StyleSheet, View, Text, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";
import { useUser } from "@/packages/context/UserContext";

interface TopbarProps {
  sidebarWidth?: number; // largura da sidebar em px (desktop)
}

export default function Topbar({ sidebarWidth = 256 }: TopbarProps) {
  const { logout, user } = useUser();
  const router = useRouter();

  const handleLogout = () => {
    logout();
    router.replace('/login');
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={[styles.container, { paddingLeft: 16, paddingRight: 16 }]}>
        <View style={styles.logoContainer}>
          <Text style={styles.userText}>{user?.name} ({user?.role})</Text>
          <TouchableOpacity onPress={handleLogout} style={styles.logoutButton}>
            <Text style={styles.logoutText}>Logout</Text>
          </TouchableOpacity>
        </View>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  safeArea: {
    backgroundColor: "#fff",
  },
  container: {
    height: 56, // equivalente a h-14 do tailwind
    flexDirection: "row",
    alignItems: "center",
    borderBottomWidth: 1,
    borderBottomColor: "#E5E7EB", // gray-200
    width: "100%",
    zIndex: 30,
  },
  logoContainer: {
    marginLeft: "auto",
    flexDirection: "row",
    alignItems: "center",
    gap: 8, // espaço entre itens se houver mais elementos
  },
  logo: {
    width: 28,
    height: 28,
  },
  userText: {
    fontSize: 14,
    color: "#374151", // gray-700
  },
  logoutButton: {
    paddingHorizontal: 12,
    paddingVertical: 6,
    backgroundColor: "#DC2626", // red-600
    borderRadius: 4,
  },
  logoutText: {
    color: "#fff",
    fontSize: 14,
  },
});