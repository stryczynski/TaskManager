import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Task Manager!");
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        taskManager.loadFromFile("tasks.txt");

        while(true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Show Tasks");
            System.out.println("5. Save and Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    break;
                case "2":
                    System.out.print("Enter task index to complete: ");
                    int completeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    taskManager.completeTask(completeIndex);
                    break;
                case "3":
                    System.out.print("Enter task index to remove: ");
                    int removeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    taskManager.removeTask(removeIndex);
                    break;
                case "4":
                    taskManager.showTasks();
                    break;
                case "5":
                    taskManager.saveToFile("tasks.txt");
                    System.out.println("Tasks saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}