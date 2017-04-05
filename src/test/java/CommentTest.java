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


public class CommentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void commentInstantiatesCorrectly_true() {
    Comment newComment = new Comment("Good Times", 1);
    assertTrue(newComment instanceof Comment);
  }

  @Test
  public void getContent_commentGetsContent_String() {
    Comment newComment = new Comment("Better Times", 1);
    assertEquals("Better Times", newComment.getContent());
  }

  @Test
  public void createCommentAndSaveIt_size1() {
    Comment newComment = new Comment("Better Times", 1);
    newComment.save();
    assertTrue (Comment.getAllComments().size()==1);
  }

  @Test
  public void commentIDgreaterthan1() {
    Comment newComment = new Comment("Better Times", 1);
    newComment.save();
    assertTrue (newComment.getId()>0);
  }

  @Test
  public void savedCommentTimeStampisaccurate_true(){
    Comment newComment = new Comment("Better Times",1);
    newComment.save();
    Timestamp timeNow = new Timestamp(new Date().getTime());
    assertEquals (DateFormat.getDateTimeInstance().format(newComment.getSubmittedTime()), DateFormat.getDateTimeInstance().format(timeNow.getTime()));
  }

  // @Test
  // public void findComment_true() {
  //   Comment newComment = new Comment("Better Times");
  //   newComment.save();
  //   Comment newComment2 = new Comment("Best Of Times");
  //   newComment2.save();
  //   assertTrue (newComment2.equals(Comment.find(newComment2.getId())));
  // }
  //
  // @Test
  // public void deleteComment_sizeis1() {
  //   Comment newComment = new Comment("Better Times");
  //   newComment.save();
  //   Comment newComment2 = new Comment("Best Of Times");
  //   newComment2.save();
  //   newComment.delete();
  //   assertTrue (Comment.getAllComments().size()==1);
  // }
  //
  // @Test
  // public void updateComment_Best() {
  //   Comment newComment = new Comment("Better Times");
  //   newComment.save();
  //   newComment.update("Best Times");
  //   assertEquals("Best Times", Comment.find(newComment.getId()).getContent());
  // }
}
