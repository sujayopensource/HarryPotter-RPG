package com.game.rpg.common;

import java.util.*;

public class OutputDisplay
{
    /*Buffers messages upto some count. Required if:
            a) Recent messages need to be written out again
            b) UT support to verify messages written to console.

      Note: This is a very Simple buffer thats sufficient for this purpose
     */
    private static final List<String> m_outputBuffer = new ArrayList<>();
    private static  int BUFFER_SIZE = 20;

    public static void writeOutput(final String output)
    {
        clearBuffer();
        m_outputBuffer.add(output);
        System.out.println(output);
    }

    public static void writeOutputWithoutNewLine(final String output)
    {
        clearBuffer();
        m_outputBuffer.add(output);
        System.out.print(output);
    }

    public static void writeError(final String output)
    {
        clearBuffer();
        m_outputBuffer.add(output);
        System.err.println(output);
    }

    private static void clearBuffer()
    {
        if(m_outputBuffer.size() >= BUFFER_SIZE)
        {
            m_outputBuffer.clear();
        }
    }

    public static void forceClearBuffer()
    {
        m_outputBuffer.clear();
    }

    public static List<String> getOutputBuffer()
    {
        return m_outputBuffer;
    }

    public static void setBufferSize(int size)
    {
        BUFFER_SIZE = size;
    }
}
