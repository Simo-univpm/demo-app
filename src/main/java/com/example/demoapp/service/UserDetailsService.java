package com.example.demoapp.service;

import com.example.demoapp.exceptions.UserNotFoundException;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsService {

    private final UsersRepository usersRepository;

    public UserEntity getUserDetails(long id){

        // lettura utente a db
        UserEntity savedUser = usersRepository.findById(id);

        // verifica esistenza utente
        if(savedUser == null) throw new UserNotFoundException("User with id " + id + " not found");

        // estrazione dati utente
        log.info("successfully returned data for user with id {}", id);
        return extractUserData(savedUser);

    }


    private UserEntity extractUserData(UserEntity savedUser){

        UserEntity response = new UserEntity();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setUsername(savedUser.getUsername());
        response.setPasswd(null);

        return response;

    }

}
