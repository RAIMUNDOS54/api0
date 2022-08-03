package br.com.exactaworks.desafio.dinamica.gabriel.api0.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ExpenseDto {
    private long id;
    private String name;
    private String description;
    private Date dateregister;
    private BigDecimal amount;
    private List<String> tags;
    private String createdDate;
}
