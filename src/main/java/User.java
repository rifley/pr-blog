import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class User {
  private String name;
  private int id;

  public User(String name) {
    this.name=name;
  }
  public String getName(){
    return name;
  }
  public int getUserId(){
    return id;
  }
  public void save(){
    try (Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO users (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }
  public static List<User> getAllUsers(){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users;";
      return con.createQuery(sql)
      .executeAndFetch(User.class);
    }
  }
  public static User find(int id){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users WHERE id=:id;";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(User.class);
    }
  }
  @Override
  public boolean equals (Object otherUser){
    if(! (otherUser instanceof User)){
      return false;
    } else{
      User newUser = (User) otherUser;
      return this.getName().equals(newUser.getName()) && this.getUserId() == newUser.getUserId();
    }
  }


}
