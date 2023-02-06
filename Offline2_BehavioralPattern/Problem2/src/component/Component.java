package component;

import mediator.Mediator;

import java.util.Objects;

public abstract class Component {
    protected String componentType;
    protected int id;
    protected Mediator examControllerOffice;

    public Component(String componentType, int id, Mediator examControllerOffice) {
        this.componentType = componentType;
        this.id = id;
        this.examControllerOffice = examControllerOffice;
    }

    public int getId() {
        return id;
    }

    public Mediator getExamControllerOffice() {
        return examControllerOffice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExamControllerOffice(Mediator examControllerOffice) {
        this.examControllerOffice = examControllerOffice;
    }

    protected void showComponentDetail() {
        System.out.println(componentType + " ID: " + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        Component component = (Component) o;
        return getId() == component.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
