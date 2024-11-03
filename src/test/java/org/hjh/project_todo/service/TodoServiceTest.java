package org.hjh.project_todo.service;

import lombok.extern.log4j.Log4j2;
import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
   /* @Autowired
    private TodoService todoService;
    @Test
    public void testRegister(){
        log.info(todoService.getClass().getName());

        TodoDTO todoDTO = TodoDTO.builder()
                .title("Sample title")
                .description("Sample description")
                .complete(false)
                .build();
        Long todo_id = todoService.register(todoDTO);
        log.info("todo_id"+todo_id);
    }
    @Test
    public void testModify(){
        TodoDTO todoDTO = TodoDTO.builder()
                .todo_id(3l)
                .title("md_Sample title")
                .description("md_Sample description")
                .complete(false)
                .build();

        todoService.modify(todoDTO);
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(7)
                .build();
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList()list(pageRequestDTO);
        log.info(responseDTO);
    }*/

}
