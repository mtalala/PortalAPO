// src/type/types.ts

export interface Program {
  id: number;
  name: string;
}

export interface Coordenador {
  id: number;
  name: string;
}

export interface Activity {
  id: number;
  label: string;
  points: number;
}