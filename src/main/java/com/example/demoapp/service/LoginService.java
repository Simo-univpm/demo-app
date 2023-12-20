package com.example.demoapp.service;

import com.example.demoapp.exceptions.InvalidCredentialsException;
import com.example.demoapp.exceptions.UserNotFoundException;
import com.example.demoapp.kafka.service.TopicUpdaterService;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.model.rest.LoginRequest;
import com.example.demoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UsersRepository usersRepository;
    private final LastAccessService lastAccessService;
    private final TopicUpdaterService topicUpdaterService;


    public void loginUser(LoginRequest userToLogIn){

        // lettura utente a db
        String username = userToLogIn.getUsername();
        UserEntity savedUser = usersRepository.findByUsername(username);

        // verifica esistenza utente
        if(savedUser == null) throw new UserNotFoundException("Username " + username + " not found");

        // verifica correttezza credenziali
        if( ! userToLogIn.getPasswd().equals(savedUser.getPasswd())) throw new InvalidCredentialsException("Password is incorrect");

        // qui utente esite e ha credenziali valide
        log.info("User {} successfully logged in", username);

        // registra data accesso utente
        lastAccessService.recordLastAccess(savedUser.getId());

        // scrive in kafka
        topicUpdaterService.updatePartition(1, "LOGIN", username);

    }

}
