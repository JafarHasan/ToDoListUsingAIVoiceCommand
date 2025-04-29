package com.AccioJob.VoiceToDoListWithAi.Service;

import com.AccioJob.VoiceToDoListWithAi.Models.Task;
import com.AccioJob.VoiceToDoListWithAi.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public Task addTask(String operation, String task, String urgency, String datetime) {
        Task newTask = new Task();
        newTask.setOperation(operation);
        newTask.setTask(task);
        newTask.setUrgency(urgency);
        newTask.setDateTime(datetime);
        return taskRepository.save(newTask);
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask=taskRepository.findById(taskId);
        if(optionalTask.isEmpty()){
            throw new RuntimeException("Task Not Found With given ID");
        }
        Task task=optionalTask.get();
        return task;
    }

    public Task updateTaskById(Long id, String operation, String task, String urgency, String datetime) {
        Optional<Task> optionalTask=taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new RuntimeException("Task Not Found With given ID");
        }
        Task existingTask=optionalTask.get();
        existingTask.setOperation(operation);
        existingTask.setTask(task);
        existingTask.setUrgency(urgency);
        existingTask.setDateTime(datetime);
        return taskRepository.save(existingTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public String deleteTaskById(Long id) {
        Optional<Task> optionalTask=taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new RuntimeException("Task Not Found With given ID");
        }
        taskRepository.deleteById(id);
        return "Task has been deleted Successfully";
    }
}
