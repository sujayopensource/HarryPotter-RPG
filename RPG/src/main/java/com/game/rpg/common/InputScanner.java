package com.game.rpg.common;

import java.util.Scanner;

public class InputScanner
{
        private final Scanner scanner;

        public InputScanner()
        {
            this(new Scanner(System.in));
        }
        InputScanner(Scanner scanner)
        {
            this.scanner  = scanner;
        }

        public String getInput()
        {
            return scanner.nextLine();
        }

}
