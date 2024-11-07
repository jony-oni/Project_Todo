package org.hjh.project_todo.service;

import lombok.extern.log4j.Log4j2;
import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;


    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type(null)
                .keyword(null)
                .page(1)
                .size(7)
                .build();
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        log.info(responseDTO);
    }

}
