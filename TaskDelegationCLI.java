import java.util.*;
import java.util.Scanner;

class Task {
    private int taskId;
    private String title;
    private String description;
    private String assignee;
    private String priority;
    private String dueDate;
    private String status;
    private int progress;

    public Task(int taskId, String title, String description, String assignee, String priority, String dueDate) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = "Not Started";
        this.progress = 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void updateProgress(int progress) {
        this.progress = progress;
    }

    public void displayTask() {
        System.out.println("Task ID: " + taskId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Assignee: " + assignee);
        System.out.println("Priority: " + priority);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Status: " + status);
        System.out.println("Progress: " + progress + "%");
    }
}

class User {
    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String toString() {
        return name + " (" + role + ")";
    }
}

class TaskDelegationSystem {
    private Map<Integer, Task> tasks;
    private Map<String, User> users;
    private int taskCounter;

    public TaskDelegationSystem() {
        tasks = new HashMap<>();
        users = new HashMap<>();
        taskCounter = 1;
    }

    public void addUser(String name, String role) {
        users.put(name, new User(name, role));
        System.out.println("User '" + name + "' added successfully.");
    }

    public void createTask(String title, String description, String assignee, String priority, String dueDate) {
        if (users.containsKey(assignee)) {
            Task task = new Task(taskCounter, title, description, assignee, priority, dueDate);
            tasks.put(taskCounter, task);
            taskCounter++;
            System.out.println("Task '" + title + "' created and assigned to " + assignee + ".");
        } else {
            System.out.println("Assignee does not exist in the system.");
        }
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        if (tasks.containsKey(taskId)) {
            tasks.get(taskId).updateStatus(newStatus);
            System.out.println("Task " + taskId + " status updated to '" + newStatus + "'.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void updateTaskProgress(int taskId, int progress) {
        if (tasks.containsKey(taskId)) {
            tasks.get(taskId).updateProgress(progress);
            System.out.println("Task " + taskId + " progress updated to " + progress + "%.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void displayTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            tasks.get(taskId).displayTask();
        } else {
            System.out.println("Task not found.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks.values()) {
                System.out.println("[" + task.getTaskId() + "] " + task.getTitle() + " - " + task.getAssignee() + " - " + task.getStatus() + " - " + task.getProgress() + "% complete");
            }
        }
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users.values()) {
                System.out.println(user.toString());
            }
        }
    }
}

public class TaskDelegationCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskDelegationSystem system = new TaskDelegationSystem();

        while (true) {
            System.out.println("\nTask Delegation System CLI");
            System.out.println("1. Add User");
            System.out.println("2. Create Task");
            System.out.println("3. Update Task Status");
            System.out.println("4. Update Task Progress");
            System.out.println("5. View Task");
            System.out.println("6. List Tasks");
            System.out.println("7. List Users");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user role (Team Leader/Team Member/Project Manager): ");
                    String role = scanner.nextLine();
                    system.addUser(name, role);
                    break;

                case "2":
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task assignee (name): ");
                    String assignee = scanner.nextLine();
                    System.out.print("Enter task priority (high/medium/low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    system.createTask(title, description, assignee, priority, dueDate);
                    break;

                case "3":
                    System.out.print("Enter task ID to update status: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new status (Not Started/In Progress/Completed): ");
                    String newStatus = scanner.nextLine();
                    system.updateTaskStatus(taskId, newStatus);
                    break;

                case "4":
                    System.out.print("Enter task ID to update progress: ");
                    taskId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter progress (0-100): ");
                    int progress = Integer.parseInt(scanner.nextLine());
                    system.updateTaskProgress(taskId, progress);
                    break;

                case "5":
                    System.out.print("Enter task ID to view: ");
                    taskId = Integer.parseInt(scanner.nextLine());
                    system.displayTask(taskId);
                    break;

                case "6":
                    system.listTasks();
                    break;

                case "7":
                    system.listUsers();
                    break;

                case "8":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
