package org.hjh.project_todo.service;

import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;
import org.hjh.project_todo.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    /*@Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("getlist");

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("todo_id");

        Page<Todo> result = todoRepository.findAll(pageable);
        //Page<Todo> result = todoRepository.searchAll(types,keyword,pageable);

        List<TodoDTO> dtoList=result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toUnmodifiableList());

        return PageResponseDTO.<TodoDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }*/

    @Override
    public List<Todo> getList() {
        log.info("getList");
        List<Todo> todoList =todoRepository.findAll();
        log.info("getList");
        return todoList;
    }

    @Override
    public Todo getTodo(Long todo_id) {
        log.info("getTodo"+todo_id);
        return todoRepository.findById(todo_id).get();
    }

    @Override
    public void saveTodo(Todo todo) {
        log.info("Saving Todo: " + todo);
        todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        log.info("Update todo"+todo);
        Todo oldTodo = todoRepository.findById(todo.getTodo_id()).get();
        oldTodo.setTitle(todo.getTitle());
        oldTodo.setDescription(todo.getDescription());
        oldTodo.setDue_date(todo.getDue_date());

        oldTodo.setComplete(todo.getComplete());
        
        todoRepository.save(oldTodo);
    }

    @Override
    public void deleteTodo(Long todo_id) {
        log.info("Delete Todo+" + todo_id);
        todoRepository.deleteById(todo_id);
    }
}
