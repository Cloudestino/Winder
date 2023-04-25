package com.example.pidev.Service;

import com.example.pidev.entity.DictionnaireBadWords;
import com.example.pidev.entity.Reclamation;

import java.util.List;

public interface IBadWordService {
    List<DictionnaireBadWords> retrieveAllBadWord();

    DictionnaireBadWords addBadWord (DictionnaireBadWords r);

    public void updateBadWord(DictionnaireBadWords b, Long idb);

    void deleteBadWord(long idB);
}
