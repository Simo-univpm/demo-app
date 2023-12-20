package com.example.demoapp.service;

import com.example.demoapp.exceptions.UserNotFoundException;
import com.example.demoapp.kafka.service.TopicUpdaterService;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.model.rest.UserEditRequest;
import com.example.demoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEditService {

    private final UsersRepository usersRepository;
    private final TopicUpdaterService topicUpdaterService;

    // ci permette di cambiare nome e cognome dell'utente associato all'username speficifato nel body
    public UserEditRequest editUser(UserEditRequest userEditRequest){

        String username = userEditRequest.getUsername();
        UserEntity userToEdit = usersRepository.findByUsername(username);

        if(userToEdit == null) throw new UserNotFoundException("User with username " + username + " not found");

        // TODO: riscrivi con mapstruct
        // aggiorna campi
        userToEdit.setFirstName(userEditRequest.getFirstName());
        userToEdit.setLastName(userEditRequest.getLastName());

        UserEditRequest response = mapUserEditRequest(userToEdit);

        // scrive utente a db
        usersRepository.saveAndFlush(userToEdit);
        log.info("Edit user {} successful", username);

        // scrive in kafka
        topicUpdaterService.updatePartition(2, "EDIT", username);

        return response;

    }

    private UserEditRequest mapUserEditRequest(UserEntity userToEdit){

        UserEditRequest response = new UserEditRequest();

        response.setUsername(userToEdit.getUsername());
        response.setFirstName(userToEdit.getFirstName());
        response.setLastName(userToEdit.getLastName());

        return response;

    }

}
