package util.exam_util;

import java.util.List;

public class ExaminerPackage {
    private List<Script> scriptList;
    private MarkSheet marksheet;

    public ExaminerPackage(List<Script> scriptList, MarkSheet marksheet) {
        this.scriptList = scriptList;
        this.marksheet = marksheet;
    }

    public List<Script> getScriptList() {
        return scriptList;
    }

    public MarkSheet getMarksheet() {
        return marksheet;
    }

    public void setScriptList(List<Script> scriptList) {
        this.scriptList = scriptList;
    }

    public void setMarksheet(MarkSheet marksheet) {
        this.marksheet = marksheet;
    }
}
