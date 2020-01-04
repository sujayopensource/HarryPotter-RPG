package com.game.rpg.exceptions;

public class InvalidFactoryUsageException  extends Exception
{
    public InvalidFactoryUsageException(final String message) {
        super(message);
    }

    public InvalidFactoryUsageException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}