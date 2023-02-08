import java.util.List;

public class Developer extends Person{
    private String projectManagerName;
    private String projectName;
    private String companyName;

    public Developer(String name,String projectManagerName, String projectName, String companyName) {
        this.name = name;
        this.projectManagerName = projectManagerName;
        this.projectName = projectName;
        this.companyName = companyName;
    }

    @Override
    public void details() {
        System.out.println("Name: " + name);
        System.out.println("Role: " + "Developer");
        System.out.println("Current Project: " + projectName);
        System.out.println("Project Manager: " + projectManagerName);
        System.out.println("Company: " + companyName);
    }

    @Override
    public void hierarchy() {
        System.out.println("\t\t- " + name);
    }

    @Override
    public boolean addChild(String name) {
        return false;
    }

    @Override
    public Component getChild(String name) {
        return null;
    }

    @Override
    public void removeChild(String name) {
        // not relevant
    }

    @Override
    public void removeAllChildren() {
        // not relevant
    }

    @Override
    public List<String> getChildrenNames() {
        // not relevant
        return null;
    }
}
