package co.com.poli.psd_todo.controllers;

import co.com.poli.psd_todo.exceptions.TaskException;
import co.com.poli.psd_todo.persistence.entity.Task;
import co.com.poli.psd_todo.persistence.entity.TaskStatus;
import co.com.poli.psd_todo.services.DTO.TaskInDTO;
import co.com.poli.psd_todo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){
        return taskService.createTask(taskInDTO);
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable TaskStatus status) {
        return taskService.findAllByTaskStatus(status);
    }

    @GetMapping
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @PatchMapping("/markTaskFinished/{id}")
    public ResponseEntity<?> findAllByTaskStatus(@PathVariable Long id) {
        Task task = this.taskService.markStatusFinished(id);
        if(Objects.isNull(task)){
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        Task task = this.taskService.deleteTaskById(id);
        if(Objects.isNull(task)){
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

}
