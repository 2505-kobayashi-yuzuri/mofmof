package com.example.mofmof.controller;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
public class TasksController {
    @Autowired
    TasksService TasksService;

    /*
     * タスク内容表示処理
     */
    @GetMapping
    public ModelAndView top(@ModelAttribute("start") String start, @ModelAttribute("end")  String end) throws ParseException {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        List<TasksForm> TasksData = TasksService.findAllTasks(start, end);
        //返信の全件取得
        TasksForm tasksForm = new TasksForm();
        mav.addObject("TasksModel",tasksForm);
        // 画面遷移先を指定
        mav.setViewName("/top");
        // タスクデータオブジェクトを保管
        mav.addObject("tasks", TasksData);
        return mav;
    }

}
