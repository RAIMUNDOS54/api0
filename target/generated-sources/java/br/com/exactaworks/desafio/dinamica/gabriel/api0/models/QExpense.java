package br.com.exactaworks.desafio.dinamica.gabriel.api0.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpense is a Querydsl query type for Expense
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExpense extends EntityPathBase<Expense> {

    private static final long serialVersionUID = 2050539218L;

    public static final QExpense expense = new QExpense("expense");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> dateRegister = createDateTime("dateRegister", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> tags = this.<String, StringPath>createList("tags", String.class, StringPath.class, PathInits.DIRECT2);

    public QExpense(String variable) {
        super(Expense.class, forVariable(variable));
    }

    public QExpense(Path<? extends Expense> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExpense(PathMetadata metadata) {
        super(Expense.class, metadata);
    }

}

