
public class ClassEntry {
    
    private String semester;
    private String coursecode;
    private int seats;
    private String description;
    
    public ClassEntry(String semester, String coursecode, int Seats, String description) {
        this.semester = semester;
        this.coursecode = coursecode;
        this.seats = Seats;
        this.description = description;
        
    }
    
    public String getSemester() {
        return semester;
    }
    
    public String getcoursecode() {
        return coursecode;
    }
    
    public int getSeats() {
        return seats;
    }
    
    public String getDescription() {
        return description;
    }
}
