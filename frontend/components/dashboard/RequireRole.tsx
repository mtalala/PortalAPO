import { useUser } from "@/packages/context/UserContext";
import { Role } from "@/packages/types/user";
import React, { ReactNode } from "react";
import { Text, View } from "react-native";

interface AuthProps {
  roles: Role[];
  children: ReactNode;
  fallback?: ReactNode;
}

export function RequireRole({
  roles,
  children,
  fallback,
}: AuthProps) {
  const { user } = useUser();

  // Conteúdo padrão se não passar fallback
  const fallbackContent = fallback ?? (
    <View style={{ padding: 16 }}>
      <Text style={{ color: "red", fontWeight: "bold" }}>Acesso negado</Text>
    </View>
  );

  if (!user || !roles.includes(user.role)) return <>{fallbackContent}</>;

  return <>{children}</>;
}