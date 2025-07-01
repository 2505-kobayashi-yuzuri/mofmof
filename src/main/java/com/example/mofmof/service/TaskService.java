package com.example.mofmof.service;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.repository.TaskRepository;
import com.example.mofmof.repository.entity.Tasks;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository TaskRepository;
    /*
     * レコード全件取得処理
     */
    public List<TasksForm> findAllTasks(String start, String end, Short status, String content) throws ParseException {
        String setStart = null;
        String setEnd = null;
        SimpleDateFormat simpleDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isBlank(start)) {
            setStart = "2020-01-01 00:00:00";
        } else {
            setStart = start + " 00:00:00";
        }

        if (StringUtils.isBlank(end)) {
            setEnd = "2100-01-01 00:00:00";
        } else {
            setEnd = end + " 23:59:59";
        }

        Date startDate = simpleDefault.parse(setStart);
        Date endDate = simpleDefault.parse(setEnd);
        //作成日時を絞り込みかつ、更新日時での降順に設定
        List<Tasks> results = new ArrayList<>();
        if(status != null && content != null) {
            results = TaskRepository.findAllByLimitDateBetweenAndStatusAndContentContainingOrderByLimitDateAsc(startDate, endDate, status, content);
        } else if(status != null && content == null) {
            results = TaskRepository.findAllByLimitDateBetweenAndStatusOrderByLimitDateAsc(startDate, endDate, status);
        } else if(status == null && content != null) {
            results = TaskRepository.findAllByLimitDateBetweenAndContentContainingOrderByLimitDateAsc(startDate, endDate, content);
        } else {
            results = TaskRepository.findAllByLimitDateBetweenOrderByLimitDateAsc(startDate, endDate);
        }
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
            task.setContent(result.getContent());
            task.setStatus(result.getStatus());
            task.setLimitDate(result.getLimitDate());
            tasks.add(task);
        }
        return tasks;
    }
    //タスク削除
    public void deleteTask(int id) {
        TaskRepository.deleteById(id);
    }

    //ステータス変更 タスクID,ステータスIDをentityに詰めて更新
    public void saveTask(TasksForm tasksform) {
        Tasks saveTask = setTasksEntity(tasksform);
        TaskRepository.save(saveTask);
    }

    //Form→entityに詰め変えるメソッド
    private Tasks setTasksEntity(TasksForm reqTask) {
        Tasks task = new Tasks();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setStatus(reqTask.getStatus());
        task.setContent(reqTask.getContent());
        task.setLimitDate(reqTask.getLimitDate());
        return task;
    }

    public TasksForm editTask(Integer id) {
        List<Tasks> results = new ArrayList<>();
        results.add((Tasks) TaskRepository.findById(id).orElse(null));
        List<TasksForm> reports = setTasksForm(results);
        return reports.get(0);
    }

    public void updateStatus(Integer id, Short status) {
        TaskRepository.updateStatusById(id, status);
    }
}
