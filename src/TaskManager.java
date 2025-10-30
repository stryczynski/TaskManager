import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String Title, String Description) {
        tasks.add(new Task(Title, Description));
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).complete();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isCompleted() ? "Completed" : "Pending";
            System.out.printf("%d. %s - %s [%s]%n", i + 1, task.getTitle(), task.getDescription(), status);
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task t : tasks) {
                writer.write(t.isCompleted() + ";" + t.getTitle() + ";" + t.getDescription());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts.length == 3) {
                    boolean isCompleted = Boolean.parseBoolean(parts[0]);
                    String title = parts[1];
                    String description = parts[2];
                    Task task = new Task(title, description);
                    if (isCompleted) {
                        task.complete();
                    }
                    tasks.add(task);
                }
            }
        }
    }
}
