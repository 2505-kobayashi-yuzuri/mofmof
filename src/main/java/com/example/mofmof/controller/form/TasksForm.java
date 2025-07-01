package com.example.mofmof.controller.form;

import com.example.mofmof.repository.TaskRepository;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TasksForm {
    @Autowired
    TaskRepository TaskRepository;

    private int id;
    @NotBlank(message ="タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;
    private Short status;
    @NotNull(message = "期限を設定してください")
    @Future(message = "無効な日付です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}