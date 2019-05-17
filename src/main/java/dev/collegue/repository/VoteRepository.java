package dev.collegue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.collegue.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

	public Optional<List<Vote>> findByTargetEmail(String target);

}
