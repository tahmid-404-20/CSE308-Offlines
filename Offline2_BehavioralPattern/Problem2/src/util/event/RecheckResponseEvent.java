package util.event;

import util.exam_util.Script;

public class RecheckResponseEvent implements Event {
    private Script script;
    private boolean changed;

    public RecheckResponseEvent(Script script, boolean changed) {
        this.script = script;
        this.changed = changed;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }
}
