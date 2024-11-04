package org.hjh.project_todo.service;

import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    /*List<Todo> getList();
    Todo getTodo(Long todo_id);
    void saveTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Long todo_id);*/


    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
    Todo getTodo(Long todoId);
    void saveTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Long todoId);
}
