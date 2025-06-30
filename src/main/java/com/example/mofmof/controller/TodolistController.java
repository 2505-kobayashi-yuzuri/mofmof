package com.example.mofmof.controller;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.service.TaskService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TodolistController {
    @Autowired
    TaskService TaskService;
    /*
     * タスク内容表示処理
     */
    @GetMapping
    public ModelAndView top(@ModelAttribute("start") String start, @ModelAttribute("end")  String end,
                            @RequestParam(name = "status", required = false) Short status, @RequestParam(name = "content", required = false) String content ) throws ParseException {
        //requestmappingに変更
        ModelAndView mav = new ModelAndView();
        // 日付の取得
        Date date = new Date();
        SimpleDateFormat simpleDefault = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDefault.format(date);
        // タスクを全件取得
        List<TasksForm> TasksData = TaskService.findAllTasks(start, end, status, content);
        TasksForm tasksForm = new TasksForm();
        mav.addObject("TasksModel",tasksForm);
        //本日の日付の表示
        mav.addObject("Today", today);
        mav.addObject("MapStatus", MapStatus());
        // 画面遷移先を指定
        mav.setViewName("/top");
        // タスクデータオブジェクトを保管
        mav.addObject("tasks", TasksData);
        return mav;
    }

    public Map<Short, String> MapStatus(){
        Map<Short, String> map = new LinkedHashMap<>();
        map.put((short) 1, "未着手");
        map.put((short) 2, "実行中");
        map.put((short) 3, "ステイ中");
        map.put((short) 4, "完了");
        return map;
    }

    //タスク削除機能  タスクIDを取得しDelete
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        TaskService.deleteTask(id);
        // Top画面へリダイレクト
        return new ModelAndView("redirect:/");
    }

    //ステータス変更　 タスクID,ステータスIDを取得しUpdate
    @PutMapping("/update/{id}")
    public ModelAndView updateLimit (@PathVariable Integer id,@ModelAttribute("formModel") TasksForm tasks){
        // UrlParameterのidを更新するentityにセット
        tasks.setId(id);
        // 編集した投稿を更新
        TaskService.saveLimit(tasks);
        return new ModelAndView("redirect:/");
    }
}