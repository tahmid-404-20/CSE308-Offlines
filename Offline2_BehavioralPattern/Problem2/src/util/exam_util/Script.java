package util.exam_util;

public class Script {
    private int examiner_id;
    private int student_id;
    private int marks;

    public Script(int examiner_id, int student_id, int marks) {
        this.examiner_id = examiner_id;
        this.student_id = student_id;
        this.marks = marks;
    }

    public int getExaminer_id() {
        return examiner_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getMarks() {
        return marks;
    }

    public void setExaminer_id(int examiner_id) {
        this.examiner_id = examiner_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
