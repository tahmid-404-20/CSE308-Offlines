package mediator;

import component.Component;
import component.Examiner;
import component.Student;
import util.event.Event;
import util.event.RecheckRequestEvent;
import util.event.RecheckResponseEvent;
import util.event.ResultPublishedEvent;
import util.exam_util.MarkSheet;
import util.exam_util.MarkSheetEntry;
import util.exam_util.Script;

import java.util.*;

public class ExamControllerOffice implements Mediator {
    private static final int RANDOM_SCRIPT_MISTAKE = 2;
    private int nExaminers;
    private int nStudents;
    private int receivedResultCount;

    List<Examiner> examinerList;
    List<Student> studentList;

    Map<Examiner,List<Script>> examinersExamScripts;
    Map<Examiner, MarkSheet> examinersMarksheet;

    public ExamControllerOffice() { }

    public void setExaminerList(List<Examiner> examinerList) {
        this.receivedResultCount = 0;
        this.examinerList = examinerList;
        this.nExaminers = examinerList.size();
        examinersExamScripts = new HashMap<>();
        examinersMarksheet = new HashMap<>();
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        this.nStudents = studentList.size();
    }

    // things start here
    @Override
    public void send(Component from, Event event) {
        showOfficeName();
        if(from instanceof Examiner) {
            examinerEvents(from, event);
        } else if(from instanceof Student) {
            studentEvents(from, event);
        } else {
            System.out.println("Unrecognized Sender");
        }
    }

    private void examinerEvents(Component examiner, Event event) {
        if(event instanceof ResultPublishedEvent resultPublishedEvent) {
            List<Script> scripts = resultPublishedEvent.getExaminerPackage().getScriptList();
            MarkSheet markSheet = resultPublishedEvent.getExaminerPackage().getMarksheet();
            examinerHasSentResults(examiner, scripts, markSheet);
        } else if(event instanceof RecheckResponseEvent recheckResponseEvent) {
            Script script = recheckResponseEvent.getScript();
            System.out.println("Received re-examination response from Examiner " + script.getExaminer_id() + " for Student " + script.getStudent_id());
            System.out.println("Sending response to Student " +script.getStudent_id());
            if(recheckResponseEvent.isChanged()) {
                // update in the marksheet
                Objects.requireNonNull(getMarkSheetEntryOfStudentId(examinersMarksheet.get((Examiner) examiner),
                        script.getStudent_id())).setMarks(script.getMarks());
                System.out.println("Status: Changed");
            } else {
                System.out.println("Status: Unchanged");
            }

            System.out.println();

            for(Student student:studentList) {
                if(student.getId() == script.getStudent_id()) {
                    student.receiveRecheckResponse(script, recheckResponseEvent.isChanged());
                    break;
                }
            }

        } else {
            System.out.println("Unrecognized Event");
        }
    }

    private void studentEvents(Component student, Event event) {
        if (event instanceof RecheckRequestEvent recheckRequestEvent) {
            Script script = recheckRequestEvent.getScript();
            System.out.println("Received re-examination request from Student " + student.getId() + " for Examiner " + script.getExaminer_id() + "\n");
            for(Examiner examiner:examinerList) {
                if(examiner.getId() == script.getExaminer_id()) {
                    examiner.receiveRecheckRequest(script);
                }
            }
        } else {
            System.out.println("Unrecognized Event");
        }
    }

    //    examiner event helpers
    private void examinerHasSentResults(Component examiner, List<Script> scripts, MarkSheet markSheet) {
        // scrutinize the results
        System.out.println("Examiner " + examiner.getId() + " has sent scripts.");
        for(Script script:scripts) {
            System.out.println("StudentId: " + script.getStudent_id() + " Marks: " + script.getMarks());
        }

        System.out.println("Now scrutinizing...");
        for(Script script:scripts) {
            Random random = new Random();
            int k = random.nextInt() % RANDOM_SCRIPT_MISTAKE;

            System.out.print("Script of student " + script.getStudent_id() + " --> ");
            if (k == 0) { // mistake
                int prevMarks = script.getMarks();
                int newMarks = random.nextInt(101); // marks between 0 to 100

                // update in both script and markSheet
                script.setMarks(newMarks);
                Objects.requireNonNull(getMarkSheetEntryOfStudentId(markSheet, script.getStudent_id())).setMarks(newMarks);
                System.out.println("Mistake found. Previous marks " + prevMarks +  " Corrected marks " + script.getMarks());
            } else {
                System.out.println("OK");
            }
        }

        System.out.println();

        // update map
        examinersExamScripts.putIfAbsent((Examiner) examiner,scripts);
        examinersMarksheet.putIfAbsent((Examiner) examiner,markSheet);
        receivedResultCount++;

        // everyone has published result
        if(receivedResultCount == nExaminers) {
            publishResult();
        }
    }

    // helpers
    private void publishResult() {
        showOfficeName();
        System.out.println("Received Result from All the examiners! Preparing result and sending to students...\n");
        Map<Student, List<Script>> studentsScripts = new HashMap<>();

        // create student -> students script list format
        for(Examiner examiner:examinersExamScripts.keySet()) {
            for(Script script:examinersExamScripts.get(examiner)) {
                Student student = getStudentWithId(script.getStudent_id());
                studentsScripts.computeIfAbsent(student, k -> new ArrayList<>());
                studentsScripts.get(student).add(script);
            }
        }

        // send result to all students
        for(Student student:studentList) {
            student.receiveResult(studentsScripts.get(student));
        }
    }

    private void showOfficeName() {
        System.out.println("Exam Controller Office:");
    }

    private Student getStudentWithId(int studentId) {
        for(Student std:studentList) {
            if(studentId == std.getId()) {
                return  std;
            }
        }

        return null;
    }

    private MarkSheetEntry getMarkSheetEntryOfStudentId(MarkSheet markSheet, int studentId) {
        List<MarkSheetEntry> entries = markSheet.getMarkSheetEntry();

        for(MarkSheetEntry entry:entries) {
            if(entry.getStudentId() == studentId) {
                return entry;
            }
        }

        return null;
    }
}
