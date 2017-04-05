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

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void createUserObject_true (){
    User newUser = new User ("Riley");
    assertTrue(newUser instanceof User);
  }
  @Test
  public void returnUserName_Riley (){
    User newUser = new User ("Riley");
    assertEquals("Riley", newUser.getName());
  }
  @Test
  public void saveUsertoDB_size1(){
    User newUser = new User ("Riley");
    newUser.save();
    assertEquals(1, User.getAllUsers().size());
  }
  @Test
  public void getUserId_greaterthan0(){
    User newUser = new User ("Riley");
    newUser.save();
    assertTrue(newUser.getUserId() > 0);
  }
  @Test
  public void findUser_true(){
    User newUser = new User ("Riley");
    newUser.save();
    User newUser2 = new User ("Xia");
    newUser2.save();
    assertTrue(newUser2.equals(User.find(newUser2.getUserId())));
  }
}
