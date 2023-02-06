package util.event;

import util.exam_util.Script;

public class RecheckRequestEvent implements Event {
    private Script script;

    public RecheckRequestEvent(Script script) {
        this.script = script;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
