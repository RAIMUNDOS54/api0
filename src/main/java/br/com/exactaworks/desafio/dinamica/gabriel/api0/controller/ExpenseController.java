package br.com.exactaworks.desafio.dinamica.gabriel.api0.controller;

import java.util.List;

import javax.annotation.Resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.exactaworks.desafio.dinamica.gabriel.api0.dto.ExpenseDto;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.models.Expense;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.services.ExpenseService;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.uri.ExpenseUri;

@Slf4j
@RepositoryRestController
@RequestMapping("/expenses/")
@RequiredArgsConstructor
@RepositoryRestResource(path = ExpenseUri.GASTOS, collectionResourceRel = "expenses")
public class ExpenseController {

    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final ExpenseService expenseService;

    @GetMapping(path = ExpenseUri.GASTOS)
    public ResponseEntity<?> getExpenses(ExpenseDto expenseDto, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        log.info("ExpenseController: " + expenseDto);
        Page<Expense> events = expenseService.getExpenses(pageable);
        PagedModel<?> resource
                = pagedResourcesAssembler.toModel(events, resourceAssembler);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(path = ExpenseUri.GASTO)
    public ResponseEntity<?> getExpense(@PathVariable("id") int expenseId,
            Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        try {
            log.info("ExpenseController::: " + expenseId);
            Expense expense = expenseService.getExpense(expenseId);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(
                    this.getClass()).getExpense(expenseId, pageable, resourceAssembler)
            ).withSelfRel();
            Link allExpensesLink = WebMvcLinkBuilder.linkTo(this.getClass()
            ).slash("/expenses").withRel("all expenses");
            EntityModel<Expense> entityModel = EntityModel.of(expense);
            entityModel.add(selfLink, allExpensesLink);
            return ResponseEntity.ok(entityModel);
        } catch (RuntimeException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Expense Not Found", exc);
        }
    }

    @PostMapping(path = ExpenseUri.CREATE_GASTO)
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDto expenseDto, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        log.info("ExpenseController: " + expenseDto);
        Expense events = expenseService.saveExpense(expenseDto);
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(
                this.getClass()).createExpense(expenseDto, pageable,
                resourceAssembler)).withSelfRel();
        Link allExpensesLink = WebMvcLinkBuilder.linkTo(this.getClass()
        ).slash("/expenses").withRel("all expenses");
        EntityModel<Expense> expenseResource = EntityModel.of(events);
        expenseResource.add(selfLink, allExpensesLink);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomResponseHeader", "CustomValue");
        return new ResponseEntity<EntityModel<Expense>>(expenseResource,
                responseHeaders, HttpStatus.CREATED);
    }
    /*@Resource(name = "expenseService")
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expense/{id}")
    public Expense getExpense(@PathVariable int id) {
        return expenseService.list().get(id);
    }

    @PostMapping("/expense")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/expense/{id}")
    public boolean deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }*/
}
