package si.um.feri.kis.kisrest.execptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<ExceptionResponse> handleItemNotFound(ItemNotFound ex) {
        return ex.buildResponseEntity();
    }
}
