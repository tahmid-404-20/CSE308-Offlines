import java.util.*;

public class Client {
    // some useful methods for i/o
    public static String chooseCompanyName(Scanner scr,  Map<String, Component> softwareCompanies) {
        List<String> companyNames = new ArrayList<>();
        System.out.println("Choose a company");
        int i = 1;
        for (String companyName : softwareCompanies.keySet()) {
            companyNames.add(companyName);
            System.out.println(i + ". " + companyName);
            i++;
        }
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scr.nextLine());
        if(choice < 1 || choice > companyNames.size()) {
            System.out.println("Invalid choice");
            return null;
        }
        return companyNames.get(choice - 1);
    }
    public static String chooseProjectManagerName(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return null;
        }

        List<String> projectManagerNames = softwareCompanies.get(companyName).getChildrenNames();
        System.out.println("Choose a project manager of " + companyName + " company");
        int i = 1;
        for (String projectManagerName : projectManagerNames) {
            System.out.println(i + ". " + projectManagerName);
            i++;
        }
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scr.nextLine());
        if(choice < 1 || choice > projectManagerNames.size()) {
            System.out.println("Invalid choice");
            return null;
        }
        return projectManagerNames.get(choice - 1);
    }
    public static String chooseProjectManagerName(Scanner scr, Map<String, Component> softwareCompanies, String companyName) {
        if(companyName == null) {
            return null;
        }

        List<String> projectManagerNames = softwareCompanies.get(companyName).getChildrenNames();
        if(projectManagerNames.size() == 0) {
            System.out.println("No project managers in this company");
            return null;
        }
        System.out.println("Choose a project manager of " + companyName + " company");
        int i = 1;
        for (String projectManagerName : projectManagerNames) {
            System.out.println(i + ". " + projectManagerName);
            i++;
        }
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scr.nextLine());
        if(choice < 1 || choice > projectManagerNames.size()) {
            System.out.println("Invalid choice");
            return null;
        }
        return projectManagerNames.get(choice - 1);
    }
    public static String chooseDeveloperName(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return null;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return null;
        }

        List<String> developerNames = softwareCompanies.get(companyName).getChild(projectManagerName).getChildrenNames();
        if(developerNames.size() == 0) {
            System.out.println("No developers under given project manager");
            return null;
        }
        System.out.println("Choose a developer of " + projectManagerName + " project manager");
        int i = 1;
        for (String developerName : developerNames) {
            System.out.println(i + ". " + developerName);
            i++;
        }
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scr.nextLine());
        if(choice < 1 || choice > developerNames.size()) {
            System.out.println("Invalid choice");
            return null;
        }
        return developerNames.get(choice - 1);
    }
    public static String chooseDeveloperName(Scanner scr, Map<String, Component> softwareCompanies, String companyName, String projectManagerName) {
        if(companyName == null || projectManagerName == null) {
            return null;
        }

        List<String> developerNames = softwareCompanies.get(companyName).getChild(projectManagerName).getChildrenNames();
        System.out.println("Choose a developer of " + projectManagerName + " project manager");
        int i = 1;
        for (String developerName : developerNames) {
            System.out.println(i + ". " + developerName);
            i++;
        }
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scr.nextLine());
        if(choice < 1 || choice > developerNames.size()) {
            System.out.println("Invalid choice");
            return null;
        }
        return developerNames.get(choice - 1);
    }
    public static void showOptions() {
        System.out.println("1. Add a company");
        System.out.println("2. Add a project manager");
        System.out.println("3. Add a developer");
        System.out.println("4. Remove a company");
        System.out.println("5. Remove a project manager");
        System.out.println("6. Remove a developer");
        System.out.println("7. Show details of a company");
        System.out.println("8. Show details of a project manager");
        System.out.println("9. Show details of a developer");
        System.out.println("10. Show hierarchy of a company");
        System.out.println("11. Show hierarchy of a project manager");
        System.out.print("Enter your choice(0 to exit): ");
    }

    // operational methods
    // adding
    public static void addCompany(Scanner scr, Map<String, Component> softwareCompanies) {
        System.out.print("Enter company name: ");
        String companyName = scr.nextLine();
        if(softwareCompanies.containsKey(companyName)) {
            System.out.println("Company already exists");
            return;
        } else {
            softwareCompanies.put(companyName, new SoftwareCompany(companyName));
            System.out.println("Company " + companyName + " added successfully");
        }
    }
    public static void addProjectManager(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        System.out.print("Enter project manager name: ");
        String projectManagerName = scr.nextLine();
        if(softwareCompanies.get(companyName).addChild(projectManagerName)) {
            System.out.print("Enter project name: ");
            String projectName = scr.nextLine();
            // without this line, the project name will be null
            ((ProjectManager)softwareCompanies.get(companyName).getChild(projectManagerName)).setProjectName(projectName);
        }
    }
    public static void addDeveloper(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return;
        }
        System.out.print("Enter developer name: ");
        String developerName = scr.nextLine();
        softwareCompanies.get(companyName).getChild(projectManagerName).addChild(developerName);
    }

    // removing
    public static void removeCompany(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        softwareCompanies.get(companyName).removeAllChildren();
        softwareCompanies.remove(companyName);
        System.out.println("Company " + companyName + " removed successfully");
    }
    public static void removeProjectManager(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return;
        }
        softwareCompanies.get(companyName).getChild(projectManagerName).removeAllChildren();
        softwareCompanies.get(companyName).removeChild(projectManagerName);
    }
    public static void removeDeveloper(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return;
        }
        String developerName = chooseDeveloperName(scr, softwareCompanies, companyName, projectManagerName);
        if(developerName == null) {
            return;
        }
        softwareCompanies.get(companyName).getChild(projectManagerName).removeChild(developerName);
    }

    // showing details
    public static void showCompanyDetails(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        softwareCompanies.get(companyName).details();
    }
    public static void showProjectManagerDetails(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return;
        }
        softwareCompanies.get(companyName).getChild(projectManagerName).details();
    }
    public static void showDeveloperDetails(Scanner scr, Map<String, Component> softwareCompanies) {
        String companyName = chooseCompanyName(scr, softwareCompanies);
        if(companyName == null) {
            return;
        }
        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, companyName);
        if(projectManagerName == null) {
            return;
        }
        String developerName = chooseDeveloperName(scr, softwareCompanies, companyName, projectManagerName);
        if(developerName == null) {
            return;
        }
        softwareCompanies.get(companyName).getChild(projectManagerName).getChild(developerName).details();
    }

    public static void main(String[] args) {
        Map<String, Component> softwareCompanies = new HashMap<>();
        Scanner scr = new Scanner(System.in);

        while(true) {
            showOptions();
            try{
                int choice = Integer.parseInt(scr.nextLine());
                if(choice == 0) {
                    break;
                }

                switch(choice) {
                    case 1:
                        addCompany(scr, softwareCompanies);
                        break;
                    case 2:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        addProjectManager(scr, softwareCompanies);
                        break;
                    case 3:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        addDeveloper(scr, softwareCompanies);
                        break;
                    case 4:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        removeCompany(scr, softwareCompanies);
                        break;
                    case 5:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        removeProjectManager(scr, softwareCompanies);
                        break;
                    case 6:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        removeDeveloper(scr, softwareCompanies);
                        break;
                        case 7:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        showCompanyDetails(scr, softwareCompanies);
                        break;
                    case 8:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        showProjectManagerDetails(scr, softwareCompanies);
                        break;
                    case 9:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        showDeveloperDetails(scr, softwareCompanies);
                        break;
                    case 10:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        String companyName = chooseCompanyName(scr, softwareCompanies);
                        softwareCompanies.get(companyName).hierarchy(0);
                        break;
                    case 11:
                        if(softwareCompanies.isEmpty()) {
                            System.out.println("No company exists");
                            break;
                        }
                        String compName = chooseCompanyName(scr, softwareCompanies);
                        String projectManagerName = chooseProjectManagerName(scr, softwareCompanies, compName);
                        softwareCompanies.get(compName).getChild(projectManagerName).hierarchy(0);
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("Invalid input");
            }
        }
    }
}
