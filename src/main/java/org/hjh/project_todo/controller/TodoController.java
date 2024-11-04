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
    public void list(Model model) {
        log.info("controller list");
        model.addAttribute("todoList", todoService.getList());
        //model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
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
    public void read(Long todo_id,Model model){
        log.info("controller read"+todo_id);
        model.addAttribute("dto",todoService.getTodo(todo_id));
    }

    @PostMapping("/modify")
    public String modify(Todo todo, RedirectAttributes redirectAttributes){
        log.info("controller modify"+todo);
        todoService.updateTodo(todo);
        redirectAttributes.addAttribute("todo_id", todo.getTodo_id());
        return "redirect:/todo/read";
    }
    @PostMapping("/remove")
    public String remove(Todo todo){
        log.info("controller remove"+todo);
        todoService.deleteTodo(todo.getTodo_id());
        return "redirect:/todo/list";
    }
}
