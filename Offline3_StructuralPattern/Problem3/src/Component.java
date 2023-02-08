import java.util.List;

public abstract class Component {
    protected String name;

    public String getName() {
        return name;
    }
    protected void printNTabs(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("\t");
        }
    }

    public abstract void details();
    public abstract void hierarchy(int level);

    public abstract boolean addChild(String name);
    public abstract Component getChild(String name);
    public abstract void removeChild(String name);
    public abstract void removeAllChildren();
    public abstract List<String> getChildrenNames();  // as all the names are unique
}
