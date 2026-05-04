package br.project.portalapo.repository;

import br.project.portalapo.model.MembroComite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroComiteRepository extends JpaRepository<MembroComite, Long> {

    List<MembroComite> findByFuncao(String funcao);
}