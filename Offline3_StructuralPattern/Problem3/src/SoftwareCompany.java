import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftwareCompany extends Component{
    Map<String, Component> projectManagers;

    public SoftwareCompany(String name) {
        this.name = name;
        projectManagers = new HashMap<>();
    }



    @Override
    public void details() {
        System.out.println("Company name: " + name);
        System.out.println("Number of currently running projects: " + projectManagers.size());
    }

    @Override
    public void hierarchy(int level) {
        printNTabs(level);
        System.out.println("- " + name);
        for (Component projectManager : projectManagers.values()) {
            projectManager.hierarchy(level +1);
        }
    }

    @Override
    public boolean addChild(String name) {
        if(projectManagers.containsKey(name)) {
            System.out.println("Project Manager with this name already exists");
            return false;
        } else {
            projectManagers.put(name, new ProjectManager(name, this.name));
            System.out.println("Project Manager " + name + " added to company " + this.name);
            return true;
        }
    }

    @Override
    public Component getChild(String name) {
        return projectManagers.get(name);
    }

    @Override
    public void removeChild(String name) {
        if(projectManagers.containsKey(name)) {
            projectManagers.get(name).removeAllChildren();
            projectManagers.remove(name);
            System.out.println("Project Manager " + name + " of company " + this.name + " removed");
        } else {
            System.out.println("Project Manager with this name does not exist");
        }
    }

    @Override
    public void removeAllChildren() {
        List<String> projectManagerNames = projectManagers.keySet().stream().toList();
        for(String projectManagerName : projectManagerNames) {
            projectManagers.get(projectManagerName).removeAllChildren();
            removeChild(projectManagerName);
        }
        projectManagers.clear();
    }

    @Override
    public List<String> getChildrenNames() {
        return new ArrayList<>(projectManagers.keySet());
    }
}
