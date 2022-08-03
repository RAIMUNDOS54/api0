package br.com.exactaworks.desafio.dinamica.gabriel.api0.services;

import br.com.exactaworks.desafio.dinamica.gabriel.api0.models.Expense;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.mock.ExpenseMock;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.repositories.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ExpenseServiceTest {

    @Mock
    ExpenseRepository expensesRepository;
    private ExpenseService expenseService;
    
    @BeforeEach
    public void setup() {
        expenseService = new ExpenseService(expensesRepository);
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
        Mockito.lenient().when(expensesRepository.findAll(pageable)).thenReturn(ExpenseMock.createExpenses());
    }

    @Test
    @DisplayName("Should return all expenses")
    public void getExpenses_happypath() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
        Page<Expense> expenses = expenseService.getExpenses(pageable);
        assertEquals(expenses.getTotalPages(), 1);
        assertEquals(expenses.getNumberOfElements(), 2);
        assertNotNull(expenses);
    }

    @Nested
    @DisplayName("Happy Tests")
    class happycases {

        @Test
        void justtest() {
            String name = "just testing";
            assertEquals(name, "just testing");
        }

        @Test
        void justtest1() {
            String name = "just testin";
            assertEquals(name, "just testin");
        }
    }

    /*@Nested
    @DisplayName("Unhappy Tests")
    class unhappycases {

        @Test
        void justtest() {
            String name = "just testin";
            assertEquals(name, "just testing");
        }

        @Test
        void justtest1() {
            String name = "just testing";
            assertEquals(name, "just testincc");
        }
    }*/
}