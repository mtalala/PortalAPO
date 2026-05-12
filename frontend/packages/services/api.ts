import { Platform } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

const BASE_URL = "http://54.162.60.154:8080/api";

async function getToken(): Promise<string | null> {
  return AsyncStorage.getItem("token");
}

async function request<T>(
  endpoint: string,
  options: RequestInit = {}
): Promise<T> {
  const token = await getToken();

  const headers: Record<string, string> = {
    "Content-Type": "application/json",
    ...(options.headers as Record<string, string>),
  };

  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  const res = await fetch(`${BASE_URL}${endpoint}`, {
    ...options,
    headers,
  });

  if (!res.ok) {
    const error = await res.text();
    throw new Error(error || "API Error");
  }

  return res.json();
}

export default request;