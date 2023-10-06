package com.example.socialmediabackenddemo.Common.Responses;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicResponse {

    String message;
    boolean successful;
    HttpStatus status;
    public void Success(String msg){
        successful=true;
        message=msg;
        status=HttpStatus.OK;
    }
    public void Error(String err){
        successful=false;
        message=err;
        status=HttpStatus.BAD_REQUEST;
    }
}
