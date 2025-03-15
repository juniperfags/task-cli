package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import domain.Task;

import helpers.fs.JsonFileManager;
import helpers.mappers.StringMapper;

public class CommandService {

    private Map<Integer, Task> content = new HashMap<Integer, Task>();

    private Integer availableIndex = 0;

    private void findAvailableIndex() {

        Iterator<Integer> keyIterator = content.keySet().iterator();

        while (keyIterator.hasNext())
            this.availableIndex = keyIterator.next();

        this.availableIndex = availableIndex + 1;
    }

    private void updateContent(JsonFileManager jsonFileManager) {

        String updatedContent = StringMapper.hashMapToString(this.content);

        jsonFileManager.writeContent(updatedContent);

    }

    public void execute(String[] args) {

        if (args.length < 1) {

            System.err.println("Some args were not found at command");

            System.exit(0);

        }

        String actionArg = args[0];

        try {

            JsonFileManager jsonFileManager = new JsonFileManager("tasks.json");

            jsonFileManager.createFileIfNotExists();

            String stringifiedContent = jsonFileManager.readContentFile();

            this.content = StringMapper.stringToHashMap(stringifiedContent);

            switch (actionArg) {
                case "add":

                    if (args.length != 2) {
                        System.err.println("Required parameters were not found");
                        System.exit(0);
                    }

                    if (!(args[1] instanceof String)) {
                        System.err.println("Description parameter must be string");
                        System.exit(0);
                    }

                    findAvailableIndex();

                    this.content.put(availableIndex,
                            new Task(this.availableIndex, args[1], "todo", new Date(), new Date()));

                    this.updateContent(jsonFileManager);

                    System.out.println("Task added successfully (ID: " + this.availableIndex + ")");

                    break;
                case "update":

                    if (args.length != 3) {
                        System.err.println("Required parameters were not found");
                        System.exit(0);
                    }

                    Integer taskId = Integer.parseInt(args[1]);

                    String description = args[2];

                    Task oldTask = content.get(taskId);

                    if (oldTask == null)
                        System.err.println("Task not exists");

                    this.content.replace(taskId,
                            new Task(taskId, description, oldTask.getStatus(), oldTask.getCreatedAt(), new Date()));

                    this.updateContent(jsonFileManager);

                    break;
                case "delete":

                    if (args.length != 2) {
                        System.err.println("Required parameters were not found");
                        System.exit(0);
                    }

                    Integer id = Integer.parseInt(args[1]);

                    this.content.remove(id);

                    this.updateContent(jsonFileManager);

                    break;
                case "list":

                    if (args.length == 1) {

                        String jsonContent = StringMapper.hashMapToString(this.content);

                        System.out.println(jsonContent);

                    } else {

                        Stream<Task> stream = this.content.values().stream();

                        switch (args[1]) {
                            case "done":
                            case "todo":
                            case "in-progress":

                                List<Task> list = stream
                                        .filter((task) -> task.getStatus().equals(
                                                args[1]))
                                        .toList();

                                String jsonContent = StringMapper.arrayToString(list);

                                System.out.println(jsonContent);

                                break;
                            default:

                                System.err.println("Tasks with status " + args[1] + " were not found");

                                break;
                        }
                    }
                    break;
                case "mark-in-progress":
                case "mark-done":

                    if (args.length != 2)
                        System.err.println("Required parameters were not found");

                    Task selectedTask = this.content.get(Integer.parseInt(args[1]));

                    if (selectedTask == null)
                        System.err.println("Task not exists");

                    String newStatus = actionArg.equals("mark-in-progress") ? "in-progress" : "done";

                    this.content.put(
                            selectedTask.getTaskId(),
                            new Task(selectedTask.getTaskId(), selectedTask.getDescription(), newStatus,
                                    selectedTask.getCreatedAt(), new Date()));

                    this.updateContent(jsonFileManager);

                    break;
                default:
                    System.err.println("No options were found!!");
                    break;
            }

        } catch (NumberFormatException e) {

            System.err.println("Task id must be int");
            //
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}