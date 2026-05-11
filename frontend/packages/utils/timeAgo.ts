// src/utils/timeAgo.ts

export function timeAgo(date: string): string {
  const now = new Date().getTime();
  const past = new Date(date).getTime();
  const diff = Math.floor((now - past) / 1000);

  const minutes = Math.floor(diff / 60);
  const hours = Math.floor(diff / 3600);
  const days = Math.floor(diff / 86400);

  if (minutes < 1) return "agora mesmo";
  if (minutes < 60) return `há ${minutes} minuto${minutes > 1 ? "s" : ""}`;
  if (hours < 24) return `há ${hours} hora${hours > 1 ? "s" : ""}`;
  return `há ${days} dia${days > 1 ? "s" : ""}`;
}