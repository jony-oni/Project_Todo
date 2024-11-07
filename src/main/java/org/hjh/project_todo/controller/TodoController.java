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
        // responseDTO.getDtoList()에서 첫 번째 TodoDTO의 dueDate만 가져오기
        LocalDate firstDueDate = responseDTO.getDtoList().isEmpty() ? LocalDate.now() : responseDTO.getDtoList().get(0).getDueDate();
        model.addAttribute("firstDueDate", firstDueDate);

        String description = responseDTO.getDtoList().isEmpty() ? "sample description" : responseDTO.getDtoList().get(0).getDescription();
        model.addAttribute("description", description);

    }


    @GetMapping("/register")
    public void registerGet(@RequestParam(required = false) String pageType, Model model) {
        log.info("controller registerGet");

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPageType(pageType);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }
    @PostMapping("/register")
    public String registerPost(Todo todo,
                               @RequestParam(required = false) String pageType,
                               RedirectAttributes redirectAttributes) {
        log.info("controller registerPost " + todo);
        todoService.saveTodo(todo);

        // pageType이 null이거나 비어 있는 경우 기본값을 설정
        if (pageType == null || pageType.isEmpty()) {
            pageType = "list";  // 기본 페이지 유형으로 설정
        }

        redirectAttributes.addAttribute("pageType", pageType);
        return "redirect:/todo/list?" + pageType;
    }


    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam Long todoId,
                     @RequestParam(required = false) String pageType,
                     Model model ) {
        log.info("controller read" + todoId);
        model.addAttribute("dto", todoService.getTodo(todoId));
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        log.info(pageRequestDTO);
        pageRequestDTO.setPageType(pageType);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }

    @PostMapping("/modify")
    public String modify(Todo todo,
                         @RequestParam(required = false) String returnTo,
                         @RequestParam(required = false) String pageType,
                         RedirectAttributes redirectAttributes) {
        log.info("Modifying Todo: {}", todo);
        todoService.updateTodo(todo);
        redirectAttributes.addAttribute("todoId", todo.getTodoId());

        if (pageType == null || pageType.isEmpty()) {
            pageType = "list"; // 기본 목록으로 설정
        }

        redirectAttributes.addAttribute("pageType", pageType);



        // pageType을 URL 파라미터로 추가하여 리다이렉트
        return "redirect:/todo/list";
    }


    @PostMapping("/remove")
    public String remove(Todo todo, @RequestParam(required = false) String pageType) {
        log.info("controller remove" + todo);
        todoService.deleteTodo(todo.getTodoId());

        return "redirect:/todo/list?pageType=" + pageType;
    }
}
