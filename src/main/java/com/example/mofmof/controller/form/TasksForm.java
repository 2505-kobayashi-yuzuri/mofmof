package com.example.mofmof.controller.form;

import com.example.mofmof.repository.TaskRepository;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TasksForm {
    @Autowired
    TaskRepository TaskRepository;

    @Positive(message ="不正なパラメーター")
    private int id;
    @NotBlank(message ="タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;
    private Short status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "期限を設定してください")
    @FutureOrPresent(message = "無効な日付です")
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}