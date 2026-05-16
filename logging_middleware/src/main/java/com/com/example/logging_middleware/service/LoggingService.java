package com.com.example.logging_middleware.service;

import com.com.example.logging_middleware.model.LogRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoggingService {

    public void log(
            String stack,
            String level,
            String packageName,
            String message
    ) {

        LogRequest req = new LogRequest();

        req.setStack(stack);
        req.setLevel(level);
        req.setPackageName(packageName);
        req.setMessage(message);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJ2YWRkaW51cmkuZGVkZWVweWEyMDIyQHZpdHN0dWRlbnQuYWMuaW4iLCJleHAiOjE3Nzg5Mjc3MDIsImlhdCI6MTc3ODkyNjgwMiwiaXNzIjoiQWZmb3JkIE1lZGljYWwgVGVjaG5vbG9naWVzIFByaXZhdGUgTGltaXRlZCIsImp0aSI6IjhkNDM2OWJhLThhOGMtNDg4MC1iOTdhLWM5ZjQ3MzRiNGEyNiIsImxvY2FsZSI6ImVuLUlOIiwibmFtZSI6ImRlZGVlcHlhIHZhZGRpbnVyaSIsInN1YiI6IjdiNDVlZWU5LTNlYjktNGM4OS1iMTYwLTI3ZDljOWQ1Nzk3MiJ9LCJlbWFpbCI6InZhZGRpbnVyaS5kZWRlZXB5YTIwMjJAdml0c3R1ZGVudC5hYy5pbiIsIm5hbWUiOiJkZWRlZXB5YSB2YWRkaW51cmkiLCJyb2xsTm8iOiIyMm1pczA0OTUiLCJhY2Nlc3NDb2RlIjoiU2ZGdVdnIiwiY2xpZW50SUQiOiI3YjQ1ZWVlOS0zZWI5LTRjODktYjE2MC0yN2Q5YzlkNTc5NzIiLCJjbGllbnRTZWNyZXQiOiJtTVJjdGdLQ3lBZk1hTVNwIn0.IiS1epneCN1VTDzCHxqZIR8w_c-63wJrFxIf92bzxC0");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LogRequest> entity =
                new HttpEntity<>(req, headers);

        String url =
                "http://4.224.186.213/evaluation-service/logs";

        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(
                            url,
                            entity,
                            String.class
                    );

            System.out.println(response.getBody());

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}