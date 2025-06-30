package com.example.mofmof.controller.form;

import com.example.mofmof.repository.TaskRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
    private Integer status;
    @NotBlank(message = "期限を設定してください")
    @Past(message = "無効な日付です")
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}