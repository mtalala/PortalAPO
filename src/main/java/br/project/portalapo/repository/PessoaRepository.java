package br.project.portalapo.repository;

import br.project.portalapo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByAtivoTrue();

    List<Pessoa> findByAtivoFalse();
}