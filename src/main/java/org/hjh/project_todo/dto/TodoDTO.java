package org.hjh.project_todo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {
    private Long todo_id;
    private String member_id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;
    private Boolean complete;
    private LocalDateTime due_date;
}
