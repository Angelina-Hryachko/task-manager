package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import taskmanager.model.Task;
import taskmanager.model.User;
import taskmanager.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> getAllTasks() {
        User user = getCurrentUser();

        return taskRepository.findAllByUser(user);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task saveTask(Task task) {
        User user = getCurrentUser();
        task.setUser(user);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskForCurrentUser(id);
        taskRepository.delete(task);
    }

    public void toggleTask(Long id) {
        Task task = getTaskForCurrentUser(id);
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userService.findByUsername(username);
    }

    private Task getTaskForCurrentUser(Long id) {
        User user = getCurrentUser();

        return taskRepository.findById(id)
                .filter(task -> task.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
