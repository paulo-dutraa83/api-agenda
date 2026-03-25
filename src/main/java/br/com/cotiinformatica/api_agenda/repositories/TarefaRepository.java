package br.com.cotiinformatica.api_agenda.repositories;

import br.com.cotiinformatica.api_agenda.dtos.TarefaCategoriaDto;
import br.com.cotiinformatica.api_agenda.dtos.TarefaPrioridadeDto;
import br.com.cotiinformatica.api_agenda.entities.Categoria;
import br.com.cotiinformatica.api_agenda.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {

    @Query("""
        SELECT t FROM Tarefa t
        JOIN t.categoria c
        WHERE t.data >= :dataMin AND t.data <= :dataMax
        ORDER BY t.data ASC, t.hora ASC
    """)
    List<Tarefa> findByDatas(
            @Param("dataMin") LocalDate dataMin,
            @Param("dataMax") LocalDate dataMax
    );

    @Query("""
        SELECT c.nome AS categoria, COUNT(t) AS quantidade
        FROM Tarefa t
        JOIN t.categoria c
        WHERE t.data >= :dataMin AND t.data <= :dataMax
        GROUP BY c.nome
        ORDER BY COUNT(t) DESC
    """)
    List<TarefaCategoriaDto> groupByTarefaCategoria(
            @Param("dataMin") LocalDate dataMin,
            @Param("dataMax") LocalDate dataMax
    );

    @Query("""
        SELECT t.prioridade AS prioridade, COUNT(t) AS quantidade
        FROM Tarefa t
        WHERE t.data >= :dataMin AND t.data <= :dataMax
        GROUP BY t.prioridade
        ORDER BY COUNT(t) DESC
    """)
    List<TarefaPrioridadeDto> groupByTarefaPrioridade(
            @Param("dataMin") LocalDate dataMin,
            @Param("dataMax") LocalDate dataMax
    );
}
