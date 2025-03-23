package com.AccioJob.VoiceToDoListWithAi.Controller;

import com.AccioJob.VoiceToDoListWithAi.Models.Task;
import com.AccioJob.VoiceToDoListWithAi.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping("/add/task")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }

    @GetMapping("get/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("/update/task/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id,@RequestBody Task task){
        return new ResponseEntity<>(taskService.updateTaskById(id,task), HttpStatus.OK);
    }

    @GetMapping("get-all/task")
    public ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @DeleteMapping("delete/tasks/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.deleteTaskById(id), HttpStatus.OK);
    }
}
