package br.com.exactaworks.desafio.dinamica.gabriel.api0.models;

import lombok.Data;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date dateRegister;
    private BigDecimal amount;
    @ElementCollection
    private List<String> tags;
}
