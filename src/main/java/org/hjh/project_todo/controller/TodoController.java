package org.hjh.project_todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.dto.PageRequestDTO;
import org.hjh.project_todo.dto.PageResponseDTO;
import org.hjh.project_todo.dto.TodoDTO;
import org.hjh.project_todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO,Model model) {
        log.info("controller list");
        //model.addAttribute("todoList", todoService.getList());

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        /*model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));*/
    }
    @GetMapping("/register")
    public void registerGet(){

        log.info("controller registerGet");
    }
    @PostMapping("/register")
    public String registerPost(Todo todo){
        log.info("controller registerPost"+todo);
        todoService.saveTodo(todo);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long todoId,PageRequestDTO pageRequestDTO, Model model){
        log.info("controller read"+todoId);
        model.addAttribute("dto",todoService.getTodo(todoId));
    }

    @PostMapping("/modify")
    public String modify(Todo todo, PageRequestDTO pageRequestDTO,RedirectAttributes redirectAttributes){
        log.info("controller modify"+todo);
        todoService.updateTodo(todo);
        redirectAttributes.addAttribute("todoId", todo.getTodoId());
        return "redirect:/todo/read";
    }
    @PostMapping("/remove")
    public String remove(Todo todo){
        log.info("controller remove"+todo);
        todoService.deleteTodo(todo.getTodoId());
        return "redirect:/todo/list";
    }
}
