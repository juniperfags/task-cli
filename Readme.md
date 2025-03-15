# Task CLI - Command Line Task Manager

Task CLI is a simple command-line application to manage tasks efficiently. It allows you to add, update, delete, and mark tasks with different statuses.

## Installation

Clone the repository and compile the Java program:

```sh
# Clone the repository
git clone https://github.com/your-repo/task-cli.git
cd task-cli

# Compile the Java program
javac -d out -sourcepath src src/CommandLineEntryPoint.java

# Run the CLI
java -cp out CommandLineEntryPoint
```

## Usage

### Adding a new task

To add a new task, use the following command:

```sh
java -cp out CommandLineEntryPoint add "Buy groceries"
```

**Output:**

```
Task added successfully (ID: 1)
```

### Updating and deleting tasks

To update an existing task:

```sh
java -cp out CommandLineEntryPoint update 1 "Buy groceries and cook dinner"
```

To delete a task:

```sh
java -cp out CommandLineEntryPoint delete 1
```

### Marking a task as in-progress or done

To mark a task as **in progress**:

```sh
java -cp out CommandLineEntryPoint mark-in-progress 1
```

To mark a task as **done**:

```sh
java -cp out CommandLineEntryPoint mark-done 1
```

### Listing all tasks

To list all tasks:

```sh
java -cp out CommandLineEntryPoint list
```

#### Example JSON Output:
```json
[
	{
		"id": 1,
		"description": "Buy groceries",
		"status": "todo",
		"createdAt": "Fri Mar 14 23:01:43 PET 2025",
		"updatedAt": "Fri Mar 14 23:01:43 PET 2025"
	},
	{
		"id": 2,
		"description": "Buy groceries",
		"status": "todo",
		"createdAt": "Fri Mar 14 23:17:54 PET 2025",
		"updatedAt": "Fri Mar 14 23:17:54 PET 2025"
	},
	{
		"id": 3,
		"description": "Buy groceries",
		"status": "todo",
		"createdAt": "Fri Mar 14 23:44:45 PET 2025",
		"updatedAt": "Fri Mar 14 23:44:45 PET 2025"
	}
]
```

### Listing tasks by status

To filter tasks based on status, use:

```sh
java -cp out CommandLineEntryPoint list done
```

```sh
java -cp out CommandLineEntryPoint list todo
```

```sh
java -cp out CommandLineEntryPoint list in-progress
```

## Features

- Add, update, delete tasks
- Mark tasks as "to do", "in progress", or "done"
- List tasks with filtering options

## License

This project is licensed under the MIT License.

