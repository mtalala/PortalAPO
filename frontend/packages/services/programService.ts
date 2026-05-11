import request from "@/packages/services/api";
import type { Program } from "@/packages/types/types";

export async function getPrograms(): Promise<Program[]> {
  return request<Program[]>("/programs");
}

export async function createProgram(program: Omit<Program, 'id'>): Promise<Program> {
  return request<Program>("/programs", {
    method: 'POST',
    body: JSON.stringify(program),
  });
}

export async function updateProgram(id: number, program: Program): Promise<Program> {
  return request<Program>(`/programs/${id}`, {
    method: 'PUT',
    body: JSON.stringify(program),
  });
}

export async function deleteProgram(id: number): Promise<void> {
  return request<void>(`/programs/${id}`, {
    method: 'DELETE',
  });
}
