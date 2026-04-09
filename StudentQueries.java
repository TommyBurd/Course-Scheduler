
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getName;
    private static PreparedStatement getSpecStudent;
    private static PreparedStatement getStudentListTwo;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSetID;
    private static ResultSet resultSet;
    
    public static void addStudent(String ID, String first, String last)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.Student (studentid,firstname,lastname) values (?,?,?)");
            addStudent.setString(1, ID);
            addStudent.setString(2, first);
            addStudent.setString(3, last);
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getStudentList()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();

        try
        {
            getStudentList = connection.prepareStatement("select * from app.student");
            resultSet = getStudentList.executeQuery();
            
            while(resultSet.next())
            {

                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static String getStudentID(String first, String last)
    {
        connection = DBConnection.getConnection();
        String id = "";
        try
        {
            getStudentID = connection.prepareStatement("select studentID from app.student where firstname = (?) and lastname = (?)");
            getStudentID.setString(1, first);
            getStudentID.setString(2, last);
            resultSetID = getStudentID.executeQuery();
            
            while(resultSetID.next())
            {

                id = resultSetID.getString(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return id;
        
    }
    
    public static String getName(String ID)
    {
        connection = DBConnection.getConnection();
        String name = "";
        try
        {
            getName = connection.prepareStatement("select lastname, firstname from app.student where StudentID = ? ");
            getName.setString(1, ID);
            resultSetID = getName.executeQuery();
            
            while(resultSetID.next())
            {

                name = resultSetID.getString(1) + ", " + resultSetID.getString(2);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return name;
        
    }
    
    public static StudentEntry getSpecStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        StudentEntry student = null;

        try
        {
            getSpecStudent = connection.prepareStatement("select * from app.student where studentID = ?");
            getSpecStudent.setString(1, studentID);
            resultSet = getSpecStudent.executeQuery();
            
            while(resultSet.next())
            {

                student = new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static ArrayList<String> getStudentListTwo()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> student = new ArrayList<String>();
        try
        {
            getStudentListTwo = connection.prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = getStudentListTwo.executeQuery();
            
            while(resultSet.next())
            {

                student.add(resultSet.getString(3) + ", " + resultSet.getString(2));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static void dropStudent(String studentID)
    {
        connection = DBConnection.getConnection();

        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where studentID = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }       
    }
}
