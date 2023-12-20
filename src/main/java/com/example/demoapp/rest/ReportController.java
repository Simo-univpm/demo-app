package com.example.demoapp.rest;


import com.example.demoapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-20T13:18:15.490530100+01:00[Europe/Rome]")
@Controller
@RequestMapping("${openapi.aPIDemoApp.base-path:}")
@RequiredArgsConstructor
public class ReportController implements ReportControllerApi {

    private final NativeWebRequest request;

    private final ReportService reportService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Long> writeReport(Timestamp startDate, Timestamp endDate, String key) {

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(key);

        long result = reportService.writeReport(startDate, endDate, key);
        return ResponseEntity.ok(result);

    }

    @Override
    public ResponseEntity<Void> writeReportVerbose(Timestamp startDate, Timestamp endDate, String key) {

        reportService.writeReportVerbose(startDate, endDate, key);
        return ResponseEntity.ok().build();

    }
}
