import java.sql.Timestamp;
import java.util.Calendar;

public class ScheduleEntry {
    private String semester;
    private String coursecode;
    private String studentID;
    private String status;
    private Timestamp timestamp;

    public ScheduleEntry(String semester, String coursecode, String studentID, String status, Timestamp timestamp) {
        this.semester = semester;
        this.coursecode = coursecode;
        this.studentID = studentID;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getSemester() {
        return semester;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
    
    
}
