package com.example.demoapp.rest;
import com.example.demoapp.model.entities.LastAccessEntity;
import com.example.demoapp.service.LastAccessService;
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
public class LastAccessController implements LastAccessControllerApi {

    private final NativeWebRequest request;

    private final LastAccessService lastAccessService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<LastAccessEntity> getUserLastAccess(Long id) {

        LastAccessEntity last_access = lastAccessService.getUserLastAccess(id);
        return ResponseEntity.status(HttpStatus.OK).body(last_access);

    }
}
