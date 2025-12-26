package collection;

import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {

	HashMap<String, Task> taskMap = new HashMap<>();

	public void addTask(Task task) {
		taskMap.put(task.getName(), task);
		System.out.println("Task added" + task);
	}

	public void viewTask() {
	    if (taskMap.isEmpty()) {
	        System.out.println("No task found!");
	    } else {
	        for (Task task : taskMap.values()) {
	            System.out.println(task);
	        }
	    }
	}


	public void updateTask(String taskName, String newStatus) {
		Task task = taskMap.get(taskName);
		if (task != null) {
			task.setStatus(newStatus);
			System.out.println("Task update" + task);
		} else {
			System.out.println("Task not found.");
		}
	}

	public void removeTask(String taskName) {
	    if (taskMap.containsKey(taskName)) {
	        taskMap.remove(taskName);
	        System.out.println("Task removed");
	    } else {
	        System.out.println("Task not found.");
	    }
	}

	public static void main(String[] args) {
		TaskManager manager = new TaskManager();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nChoose an option: 1-Add, 2-View, 3-Delete, 4-Update Status, 5-Exit");
			String choice = sc.nextLine();

			if (choice.equals("1")) {
				try {
					System.out.println("Enter task name : ");
					String name = sc.nextLine();
					System.out.println("Enter task description : ");
					String description = sc.nextLine();
					System.out.println("Enter priority : ");
					int priority = Integer.parseInt(sc.nextLine());

					Task task = new Task(name, description, priority, "Pending");

					manager.addTask(task);
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}

			} else if (choice.equals("2")) {
				manager.viewTask();

			} else if (choice.equals("3")) {
				System.out.println("Enter taskName to delete!");
				String taskName = sc.nextLine();
				manager.removeTask(taskName);
			} else if (choice.equals("4")) {
				System.out.println("Enter taskName to update : ");
				String taskName = sc.nextLine();
				System.out.println("Enter new status");
				String newStatus = sc.nextLine();
				manager.updateTask(taskName, newStatus);
			} else {
				System.out.println("Exiting...");
				sc.close();
				return;
			}

		}

	}
}

class Task {
	private String name;
	private String description;
	private int priority;
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Task(String name, String description, int priority, String status) {
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", description=" + description + ", priority=" + priority + ", status=" + status
				+ "]";
	}

}
