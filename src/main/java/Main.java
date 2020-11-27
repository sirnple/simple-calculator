import java.io.InputStream;

/**
 * Created by: sirnple
 * Created in: 2020-11-26
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        InputStream resourceAsStream = Main.class.getResourceAsStream("example/multiply");
        System.out.println("");
    }
}
