import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Post extends SubmittedContent{
  private String type_of_content;

  public Post(String content) {
    text = content;
  }

  public void save() {
    try (Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO submitted_content (text, type_of_content, submitted_time) VALUES (:text, 'blogpost', now());";
      this.id=(int) con.createQuery(sql,true)
      .throwOnMappingFailure(false)
      .addParameter("text", this.text)
      .executeUpdate()
      .getKey();

      String savePostTime = "SELECT submitted_time FROM submitted_content WHERE id=:id;";
      this.submitted_time = con.createQuery(savePostTime)
      .throwOnMappingFailure(false)
      .addParameter("id", this.id)
      .executeAndFetchFirst(Timestamp.class);

    }
  }

  public static List<Post> getAllPosts(){
    try (Connection con = DB.sql2o.open()){
    String sql = "SELECT * FROM submitted_content WHERE type_of_content='blogpost';";
    return con.createQuery(sql)
    .executeAndFetch(Post.class);
  }
}

  public static Post find (int id){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM submitted_content WHERE id=:id;";
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .addParameter("id", id)
      .executeAndFetchFirst(Post.class);
    }
  }

  @Override
  public boolean equals(Object somePost) {
    if(!(somePost instanceof Post)) {
      return false;
    } else {
      Post newPost = (Post) somePost;
      return this.getContent().equals(newPost.getContent()) && this.getId() == newPost.getId();
    }
  }

  public void delete(){
    try(Connection con=DB.sql2o.open()){
      String sql = "DELETE FROM submitted_content WHERE id=:id;";
      con.createQuery(sql)
      .throwOnMappingFailure(false)
      .addParameter("id", this.id)
      .executeUpdate();

      //this will delete all comments of this post
      // String deleteCommentsOfPost = "DELETE FROM submitted_content WHERE blogpost_id=:id;";

      //from posts_tags table...remove rows that  blogpost_id equals the id of this post being deleted.
    }
  }

  public void update(String text){
    try(Connection con=DB.sql2o.open()){
      String sql = "UPDATE submitted_content SET text = :text;";
      con.createQuery(sql)
      .addParameter("text", text)
      .executeUpdate();
    }
  }

  //delete comments of this post




}
