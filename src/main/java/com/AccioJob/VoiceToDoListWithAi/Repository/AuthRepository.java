package com.AccioJob.VoiceToDoListWithAi.Repository;

import com.AccioJob.VoiceToDoListWithAi.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Users,Long> {
}
