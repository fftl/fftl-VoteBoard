package fftl.voteboardback.repository;

import fftl.voteboardback.entity.VoteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteUserRepository extends JpaRepository<VoteUser, Long> {
    List<VoteUser> findByVoteId(String voteId);
    List<VoteUser> findByUserId(String userId);
}
