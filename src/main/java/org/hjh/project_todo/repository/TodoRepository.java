package org.hjh.project_todo.repository;

import org.hjh.project_todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("select  b from Todo b where b.title like concat('%', :keyword,'%') order by b.todo_id desc")
    Page<Todo> searchAll(String keyword,Pageable pageable);
}
