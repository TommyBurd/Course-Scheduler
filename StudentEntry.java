
public class StudentEntry {
    private String studentID;
    private String first;
    private String last;
    
    public StudentEntry(String studentID, String first, String last ) {
        this.studentID = studentID;
        this.first = first;
        this.last = last;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getLast(){
        return last;
    }
    
    public String getFirstandLast(){
        return getLast() + ", " + getFirst();
        
    }
    
    public String toString(){
        return String.format("%s, %s %s",last,first,studentID);
    }
}
