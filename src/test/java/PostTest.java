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
import java.text.DateFormat;

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

  @Test
  public void createPostAndSaveIt_size1() {
    Post newPost = new Post("Better Times");
    newPost.save();
    assertTrue (Post.getAllPosts().size()==1);
  }

  @Test
  public void savedPostTimeStampisaccurate_true(){
    Post newPost = new Post("Better Times");
    newPost.save();
    Timestamp timeNow = new Timestamp(new Date().getTime());
    assertEquals (DateFormat.getDateTimeInstance().format(newPost.getSubmittedTime()), DateFormat.getDateTimeInstance().format(timeNow.getTime()));
  }

  @Test
  public void findPost_true() {
    Post newPost = new Post("Better Times");
    newPost.save();
    Post newPost2 = new Post("Best Of Times");
    newPost2.save();
    assertTrue (newPost2.equals(Post.find(newPost2.getId())));
  }

  @Test
  public void deletePost_sizeis1() {
    Post newPost = new Post("Better Times");
    newPost.save();
    Post newPost2 = new Post("Best Of Times");
    newPost2.save();
    newPost.delete();
    assertTrue (Post.getAllPosts().size()==1);
  }

  @Test
  public void updatePost_Best() {
    Post newPost = new Post("Better Times");
    newPost.save();
    newPost.update("Best Times");
    assertEquals("Best Times", Post.find(newPost.getId()).getContent());
  }

  //delete comments of this post







}
