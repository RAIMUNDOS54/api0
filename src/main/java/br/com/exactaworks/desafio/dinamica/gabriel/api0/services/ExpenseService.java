package br.com.exactaworks.desafio.dinamica.gabriel.api0.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.exactaworks.desafio.dinamica.gabriel.api0.dto.ExpenseDto;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.models.Expense;
import br.com.exactaworks.desafio.dinamica.gabriel.api0.repositories.ExpenseRepository;

@Slf4j
@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> list() {
        return expenseRepository.findAll();
    }

    public Page<Expense> getExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return this.getExpenses(pageable);
    }

    public Expense getExpense(long expenseId) {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        return expense.get();
    }

    public Expense saveExpense(ExpenseDto expenseDto) {
        ModelMapper modelMapper = new ModelMapper();
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        return expenseRepository.save(expense);
    }

    public boolean deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
        
        return true;
    }
}
