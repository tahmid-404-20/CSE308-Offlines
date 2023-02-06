package util.exam_util;

import java.util.List;

public class MarkSheet {
    private List<MarkSheetEntry> markSheetEntry;

    public MarkSheet(List<MarkSheetEntry> markSheetEntry) {
        this.markSheetEntry = markSheetEntry;
    }

    public void setMarkSheetEntry(List<MarkSheetEntry> markSheetEntry) {
        this.markSheetEntry = markSheetEntry;
    }

    public List<MarkSheetEntry> getMarkSheetEntry() {
        return markSheetEntry;
    }
}
