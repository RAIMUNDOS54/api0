package br.com.exactaworks.desafio.dinamica.gabriel.api0.uri;


import org.springframework.hateoas.server.EntityLinks;
import org.springframework.stereotype.Component;

@Component
public class ExpenseUri {
    private final EntityLinks entityLinks;
    
    public static final String GASTOS = "/expenses";
    public static final String GASTO = "/{id}";
    public static final String CREATE_GASTO = "/expense";
    
    public ExpenseUri(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }
}
