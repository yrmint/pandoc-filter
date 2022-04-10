import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FindTest {
    String userDir = System.getProperty("user.dir") + "\\";

    @Test
    public void test1() {
        List<String> result = List.of(userDir + "dir\\dir1\\dir2\\file.txt", userDir + "dir\\dir1\\file.txt", userDir + "dir\\file.txt");
        assertEquals(result, new FindLauncher().launch(new String[]{"-r", "-d", "dir", "file.txt"}));
    }

    @Test
    public void test2() {
        assertEquals(Collections.singletonList(userDir + "dir\\dir3\\anecdote.txt"),
                new FindLauncher().launch(new String[]{"-r", "-d", "dir", "anecdote.txt"}));
    }

    @Test
    public void test3() {
        assertEquals(Collections.singletonList("Directory dir10 does not exist"),
                new FindLauncher().launch(new String[]{"-r", "-d", "dir10", "anecdote.txt"}));
    }

    @Test
    public void test4() {
        assertEquals(Collections.singletonList("File filename.txt not found"),
                new FindLauncher().launch(new String[]{"-r", "-d", "dir", "filename.txt"}));
    }
}
