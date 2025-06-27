package com.example.mofmof.service;

import com.example.mofmof.controller.form.TasksForm;
import com.example.mofmof.repository.TaskRepository;
import com.example.mofmof.repository.entity.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TaskService {
    @Autowired
    TaskRepository taskRepository;

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
