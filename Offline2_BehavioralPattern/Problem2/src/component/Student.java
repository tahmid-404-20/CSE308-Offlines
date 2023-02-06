package component;

import mediator.Mediator;
import util.event.RecheckRequestEvent;
import util.exam_util.Script;

import java.util.List;

public class Student extends Component {
    private List<Script> studentScripts;

    public Student(int id, Mediator examControllerOffice) {
        super("Student", id, examControllerOffice);
    }

    public void receiveResult(List<Script> studentScripts) {
        this.studentScripts = studentScripts;

        // printing and other stuffs
        showComponentDetail();
        System.out.println("Received Result ");
        printAllScriptDetails();
    }

    public void sendRecheckRequest(int examinerId) {
        Script toBeRecheckedScript = null;
        for(Script script:studentScripts) {
            if(script.getExaminer_id() == examinerId) {
                toBeRecheckedScript = script;
                break;
            }
        }

        if(toBeRecheckedScript != null) {
            RecheckRequestEvent event = new RecheckRequestEvent(toBeRecheckedScript);
            showComponentDetail();
            System.out.println("Sending recheck request for script checked by examiner " + examinerId);
            System.out.println();
            examControllerOffice.send(this,event);
        } else {
            System.out.println("Invalid Examiner Id");
        }
    }

    public void receiveRecheckResponse(Script script, boolean isChanged) {
        showComponentDetail();
        System.out.println("Received response of recheck of the script of examiner " + script.getExaminer_id());
        if(isChanged) {
            System.out.println("Status: Changed");
            System.out.println("New marks: " + script.getMarks());
        } else {
            System.out.println("Status: Unchanged");
        }

        System.out.println();
    }

    public void printAllScriptDetails() {
        for(Script script:studentScripts) {
            System.out.println("Course taken by examiner: " + script.getExaminer_id() +
                    " --> Obtained Marks " + script.getMarks());
        }
        System.out.println();
    }
}
