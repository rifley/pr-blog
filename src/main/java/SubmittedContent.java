import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public abstract class SubmittedContent {
public String text;
public int user_Id;
public Timestamp submitted_time;
public int id;

  public String getContent() {
    return text;
  }
  public Timestamp getSubmittedTime(){
    return submitted_time;
  }

  public int getId (){
    return id;
  }

  

}
