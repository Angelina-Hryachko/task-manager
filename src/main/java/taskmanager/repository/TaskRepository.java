package taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import taskmanager.model.Task;
import taskmanager.model.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.id = :id")
    Task getTaskById(@Param("id") Long id);

    @Query("SELECT t FROM Task t WHERE t.user = :user")
    List<Task> findAllByUser(@Param("user") User user);
}
