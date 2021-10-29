package service;

import DAO.DAOImpl;
import VO.Enrollment;
import VO.Student;
import service.StudentService;

import java.util.List;

public class ServiceImpl implements StudentService {
    public List<Student> getAllStudents() {
        System.out.println("service");
        DAOImpl sdi = new DAOImpl();
        List<Student> res = sdi.getAll();
        sdi.close();
        return res;
    }

    public List<Enrollment> getInfoAboutStudent(Student student) {
        DAOImpl sdi = new DAOImpl();
        List<Enrollment> res = sdi.getDiscByStudent(student);
        sdi.close();
        return res;
    }

    public List<Enrollment> getInfoAboutStudentbyId(int id) {
        DAOImpl sdi = new DAOImpl();
        List<Enrollment> res = sdi.getDiscByStudentId(id);
        sdi.close();
        return res;
    }
}