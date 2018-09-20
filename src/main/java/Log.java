public class Log {
    private String name;

    Log(Class c) {
        name = c.getName();
    }

    public void info(String txt) {
        System.out.println(String.format("%s: %s", name, txt));
    }
}
