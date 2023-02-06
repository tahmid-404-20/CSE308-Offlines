package util.event;

import util.exam_util.ExaminerPackage;

public class ResultPublishedEvent implements Event {
    private ExaminerPackage examinerPackage;

    public ResultPublishedEvent(ExaminerPackage examinerPackage) {
        this.examinerPackage = examinerPackage;
    }

    public ExaminerPackage getExaminerPackage() {
        return examinerPackage;
    }

    public void setExaminerPackage(ExaminerPackage examinerPackage) {
        this.examinerPackage = examinerPackage;
    }
}
