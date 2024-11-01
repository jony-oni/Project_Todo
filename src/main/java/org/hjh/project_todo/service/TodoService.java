package org.hjh.project_todo.service;

import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;

public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO readOne(Long todo_id);
    void modify(TodoDTO todoDTO);
    void remove(Long todo_id);
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
