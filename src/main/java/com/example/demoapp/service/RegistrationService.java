package com.example.demoapp.service;

import com.example.demoapp.exceptions.UserAlreadyExistsException;
import com.example.demoapp.kafka.service.TopicUpdaterService;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.model.rest.RegistrationRequest;
import com.example.demoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final UsersRepository usersRepository;
    private final LastAccessService lastAccessService;
    private final TopicUpdaterService topicUpdaterService;

    public UserEntity registerUser(RegistrationRequest userToRegister) {

        // verifica esistenza user
        String username = userToRegister.getUsername();
        UserEntity savedUser = usersRepository.findByUsername(username);

        if (savedUser != null && savedUser.getUsername().equalsIgnoreCase(username)) {

            throw new UserAlreadyExistsException("Unable to save " + username + " user. User already exists");

        }

        // crea oggetto UserEntity da UserToRegister
        UserEntity userEntity = mapUserEntity(userToRegister);

        // scrive UserEntity a db
        usersRepository.saveAndFlush(userEntity);
        log.info("Successfully saved {} user", username);

        // registra data accesso utente
        lastAccessService.recordLastAccess(userEntity.getId());
        log.info("Saved user access (new user registered)");

        // scrive in kafka
        topicUpdaterService.updatePartition(0, "REGISTER", username);

        return userEntity;

    }


    // TODO: riscrivi con mapstruct
    private UserEntity mapUserEntity(RegistrationRequest userToRegister) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userToRegister.getUsername());
        userEntity.setPasswd(userToRegister.getPasswd());
        userEntity.setFirstName(userToRegister.getFirstName());
        userEntity.setLastName(userToRegister.getLastName());

        return userEntity;

    }

}
