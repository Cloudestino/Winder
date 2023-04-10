package com.example.pidev.Service;

import com.example.pidev.entity.Proposal;

import java.util.List;

public interface IProposalService {
    List<Proposal> retrieveAllProposal();
    Proposal addProposal(Proposal p);
    Proposal retrieveProposal (Integer idProposal);
    Proposal updateProposal (Proposal p);
    void deleteProposal (Integer idProposal);

}