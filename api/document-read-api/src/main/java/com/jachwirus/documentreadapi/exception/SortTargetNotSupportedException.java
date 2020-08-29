package com.jachwirus.documentreadapi.exception;

public class SortTargetNotSupportedException extends RuntimeException{
    public SortTargetNotSupportedException(String sortTarget) {
        super(String.format("sort by '%s' is not supported.", sortTarget));
    }
}
