package com.AccioJob.VoiceToDoListWithAi.Controller;

import com.AccioJob.VoiceToDoListWithAi.Models.Task;
import com.AccioJob.VoiceToDoListWithAi.Models.Users;
import com.AccioJob.VoiceToDoListWithAi.Repository.UserRepository;
import com.AccioJob.VoiceToDoListWithAi.Service.NotificationService;
import com.AccioJob.VoiceToDoListWithAi.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Autowired
    TaskController(TaskService taskService,
                   NotificationService notificationService,
                   UserRepository userRepository){
        this.taskService=taskService;
        this.notificationService=notificationService;
        this.userRepository=userRepository;
    }

    @PostMapping("/add-task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task createdTask = taskService.addTask(task.getOperation(), task.getTask(), task.getUrgency(), task.getDateTime());

        if (createdTask != null && task.getUserId() != null) {
            Optional<Users> user = userRepository.findById(task.getUserId());
            user.ifPresent(userData -> notificationService.sendTaskNotification(task, userData, false));
        }

        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = Optional.ofNullable(taskService.getTaskById(id));
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/task/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id,@RequestBody Task task) {
        Task updatedTask = taskService.updateTaskById(id, task.getOperation(), task.getTask(), task.getUrgency(), task.getDateTime().toString());

        if (updatedTask != null) {
            if (task.getUserId() != null) {
                Optional<Users> user = userRepository.findById(task.getUserId());
                user.ifPresent(userData -> notificationService.sendTaskNotification(task, userData, true));
            }
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
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
