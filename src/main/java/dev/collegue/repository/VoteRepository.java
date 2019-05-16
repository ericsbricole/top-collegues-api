package dev.collegue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.collegue.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


}
