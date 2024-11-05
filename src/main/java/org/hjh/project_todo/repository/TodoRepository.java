package org.hjh.project_todo.repository;

import org.hjh.project_todo.domain.Todo;
import org.hjh.project_todo.repository.search.TodoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> , TodoSearch {
   /* @Query("select b from Todo b where b.title like concat('%',:keyword,'%') order by b.todo_id desc")
    Page<Todo>searchAll(String keyword, Pageable pageable)*/;
    @Query("SELECT t FROM Todo t WHERE t.dueDate = :dueDate")
    Page<Todo> findByDueDate(LocalDate dueDate, Pageable pageable);





}
