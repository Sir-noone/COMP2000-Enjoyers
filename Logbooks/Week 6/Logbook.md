# Week 6 Logbook

## Exceptions

An exception is an event that occurs while a program is running and interrupts its normal flow, usually because an error or unexpected situation has occurred. In Java, exceptions can be handled with `try`, `catch`, and `finally` blocks: `try` contains code that may fail, `catch` responds to the exception, and `finally` runs cleanup code whether or not an exception occurs. Exceptions can also be thrown using `throw` and declared using `throws`.



## BarcodePuzzle.java 

public class BarcodePuzzle {

    static int iteration = 0;

    public static void main(String[] args) {
        star();
        System.out.println();
    }

    static void star() {
        System.out.print("* ");
        try {
            pipe();
        } catch (RuntimeException e) {
            System.out.print("* ");
        }
    }

    static void pipe() {
        System.out.print("| ");
        try {
            caret();
        } finally {
        System.out.print("| ");
        }
    }

    static void caret() {
        System.out.print("^ ");
        chuck_a_fit();
        System.out.print("^ ");
    }

    static void chuck_a_fit() {
        // add your throws here
        throw new RuntimeException();
    }
}
