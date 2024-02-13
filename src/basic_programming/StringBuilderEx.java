package basic_programming;

public class StringBuilderEx {
    public static void main(String[] args) throws Exception {
        StringBuilder str = new StringBuilder();
        str.append("goodbye");
        str.append("foo");
        str.append("world!");
        str.delete(7,10); // remove the characters at index positions 7-9, i.e. "foo"
        str.insert(7, " "); // insert a space at index position 7
        str.replace(0, 7, "hello"); // replaces the characters at index positions 0-6 (i.e. "goodbye) with "hello"
        String result = str.toString(); // -> "hello world!";
        System.out.println(result);

        StringBuilder str2 = new StringBuilder("hello world!");
        StringBuilder rts = str2.reverse();
        result = rts.toString();
        System.out.println(result);
    }
}
