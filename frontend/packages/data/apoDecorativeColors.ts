// src/data/apoDecorativeColors.ts
export const APO_DECORATIVE_COLORS = [
  "#1E99A7", // azul petróleo
  "#91277F", // roxo vinho
  "#8C4444", // vermelho terroso
  "#49754D", // verde oliva
  "#2F5D8A", // azul aço
  "#6D3A5F", // ameixa fechada
  "#A0672D", // caramelo escuro
  "#355C5B", // verde petróleo
  "#5A5F73", // cinza azulado
  "#7A4E3B", // marrom quente
];

/**
 * Retorna uma cor determinística a partir de um id
 */
export function getDecorativeColorById(id: string): string {
  let hash = 0;
  for (let i = 0; i < id.length; i++) {
    hash = id.charCodeAt(i) + ((hash << 5) - hash);
  }
  const index = Math.abs(hash) % APO_DECORATIVE_COLORS.length;
  return APO_DECORATIVE_COLORS[index];
}