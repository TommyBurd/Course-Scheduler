
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseList;
    private static PreparedStatement getCourseDescription;
    private static ResultSet resultSet;
    
        public static void addCourse(String name, String description)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (coursecode, description) values (?,?)");
            addCourse.setString(1, name);
            addCourse.setString(2, description);
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
        
    public static ArrayList<String> getCourseList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> course = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select coursecode from app.course order by coursecode");
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                course.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
    }
    
    public static String getCourseDescription(String coursecode)
    {
        connection = DBConnection.getConnection();
        String description = "";
        try
        {
            getCourseDescription = connection.prepareStatement("select description from app.course where coursecode = ?");
            getCourseDescription.setString(1, coursecode);
            resultSet = getCourseDescription.executeQuery();
            
            while(resultSet.next())
            {
                description = resultSet.getString(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return description;
    }
}




