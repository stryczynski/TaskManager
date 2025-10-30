import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        taskManager.loadFromFile("tasks.txt");

        while (true) {
            System.out.println("\n1. Add Task\n2. Complete Task\n3. Remove Task\n4. Show Tasks\n5. Save and Exit");
            System.out.print("Choose an option: ");
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                }
                case "2" -> {
                    System.out.print("Enter task index to complete: ");
                    taskManager.completeTask(Integer.parseInt(scanner.nextLine()) - 1);
                }
                case "3" -> {
                    System.out.print("Enter task index to remove: ");
                    taskManager.removeTask(Integer.parseInt(scanner.nextLine()) - 1);
                }
                case "4" -> taskManager.showTasks();
                case "5" -> {
                    taskManager.saveToFile("tasks.txt");
                    System.out.println("Tasks saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
