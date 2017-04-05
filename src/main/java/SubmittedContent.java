import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public abstract class SubmittedContent {
public String userContent;
public int user_Id;
public Timestamp post_Time;


  public String getContent() {
    return userContent;
  }

}
