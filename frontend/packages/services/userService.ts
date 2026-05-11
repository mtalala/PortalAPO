import { DEV_USERS } from "@/packages/mocks/users";
import type { Role, User } from "@/packages/types/user";

let currentUser: User | null = null;

const BASE_URL = "http://localhost:8080"; // Spring Boot

/**
 * Inicializa usuário atual
 */
export async function initUser() {
  if (__DEV__) {
    currentUser = DEV_USERS[0];
    return currentUser;
  }

  try {
    const res = await fetch(`${BASE_URL}/api/users/me`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!res.ok) throw new Error("Usuário não encontrado");

    currentUser = await res.json();
    return currentUser;
  } catch (e) {
    currentUser = null;
    return null;
  }
}

/**
 * Retorna usuário atual
 */
export function getUser(): User | null {
  return currentUser;
}

/**
 * Atualiza usuário localmente
 */
export function setUser(user: User) {
  currentUser = user;
}

/**
 * Switch de role (apenas DEV)
 */
export function switchRole(role: Role) {
  if (__DEV__) {
    const found = DEV_USERS.find((u) => u.role === role);
    if (found) currentUser = found;
  }
}