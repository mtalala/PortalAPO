package br.project.portalapo.repository;

import br.project.portalapo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByRa(String ra);

    List<Aluno> findByCurso(String curso);
}