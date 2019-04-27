package com.mai.pilot_assistent.util;

import java.util.function.Function;

public class Success<T> implements Result<T> {

    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public <R> R fold(Function<T, R> ifSuccess, Function<Failure, R> ifFail) {
        return ifSuccess.apply(value);
    }

    @Override
    public <N> Result<N> map(Function<T, N> mapper) {
        return new Success<>(mapper.apply(value));
    }
}
