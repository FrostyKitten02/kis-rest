package si.um.feri.kis.kisrest.execptions;


import org.springframework.http.HttpStatus;

public class ItemNotFound extends CustomRuntimeException {
    public ItemNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
