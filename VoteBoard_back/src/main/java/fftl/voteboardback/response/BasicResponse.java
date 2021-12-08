package fftl.voteboardback.response;

import lombok.Builder;
import lombok.Data;

/**
 * response basic Form
 * */

@Data
@Builder
public class BasicResponse<T> {

    private boolean success;
    private T data;
    private String message;

    public BasicResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
        this.message = null;
    }

    public BasicResponse(boolean success, T data, String message) {
        this.success = success;
        this.data =  data;
        this.message = message;
    }
}
