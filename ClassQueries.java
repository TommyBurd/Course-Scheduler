
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement getallCourses;
    private static PreparedStatement dropClass;
    private static ResultSet ResultSet;
    private static ResultSet coursecodeResults;
    private static ResultSet seatsResults;
    
    
    public static void addClass(ClassEntry classe)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class (semester,coursecode,seats,description) values (?,?,?,?)");
            addClass.setString(1, classe.getSemester());
            addClass.setString(2, classe.getcoursecode());
            addClass.setInt(3, classe.getSeats());
            addClass.setString(4, classe.getDescription());
            addClass.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    public static ArrayList<ClassEntry> getallCourses(String Semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassEntry> courses = new ArrayList<>();
        try
        {
            getallCourses = connection.prepareStatement("select semester, coursecode, seats, description from app.class where semester = (?)");
            getallCourses.setString(1, Semester);
            ResultSet = getallCourses.executeQuery();
            
            while(ResultSet.next())
            {

                courses.add(new ClassEntry(ResultSet.getString(1),ResultSet.getString(2),ResultSet.getInt(3),ResultSet.getString(4)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
    }
    
        public static ArrayList<String> getCourseCodes(String Semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> coursecodes = new ArrayList<String>();
        try
        {
            getCourseCodes = connection.prepareStatement("select coursecode from app.class where semester = (?)");
            getCourseCodes.setString(1, Semester);
            coursecodeResults = getCourseCodes.executeQuery();
            
            while(coursecodeResults.next())
            {

                coursecodes.add(coursecodeResults.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return coursecodes;
    }
    
    public static int getCourseSeats(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select seats from app.class where semester = ? and coursecode = ?");
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, coursecode);
            seatsResults = getCourseSeats.executeQuery();
            
            while(seatsResults.next())
            {

                seats = seatsResults.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
    }
    
    public static void dropClass(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();

        try
        {
            dropClass = connection.prepareStatement("delete from app.class where semester = ? and coursecode = ?");
            dropClass.setString(1, semester);
            dropClass.setString(2, coursecode);
            dropClass.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
    }
}



