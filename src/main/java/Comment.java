import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Comment extends SubmittedContent {
  private int blogpost_id;
  private String type_of_content;
  public Comment(String content, int blogpost_id) {
    text = content;
    this.blogpost_id = blogpost_id;
  }

  public static List<Comment> getAllComments(){
      try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM submitted_content WHERE type_of_content='comment';";
      return con.createQuery(sql)
      .executeAndFetch(Comment.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO submitted_content (text, type_of_content, submitted_time) VALUES (:text, 'comment', now());";
      this.id=(int) con.createQuery(sql,true)
      .throwOnMappingFailure(false)
      .addParameter("text", this.text)
      .executeUpdate()
      .getKey();

      String saveCommentTime = "SELECT submitted_time FROM submitted_content WHERE id=:id;";
      this.submitted_time = con.createQuery(saveCommentTime)
      .throwOnMappingFailure(false)
      .addParameter("id", this.id)
      .executeAndFetchFirst(Timestamp.class);

    }
  }

}
