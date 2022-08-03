package br.com.exactaworks.desafio.dinamica.gabriel.api0.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import br.com.exactaworks.desafio.dinamica.gabriel.api0.models.Expense;

/**
 *
 * @author Gabriel
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense>, QuerydslPredicateExecutor<Expense> {
    Page<Expense> findByIdIn(@Param(value = "id") List<Integer> eventid, Pageable pageable);
    
    Page<Expense> findByNameIn(@Param("name") Collection<String> nomes, Pageable pageable);
    
    @Query(name="Expense.findByName", nativeQuery = true)
    List<Expense> findByName(@Param("name") String nome);
    
    Page<Expense> findAll(Pageable pageable);
    
    @Query(name="Expense.findById", nativeQuery = true)
    @RestResource(exported = false)
    Optional<Expense> findById(@Param("id") long id);
}
