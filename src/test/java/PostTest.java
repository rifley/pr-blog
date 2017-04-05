import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class PostTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void postInstantiatesCorrectly_true() {
    Post newPost = new Post("Good Times");
    assertTrue(newPost instanceof Post);
  }

  @Test
  public void getContent_postGetsContent_String() {
    Post newPost = new Post("Better Times");
    assertEquals("Better Times", newPost.getContent());
  }

}
