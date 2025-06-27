package com.example.mofmof.service;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.repository.TaskRepository;
import com.example.mofmof.repository.entity.Tasks;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    /*
     * レコード全件取得処理
     */
    public List<TasksForm> findAllTasks(String start, String end) throws ParseException {
        String setStart = null;
        String setEnd = null;
        SimpleDateFormat simpleDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isBlank(start)) {
            setStart = "2020-01-01 00:00:00";
        } else {
            setStart = start + " 00:00:00";
        }

        if (StringUtils.isBlank(end)) {
            Date nowDate = new Date();
            setEnd = simpleDefault.format(nowDate);
        } else {
            setEnd = end + " 23:59:59";
        }

        Date startDate = simpleDefault.parse(setStart);
        Date endDate = simpleDefault.parse(setEnd);
        //作成日時を絞り込みかつ、更新日時での降順に設定
        List<Tasks> results = TaskRepository.findAllByLimitDateBetweenOrderByLimitDateAcs(startDate, endDate);
        List<TasksForm> tasks = setTasksForm(results);
        return tasks;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<TasksForm> setTasksForm(List<Tasks> results) {
        List<TasksForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TasksForm task = new TasksForm();
            Tasks result = results.get(i);
            task.setId(result.getId());
            task.setText(result.getContent());
            task.setStatus(result.getStatus());
            task.setLimitDate(result.getLimitDate());
            task.setCreatedDate(result.getCreatedDate());
            task.setUpdatedDate(result.getUpdatedDate());
            tasks.add(task);
        }
        return tasks;
    }
    //タスク削除
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    //ステータス変更 タスクID,ステータスIDをentityに詰めて更新
    public void saveLimit(TasksForm tasksform) {
        Tasks saveLimit = setTasksEntity(tasksform);
        taskRepository.save(saveLimit);
    }

    //Form→entityに詰め変えるメソッド
    private Tasks setTasksEntity(TasksForm reqReport) {
        Tasks task = new Tasks();
        task.setId(reqReport.getId());
        task.setId(reqReport.getStatus());
        task.setUpdatedDate(new Date());
        return task;
    }
}
