package hu.unideb.inf.todoapp.controller;

import hu.unideb.inf.todoapp.model.Task;
import hu.unideb.inf.todoapp.repository.TaskRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping(path="/tasks")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping(path="/addtask")
    public @ResponseBody String addNewTask (@RequestParam String title
            , @RequestParam Date day, @RequestParam Boolean reminder) {
        Task newTask = new Task();
        newTask.setTaskId(1);
        newTask.setTitle(title);
        newTask.setDay(day);
        newTask.setReminder(reminder);

        taskRepository.save(newTask);
        return "Task saved";
    }

    @PutMapping(path="/tasks/{taskId}")
    public ResponseEntity<Task> toggleReminder(@PathVariable(value = "taskId") Integer taskId, @RequestBody Task task) {
        Task ntask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (ntask.getReminder() == false) {
            ntask.setReminder(true);
        } else {
            ntask.setReminder(false);
        }

        final Task updatedReminder = taskRepository.save(ntask);
        return ResponseEntity.ok(updatedReminder);
    }
}
