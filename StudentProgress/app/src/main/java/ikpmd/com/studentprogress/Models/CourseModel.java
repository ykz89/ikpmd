package ikpmd.com.studentprogress.Models;

import java.io.Serializable;


public class CourseModel implements Serializable {
    //public int id;
    public int term;
    public String name;
    public int ects;
    public boolean mandatory;
    public String grade;

    public CourseModel(String courseName, int ects, int term, Boolean mandatory, String grade){
        //this.id = id;
        this.name = courseName;
        this.ects = ects;
        this.grade = grade;
        this.mandatory = mandatory;
        this.term = term;
    }
}
