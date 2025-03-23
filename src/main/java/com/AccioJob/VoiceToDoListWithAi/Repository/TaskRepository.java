package com.AccioJob.VoiceToDoListWithAi.Repository;

import com.AccioJob.VoiceToDoListWithAi.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
