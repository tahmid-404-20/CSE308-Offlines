package client;

import component.Examiner;
import component.Student;
import mediator.ExamControllerOffice;
import util.exam_util.Script;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        System.out.println("Welcome to result processing system!");

        Scanner scr = new Scanner(System.in);

        ExamControllerOffice examControllerOffice = new ExamControllerOffice();

        int nExaminer = 0;
        int nStudents = 0;

        try{
            System.out.print("Enter the number of examiners: ");
            nExaminer = Integer.parseInt(scr.nextLine());
            System.out.print("Enter the number of students: ");
            nStudents = Integer.parseInt(scr.nextLine());

            if(nExaminer < 1 || nStudents < 1) {
                System.out.println("Negative not acceptable");
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("Number of examiners or students must be integers!");
            System.exit(1);
        }

        List<Examiner> examinerList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        for(int i=0; i< nExaminer; i++) {
            examinerList.add(examinerList.size(), new Examiner(i+1, examControllerOffice));
        }

        for(int i=0;i<nStudents;i++) {
            studentList.add(studentList.size(), new Student(i+1, examControllerOffice));
        }

        examControllerOffice.setExaminerList(examinerList);
        examControllerOffice.setStudentList(studentList);

        /* create nStudents scripts for each examiner */
        Map<Examiner, List<Script>> examinerScriptList = new HashMap<>();
        for(Examiner examiner:examinerList) {
            List<Script> scriptListOfExaminer = new ArrayList<>();
            for(Student student:studentList) {
                scriptListOfExaminer.add(scriptListOfExaminer.size(),
                        new Script(examiner.getId(),student.getId(), 0));
            }
            examinerScriptList.put(examiner, scriptListOfExaminer);
        }

        // Examiners will send result to CoE office now
        for(Examiner examiner:examinerList) {
            examiner.sendResult(examinerScriptList.get(examiner));
        }

        // recheck section
        while(true) {
            try {
                System.out.print("Enter the student id for rechecking: ");
                int studentId = Integer.parseInt(scr.nextLine());

                boolean found = false;
                for(Student student:studentList) {
                    if(student.getId() == studentId) {
                        found = true;
                        System.out.println("Script List of Student ID: " + studentId);
                        student.printAllScriptDetails();
                        System.out.print("Enter the examiner id whom you want to send recheck request: ");
                        int examinerId = Integer.parseInt(scr.nextLine());
                        student.sendRecheckRequest(examinerId);
                    }
                }

                if(!found) {
                    System.out.println("Student Id not found");
                }
            } catch (Exception e) {
                System.out.println("Id must be an integer!");
            }
        }

    }
}
