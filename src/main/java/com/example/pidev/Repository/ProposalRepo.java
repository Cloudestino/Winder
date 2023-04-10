package com.example.pidev.Repository;

import com.example.pidev.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepo extends JpaRepository<Proposal, Integer> {
}
