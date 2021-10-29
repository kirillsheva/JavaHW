
import java.util.ArrayList;

public class Grades implements Comparable<Grades> {

    private final String name;
    private final ArrayList<Integer> grades;
    private float average_rating;

    Grades(String name, ArrayList<Integer> grades) {
        this.name = name;
        this.grades = grades;
        this.average_rating = averageCounter(grades) ;
    }


    private float averageCounter(ArrayList<Integer> grades) {
        float sum = 0;
        for(float d : grades)
            sum += d;
        return sum/grades.size();
    }


    public int compareTo(Grades st) {
        return -Float.compare(average_rating, st.average_rating);
    }


    @Override
    public String toString() {
        return "\nStudent " + name +" | Avg: " + Math.round(average_rating*10f)/10f;
    }
}