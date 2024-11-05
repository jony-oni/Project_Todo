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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

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

//        log.info("pageType=" + pageRequestDTO.getPageType());


        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        /*model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));*/
    }
    @GetMapping("/listtoday")
    public String todayList(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setDueDate(LocalDate.now());  // 오늘 날짜 설정
        model.addAttribute("responseDTO", todoService.getTodayList(pageRequestDTO));
        model.addAttribute("returnTo", "today");
        return "/todo/listtoday";
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

    //그냥 list만 사용할 시 [기존]

    /*@GetMapping({"/read","/modify"})
    public void read(Long todoId,PageRequestDTO pageRequestDTO, Model model){
        log.info("controller read"+todoId);
        model.addAttribute("dto",todoService.getTodo(todoId));
    }*/

   /* @PostMapping("/modify")
    public String modify(Todo todo, PageRequestDTO pageRequestDTO,RedirectAttributes redirectAttributes){
        log.info("controller modify"+todo);
        todoService.updateTodo(todo);
        redirectAttributes.addAttribute("todoId", todo.getTodoId());
        return "redirect:/todo/read";
    }*/

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam Long todoId, @RequestParam(required = false) String returnTo, PageRequestDTO pageRequestDTO, Model model) {
        log.info("controller read" + todoId);
        model.addAttribute("dto", todoService.getTodo(todoId));
        model.addAttribute("returnTo", returnTo); // returnTo 추가
    }

    @PostMapping("/modify")
    public  String modify(Todo todo, @RequestParam(required = false) String returnTo, RedirectAttributes redirectAttributes){
        log.info("controller modify"+todo);
        todoService.updateTodo(todo);
        redirectAttributes.addAttribute("todoId",todo.getTodoId());

        if(returnTo.equals("listtoday")){
            return "redirect:/todo/listtoday"; //list today 에서 수정한 경우
        }else {
            return "redirect:/todo/list"; //list 에서 수정한 경우}
        }

    }


    @PostMapping("/remove")
    public String remove(Todo todo){
        log.info("controller remove"+todo);
        todoService.deleteTodo(todo.getTodoId());
        return "redirect:/todo/list";
    }
}
