import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectManager extends Person{
    private Map<String, Person> developers;

    private String projectName;
    private String companyName;

    public ProjectManager(String name, String companyName) {
        this.name = name;
        this.companyName = companyName;

        developers = new HashMap<>();
    }

    @Override
    public void details() {
        System.out.println("Name: " + name);
        System.out.println("Role: " + "Project Manager");
        System.out.println("Current Project: " + projectName);
        System.out.println("Number of supervisees: " + developers.size());
        System.out.println("Company: " + companyName);
    }

    @Override
    public void hierarchy(int level) {
        printNTabs(level);
        System.out.println("- " + name + " (" + projectName + ")");
        for (Person developer : developers.values()) {
            developer.hierarchy(level +1);
        }
    }

    @Override
    public boolean addChild(String name) {
        if(developers.containsKey(name)) {
            System.out.println("Developer with this name already exists");
            return false;
        } else {
            developers.put(name, new Developer(name, this.name, projectName, companyName));
            System.out.println("Developer " + name + " added to project " + projectName + " under supervision of " + this.name);
            return true;
        }
    }

    @Override
    public Component getChild(String name) {
        return developers.get(name);
    }

    @Override
    public void removeChild(String name) {
        if(developers.containsKey(name)) {
            developers.remove(name);
            System.out.println("Developer " + name + " under supervision of " + this.name + " removed");
        } else {
            System.out.println("Developer with this name does not exist");
        }
    }


    @Override
    public void removeAllChildren() {
        List<String> developerNames = developers.keySet().stream().toList();
        for(String developerName : developerNames) {
            removeChild(developerName);
        }
        developers.clear();
    }

    @Override
    public List<String> getChildrenNames() {
        List<String> developerNames = new ArrayList<>(developers.keySet());
        return developerNames;
    }

    //getters
    public Map<String, Person> getDevelopers() {
        return developers;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    // setters
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
