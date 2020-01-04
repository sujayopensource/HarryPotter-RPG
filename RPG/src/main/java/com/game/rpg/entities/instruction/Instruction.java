package com.game.rpg.entities.instruction;

import com.game.rpg.common.OutputDisplay;

import java.io.Serializable;

public class Instruction implements Serializable
{
    private static final long serialVersionUID = -299482008790407L;
    private String m_font;
    private String m_color;
    private String m_text;

    private Instruction(final InstructionBuilder builder)
    {
        m_text = builder.text;
        m_font = builder.font;
        m_color = builder.color;

    }
    public static class InstructionBuilder
    {
        //optional
        private String font;
        private String color;
        //mandatory
        private String text;

        public InstructionBuilder(String text)
        {
            this.text = text;
        }

        public InstructionBuilder color(String color)
        {
            this.color = color;
            return this;
        }

        public InstructionBuilder font(String font)
        {
            this.font = font;
            return this;
        }

        public Instruction build()
        {
            return new Instruction(this);
        }

    }

    /**
     * Displays instruction using instruction property.
     * Right now just prints content.
     */
    public void display()
    {
        OutputDisplay.writeOutput(m_text.trim());
    }

    public String getText()
    {
        return m_text;
    }
    public String getColor()
    {
        return m_color;
    }
    public String getFont()
    {
        return m_font;
    }
}
