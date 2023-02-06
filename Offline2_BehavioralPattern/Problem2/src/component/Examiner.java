package component;

import mediator.Mediator;
import util.event.RecheckResponseEvent;
import util.event.ResultPublishedEvent;
import util.exam_util.ExaminerPackage;
import util.exam_util.MarkSheet;
import util.exam_util.MarkSheetEntry;
import util.exam_util.Script;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Examiner extends Component {

    public Examiner(int id, Mediator examControllerOffice) {
        super("Examiner", id, examControllerOffice);
    }

    public void sendResult(List<Script> examScripts) {
        List<Integer> studentIdList = new ArrayList<>();
        List<MarkSheetEntry> markSheetEntries = new ArrayList<>();
        Random random = new Random();
        for(Script script:examScripts) {
            script.setMarks(random.nextInt(101));
            studentIdList.add(studentIdList.size(), script.getStudent_id());
            markSheetEntries.add(new MarkSheetEntry(script.getStudent_id(),script.getMarks()));
        }
        ExaminerPackage examinerPackage = new ExaminerPackage(examScripts, new MarkSheet(markSheetEntries));
        ResultPublishedEvent event = new ResultPublishedEvent(examinerPackage);

        showComponentDetail();
        System.out.print("Scripts and marks of student id ");
        for(Integer i:studentIdList) {
            System.out.print(i + ",");
        }
        System.out.println("\b sent to exam controller office.");
        System.out.println();

        examControllerOffice.send(this, event);
    }

    public void receiveRecheckRequest(Script script) {
        showComponentDetail();
        System.out.println("Received recheck request for Student ID: " + script.getStudent_id() + " Marks: " + script.getMarks());
        System.out.print("Would you like to make any changes to the marks? (Y/N): ");
        Scanner scr = new Scanner(System.in);
        String response = scr.nextLine();
        if(response.startsWith("Y") || response.startsWith("y")) {
            while(true) {
                try {
                    System.out.print("Enter the updated marks: ");
                    int marks = Integer.parseInt(scr.nextLine());
                    script.setMarks(marks);
                    showComponentDetail();
                    System.out.println("Sending response to recheck request of student " + script.getStudent_id());
                    System.out.println();
                    examControllerOffice.send(this,new RecheckResponseEvent(script,true));
                    break;
                } catch (Exception e) {
                    System.out.println("Marks must be an integer!!!");
                }
            }
        } else {
            showComponentDetail();
            System.out.println("Sending response to recheck request of student " + script.getStudent_id());
            System.out.println();
            examControllerOffice.send(this,new RecheckResponseEvent(script,false));
        }
    }
}
