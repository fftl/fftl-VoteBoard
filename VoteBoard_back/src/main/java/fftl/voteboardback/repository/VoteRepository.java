package fftl.voteboardback.repository;

import fftl.voteboardback.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, String> {
    List<Vote> findByUserId(String userId);
}
