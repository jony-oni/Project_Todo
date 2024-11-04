package org.hjh.project_todo.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.hjh.project_todo.domain.QTodo;
import org.hjh.project_todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> searchAll(String[] types, String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);
        if((types != null && types.length > 0) && (keyword != null)) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type : types) {
                switch (type){
                    case "t":
                        builder.or(todo.title.contains(keyword));
                        break;
                    case "d":
                        builder.or(todo.description.contains(keyword));
                        break;
                }
            }
            query.where(builder);
        }
        query.where(todo.todoId.gt(0l));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Todo> list = query.fetch();
        long total = query.fetchCount();
        return new PageImpl<>(list, pageable, total);
    }
}
