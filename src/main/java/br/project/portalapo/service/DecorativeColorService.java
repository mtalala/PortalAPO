package br.project.portalapo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecorativeColorService {

    private static final List<String> COLORS = List.of(
        "#1E99A7",
        "#91277F",
        "#8C4444",
        "#49754D",
        "#2F5D8A",
        "#6D3A5F",
        "#A0672D",
        "#355C5B",
        "#5A5F73",
        "#7A4E3B"
    );

    public String getColorById(String id) {
        int hash = 0;
        for (char c : id.toCharArray()) {
            hash = c + ((hash << 5) - hash);
        }
        int index = Math.abs(hash) % COLORS.size();
        return COLORS.get(index);
    }
}