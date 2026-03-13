package br.com.cotiinformatica.api_agenda.repositories;

import br.com.cotiinformatica.api_agenda.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {

}
