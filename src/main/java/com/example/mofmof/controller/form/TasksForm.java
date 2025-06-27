package com.example.mofmof.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TasksForm {

    private int id;
    private String text;
//    @NotBlank(message ="コメントを入力してください")
    private short status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}