package dev.collegue.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.collegue.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String> {

}
