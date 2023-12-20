package com.example.demoapp.service;


import com.example.demoapp.exceptions.GeneralException;
import com.example.demoapp.model.entities.LastAccessEntity;
import com.example.demoapp.repository.LastAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LastAccessService {

    private final LastAccessRepository lastAccessRepository;

    public void recordLastAccess(long id){

        LastAccessEntity lastAccess = new LastAccessEntity();

        lastAccess.setUserId(id);
        lastAccess.setLastAccess(getCurrentTimestamp());

        lastAccessRepository.saveAndFlush(lastAccess);

    }


    // usato dall'API LastAccessController per permettere di accedere all'ultimo accesso di un utente da postman
    // utilizza userIdRequest come interfaccia verso l'esterno
    public LastAccessEntity getUserLastAccess(long id){

        // torna l'ultimo accesso per l'utente con id passato per parametro
        ArrayList<LastAccessEntity> allUserAccess = lastAccessRepository.findByUserId(id);

        if(allUserAccess.isEmpty()) throw new IndexOutOfBoundsException("user is registered but never logged in !!");

        return allUserAccess.get(allUserAccess.size()-1); // riempie un arraylist con tutti gli accessi e prende l'ultimo con "-1"

    }


    private Timestamp getCurrentTimestamp(){

        long currentTimeMillis = System.currentTimeMillis();                // ottiene il timestamp corrente in millisecondi
        java.util.Date currentDate = new java.util.Date(currentTimeMillis); // crea un oggetto Date con il timestamp corrente
        return new Timestamp(currentDate.getTime());                        // crea un oggetto Timestamp a partire dall'oggetto Date

    }

}
