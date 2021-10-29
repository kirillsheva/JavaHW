package service;

import VO.Enrollment;
import VO.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public List<Enrollment> getInfoAboutStudent(Student student);
    public List<Enrollment> getInfoAboutStudentbyId(int id);
}