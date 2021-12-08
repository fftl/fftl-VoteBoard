package fftl.voteboardback.repository;

import fftl.voteboardback.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
    List<VoteItem> findByVoteId(String voteId);
}
