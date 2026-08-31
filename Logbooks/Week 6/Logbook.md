# Week 6 Logbook code
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
