package server.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerResponse {

    private ServerResponse() {

    }

    public static ResponseEntity error() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity positive(T entity) {
        return new ResponseEntity(entity, new HttpHeaders(), HttpStatus.OK);
    }


}
