
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;


public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getStudentsScheduled;
    private static PreparedStatement getSchedule;
    private static PreparedStatement getScheduledByCourseCode;
    private static PreparedStatement getWaitlistedByCourseCode;
    private static PreparedStatement dropStudentByCourse;
    private static PreparedStatement waitlistedToScheduled;
    private static ResultSet resultSet;
    
    
    public static void addSchedule(String semester, String coursecode, String studentID, String status, Timestamp timestamp)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSchedule = connection.prepareStatement("insert into app.schedule (semester,coursecode,studentid, status, timestamp) values (?,?,?,?,?)");
            addSchedule.setString(1, semester);
            addSchedule.setString(2, coursecode);
            addSchedule.setString(3, studentID);
            addSchedule.setString(4, status);
            addSchedule.setTimestamp(5, timestamp);
            addSchedule.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
        public static ArrayList<ScheduleEntry> getSchedule(String Semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        try
        {
            getSchedule = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = ? and studentid = ?");
            getSchedule.setString(1, Semester);
            getSchedule.setString(2, studentID);
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next())
            {

                schedule.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static int getStudentsScheduled(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        int studentcount = 0;
        try
        {
            getStudentsScheduled = connection.prepareStatement("select count(studentid) from app.schedule where semester = (?) and courseCode = (?)");
            getStudentsScheduled.setString(1, semester);
            getStudentsScheduled.setString(2, coursecode);
            resultSet = getStudentsScheduled.executeQuery();
            
            while(resultSet.next())
            {
                studentcount = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentcount;
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduledByCourseCode(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> sList = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledByCourseCode = connection.prepareStatement("select studentID, timestamp from app.schedule where semester = ? and courseCode = ? and status = ?");
            getScheduledByCourseCode.setString(1, semester);
            getScheduledByCourseCode.setString(2, coursecode);
            getScheduledByCourseCode.setString(3, "S");
            resultSet = getScheduledByCourseCode.executeQuery();
            
            while(resultSet.next())
            {
                sList.add(new ScheduleEntry(semester,coursecode, resultSet.getString(1), "S", resultSet.getTimestamp(2)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return sList;
        
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedByCourseCode(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> wList = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedByCourseCode = connection.prepareStatement("select studentID, timestamp from app.schedule where semester = ? and courseCode = ? and status = ?");
            getWaitlistedByCourseCode.setString(1, semester);
            getWaitlistedByCourseCode.setString(2, coursecode);
            getWaitlistedByCourseCode.setString(3, "W");
            resultSet = getWaitlistedByCourseCode.executeQuery();
            
            while(resultSet.next())
            {
                wList.add(new ScheduleEntry(semester,coursecode, resultSet.getString(1), "W", resultSet.getTimestamp(2)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return wList;
        
    }
    
    public static void dropStudentByCourse(String semester, String studentID, String coursecode)
    {
        connection = DBConnection.getConnection();

        try
        {
            dropStudentByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and studentID = ? and coursecode = ?");
            dropStudentByCourse.setString(1, semester);
            dropStudentByCourse.setString(2, studentID);
            dropStudentByCourse.setString(3, coursecode);
            dropStudentByCourse.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
    }
    
    public static void waitlistedToScheduled(String semester, ScheduleEntry news)
    {
        connection = DBConnection.getConnection();

        try
        {
            waitlistedToScheduled = connection.prepareStatement("update app.schedule set status = ? where semester = ? and coursecode = ? and studentID = ?");
            waitlistedToScheduled.setString(1, "S");
            waitlistedToScheduled.setString(2, semester);
            waitlistedToScheduled.setString(3, news.getCoursecode());
            waitlistedToScheduled.setString(4, news.getStudentID());
            waitlistedToScheduled.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
    }
    
}
