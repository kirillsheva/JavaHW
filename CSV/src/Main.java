
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Grades> students = new ArrayList<>();

        ArrayList<Integer> grades = new ArrayList<>();

        String row;


        try {

            File file = new File("src/students.csv");
            BufferedReader csv = new BufferedReader(new FileReader(file));

            while ((row = csv.readLine()) != null) {


                String[] person = row.split(";");

                String name = person[0];

                for (int i=1; i<person.length;i++){
                    grades.add(Integer.parseInt(person[i]));
                }

                students.add(new Grades(name, grades));

                grades.clear();
            }
            csv.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(students);

        for (Grades st:students){
            System.out.println(st.toString());
        }

    }

}
