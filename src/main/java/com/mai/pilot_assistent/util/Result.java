package com.mai.pilot_assistent.util;

import java.util.function.Function;

public interface Result<T> {

    <R> R fold(Function<T, R> ifSuccess, Function<Failure, R> ifError);
    <N> Result<N> map(Function<T, N> mapper);

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    T getValue();

    static <T> Result<T> fail(Exception e, String message) {
        return new Failure<>(e, message);
    }

    static <T> Result<T> retrieve(CatchingSupplier<T> from) {
        try {
            T result = from.get();
            return Result.success(result);
        } catch (Exception e) {
            return Result.fail(e, e.getMessage());
        }
    }

}

