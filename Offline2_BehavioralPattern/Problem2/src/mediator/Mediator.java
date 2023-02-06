package mediator;

import component.Component;
import util.event.Event;

public interface Mediator {
    void send(Component from, Event event);
}

