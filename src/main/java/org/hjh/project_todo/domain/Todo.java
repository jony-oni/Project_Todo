package org.hjh.project_todo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Data
@Table(name="todos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    private String memberId;
    private String title;
    private String description;
    private Boolean complete;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public void change(@NotEmpty String title, @NotEmpty String description) {
        this.title = title;
        this.description = description;
    }
    @PrePersist
    public void prePersist(){
        this.complete = Boolean.FALSE;
    }
}
