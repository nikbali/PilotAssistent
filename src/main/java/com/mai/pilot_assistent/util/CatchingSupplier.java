package com.mai.pilot_assistent.util;

@FunctionalInterface
public interface CatchingSupplier<T> {

    T get() throws Exception;

}
