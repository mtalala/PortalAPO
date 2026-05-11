import { Slot, usePathname } from "expo-router";
import React, { useState } from "react";
import {
  ScrollView,
  StyleSheet,
  View,
  useWindowDimensions,
} from "react-native";

import NewRequestFab from "@/components/dashboard/NewRequestFab";
import Sidebar from "@/components/dashboard/Sidebar";
import Topbar from "@/components/dashboard/Topbar";

export default function DashboardLayout() {
  const [collapsed, setCollapsed] = useState(false);
  const pathname = usePathname();

  const { width } = useWindowDimensions();
  const isMobile = width < 768;

  const sidebarWidth = collapsed ? 60 : 256;
  const showFab = !pathname.includes("formulario");

  return (
    <View style={styles.container}>
      {/* Sidebar */}
      <Sidebar collapsed={collapsed} setCollapsed={setCollapsed} />

      {/* Conteúdo principal */}
      <View style={styles.content}>
        {/* Topbar sempre ocupa 100% */}
        <Topbar />

        {/* ScrollView com marginLeft apenas em desktop/web */}
        <ScrollView
          contentContainerStyle={[
            styles.main,
            !isMobile && { marginLeft: sidebarWidth },
          ]}
        >
          <Slot />
        </ScrollView>

        {showFab && <NewRequestFab />}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "row",
    backgroundColor: "#f9fafb",
  },
  content: {
    flex: 1,
  },
  main: {
    flexGrow: 1,
    padding: 16,
  },
});