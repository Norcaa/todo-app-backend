package hu.unideb.inf.todoapp.repository;

import hu.unideb.inf.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t")
    public List<Task> findAll();

    @Modifying
    @Query("update Task t set t.reminder = :reminder where t.taskId = :id")
    void updateReminder(@Param(value = "id") int id, @Param(value = "reminder") Boolean reminder);
}
