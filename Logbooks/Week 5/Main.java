public class Main {
    public static void main(String[] args) {
        Container<String> strings = new Container<>();
        strings.add("hello");
        strings.add("world");

        // A raw type bypasses the compiler's normal generic type checking.
        Container rawStrings = strings;
        // This inserts an Integer into the same container that is declared as Container<String>.
        rawStrings.add(42);

        String first = strings.get(0);
        String second = strings.get(1);
        // The compiler-generated runtime check fails because index 2 contains an Integer, not a String.
        String third = strings.get(2);

        System.out.println(first + " " + second);
    }
}