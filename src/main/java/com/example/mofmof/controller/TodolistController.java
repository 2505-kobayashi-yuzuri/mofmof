package com.example.mofmof.controller;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.service.TaskService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @GetMapping("/")
    public ModelAndView top(@ModelAttribute("start") String start, @ModelAttribute("end") String end,
                            @RequestParam(name = "status", required = false) Short status, @RequestParam(name = "content", required = false) String content ) throws ParseException {
        //requestmappingに変更
        ModelAndView mav = new ModelAndView();
        // 日付の取得
        Date date = new Date();
        SimpleDateFormat simpleDefault = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDefault.format(date);
        // タスクを全件取得
        List<TasksForm> TasksData = TaskService.findAllTasks(start, end, status, content);
//       TasksForm tasksForm = new TasksForm();
//       mav.addObject("TasksModel",tasksForm);
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
    @PutMapping("/updateStatus/{id}")
    public ModelAndView updateStatus (@PathVariable Integer id, @RequestParam(name = "status", required = false) Short status){
        // 編集した投稿を更新
        TaskService.updateStatus(id, status);
        return new ModelAndView("redirect:/");
    }

    //新規タスク画面表示
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        TasksForm tasksForm = new TasksForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", tasksForm);
        return mav;
    }
    //新規タスク追加処理
    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute("formModel") @Valid TasksForm tasksForm, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("/new");
            return mav;
        }
        // 投稿をテーブルに格納
        TaskService.saveTask(tasksForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * タスク編集画面に遷移するメソッド
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView();
        //idに結びついている投稿内容を取得
        TasksForm tasksForm= TaskService.editTask(id);
        //取得した投稿内容を加え
        mav.addObject("formModel", tasksForm);
        //画面遷移とともに投稿内容を送る
        mav.setViewName("/edit");
        return mav;
    }
    /*
     *　編集したタスクを受け取るメソッド
     */
    @PutMapping("/update/{id}")
    public ModelAndView saveTask (@PathVariable Integer id,
                                       @ModelAttribute("formModel") @Validated TasksForm task, BindingResult result){
                if (result.hasErrors()) {
                    return new ModelAndView("/edit");
                }
                // UrlParameterのidを更新するentityにセット
                task.setId(id);
                // 編集した投稿を更新
                TaskService.saveTask(task);
//         rootへリダイレクト
                return new ModelAndView("redirect:/");
            }
}