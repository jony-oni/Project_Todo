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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    /*@Override
    public List<Todo> getList() {
        log.info("getList");
        List<Todo> todoList =todoRepository.findAll();
        log.info("getList");
        return todoList;
    }*/

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String pageType = pageRequestDTO.getPageType();
        Pageable pageable = pageRequestDTO.getPageable(Sort.Direction.ASC, "dueDate");

        //Page<Todo> result = todoRepository.findAll(pageable);
        Page<Todo> result = todoRepository.searchAll(types,keyword,pageable,pageType);

        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<TodoDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<TodoDTO> getTodayList(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setDueDate(LocalDate.now());

        // 필터링에 사용될 타입과 키워드를 가져옴
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        // 페이징 정보를 생성
        Pageable pageable = pageRequestDTO.getPageable(Sort.Direction.ASC, "dueDate"); // 정렬 기준으로 dueDate 사용

        // 오늘의 투두 리스트를 가져오는 쿼리 호출
        Page<Todo> result = todoRepository.findByDueDate(pageRequestDTO.getDueDate(), pageable);

        // 결과를 DTO로 변환
        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

        // PageResponseDTO 객체를 생성하여 반환
        return PageResponseDTO.<TodoDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }


    @Override
    public Todo getTodo(Long todoId) {
        log.info("getTodo"+todoId);
        return todoRepository.findById(todoId).get();
    }

    @Override
    public void saveTodo(Todo todo) {
        log.info("Saving Todo: " + todo);
        todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        log.info("Update todo"+todo);
        Todo oldTodo = todoRepository.findById(todo.getTodoId()).get();
        oldTodo.setTitle(todo.getTitle());
        oldTodo.setDescription(todo.getDescription());
        oldTodo.setDueDate(todo.getDueDate());

        oldTodo.setComplete(todo.getComplete());

        todoRepository.save(oldTodo);
    }

    @Override
    public void deleteTodo(Long todoId) {
        log.info("Delete Todo+" + todoId);
        todoRepository.deleteById(todoId);
    }
}
