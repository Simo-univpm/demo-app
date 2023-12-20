package com.example.demoapp.rest;
import com.example.demoapp.model.entities.UserEntity;
import com.example.demoapp.model.rest.LoginRequest;
import com.example.demoapp.model.rest.RegistrationRequest;
import com.example.demoapp.model.rest.UserEditRequest;
import com.example.demoapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-20T13:18:15.490530100+01:00[Europe/Rome]")
@Controller
@RequestMapping("${openapi.aPIDemoApp.base-path:}")
@RequiredArgsConstructor
public class UserController implements UserControllerApi {

    private final NativeWebRequest request;

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final UserDetailsService userDetailsService;
    private final UserEditService userEditService;
    private final DeleteUserService deleteUserService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<UserEntity> registerUser(RegistrationRequest registrationRequest) {

        UserEntity result = registrationService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @Override
    public ResponseEntity<Void> loginUser(LoginRequest loginRequest) {

        loginService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @Override
    public ResponseEntity<UserEntity> getUserDetails(Long id) {

        UserEntity userData = userDetailsService.getUserDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(userData);

    }

    @Override
    public ResponseEntity<UserEditRequest> editUser(UserEditRequest userEditRequest) {

        UserEditRequest response = userEditService.editUser(userEditRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {

        deleteUserService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
