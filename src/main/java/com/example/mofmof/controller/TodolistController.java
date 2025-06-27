package com.example.mofmof.controller;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

public class TodolistController {
    @Autowired
    TaskService taskService;

    //タスク削除機能  タスクIDを取得しDelete
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        taskService.deleteTask(id);
        // Top画面へリダイレクト
        return new ModelAndView("redirect:/");
    }

    //ステータス変更　 タスクID,ステータスIDを取得しUpdate
    @PutMapping("/update/{id}")
    public ModelAndView updateLimit (@PathVariable Integer id,@ModelAttribute("formModel") TasksForm tasks){
        return new ModelAndView("redirect:/");
        // UrlParameterのidを更新するentityにセット
        tasks.setId(id);
        // 編集した投稿を更新
        taskService.saveLimit(tasks);
    }
}
