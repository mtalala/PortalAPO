import React from "react";
import { Image, SafeAreaView, StyleSheet, View } from "react-native";

interface TopbarProps {
  sidebarWidth?: number; // largura da sidebar em px (desktop)
}

export default function Topbar({ sidebarWidth = 256 }: TopbarProps) {
  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={[styles.container, { paddingLeft: 16, paddingRight: 16 }]}>
        <View style={styles.logoContainer}>
          <Image
            source={require("./assets/logomack.png")} // coloque a logo na pasta assets
            style={styles.logo}
            resizeMode="contain"
          />
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
});