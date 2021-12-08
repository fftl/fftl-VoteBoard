package fftl.voteboardback.advice.errors;

import lombok.Getter;

@Getter
public class BadRequest extends RuntimeException{
    public BadRequest(String msg){ super(msg);};
}
