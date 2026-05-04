package br.project.portalapo.repository;

import br.project.portalapo.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

    List<Coordenador> findByMandatoInicioIsNotNull();
}