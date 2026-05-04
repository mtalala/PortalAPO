package br.project.portalapo.repository;

import br.project.portalapo.model.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrientadorRepository extends JpaRepository<Orientador, Long> {

    List<Orientador> findByAreaAtuacao(String areaAtuacao);
}