package com.mai.pilot_assistent.util;

import java.util.function.Function;

public class Failure<T> implements Result<T> {

    private final Exception exception;
    private final String message;

    Failure(Exception exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    @Override
    public <R> R fold(Function<T, R> ifSuccess, Function<Failure, R> ifFail) {
        return ifFail.apply(this);
    }
    @Override
    public <N> Result<N> map(Function<T, N> mapper) {
        return new Failure<>(null,"");
    }

    public Exception getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

}
