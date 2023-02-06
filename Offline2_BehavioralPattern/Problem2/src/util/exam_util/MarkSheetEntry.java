package util.exam_util;

public class MarkSheetEntry {
    private int studentId;
    private int marks;

    public MarkSheetEntry(int studentId, int marks) {
        this.studentId = studentId;
        this.marks = marks;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getMarks() {
        return marks;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
