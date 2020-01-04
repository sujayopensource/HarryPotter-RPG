package com.game.rpg.exceptions;

public class InvalidConfigurationException extends Exception
{
    public InvalidConfigurationException(final String message)
    {
        super(message);
    }

    public InvalidConfigurationException(final String message, final Throwable throwable)
    {
        super(message, throwable);
    }
}
