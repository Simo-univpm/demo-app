package com.example.demoapp.service;

import com.example.demoapp.exceptions.UserNotFoundException;
import com.example.demoapp.kafka.service.TopicUpdaterService;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserService {

    private final UsersRepository usersRepository;
    private final TopicUpdaterService topicUpdaterService;

    public void deleteUser(long id){

        UserEntity userToDelete = usersRepository.findById(id);

        if(userToDelete == null) throw new UserNotFoundException("User with id " + id + " not found");

        // scrive in kafka
        topicUpdaterService.updatePartition(3, "DELETE", userToDelete.getUsername());

        log.info("user with id {} deleted", id);
        usersRepository.deleteById(id);

    }

}
