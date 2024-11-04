package org.hjh.project_todo.repository.search;

import org.hjh.project_todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {
    Page<Todo> searchAll(String[] types, String keyword, Pageable pageable);
}
