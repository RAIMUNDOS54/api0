package br.com.exactaworks.desafio.dinamica.gabriel.api0.mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.models.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseMock {

        public static Page<Expense> createExpenses() {
            List<Expense> expenseList = new ArrayList<>();

            Expense expense1 = new Expense();

            expense1.setId(1);
            expense1.setName("Tarefa 1");
            expense1.setDescription("Descrição da tarefa 1");
            
            Expense expense2 = new Expense();
            
            expense2.setId(2);
            expense2.setName("Tarefa 2");
            expense2.setDescription("Descrição da tarefa 2");
            
            expenseList.add(expense1);
            expenseList.add(expense2);
            
            Page<Expense> pagedResponse = new PageImpl(expenseList);
            
            return pagedResponse;
        }
}