package helpers.mappers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import domain.Task;
import exceptions.KeyNotAvailableException;

public class StringMapper {

    private static String collectionToString(Iterator<Task> taskIterator) {

        StringBuilder jsonContent = new StringBuilder();

        jsonContent.append("[");

        while (taskIterator.hasNext()) {

            Task currentTask = taskIterator.next();

            jsonContent.append(currentTask.toString());

            jsonContent.append(",");

        }

        // This line will remove the last comma
        jsonContent.deleteCharAt(jsonContent.toString().length() - 1);

        jsonContent.append("\n]");

        return jsonContent.toString();

    }

    public static String arrayToString(List<Task> content) {

        if (content.isEmpty())

            return "[]";

        else {

            Iterator<Task> taskIterator = content.iterator();

            return collectionToString(taskIterator);
        }
    }

    public static String hashMapToString(Map<Integer, Task> content) {

        if (content.isEmpty())

            return "[]";

        else {

            Iterator<Task> taskIterator = content.values().iterator();

            return collectionToString(taskIterator);

        }

    }

    public static Map<Integer, Task> stringToHashMap(String stringifiedJson) {

        Map<Integer, Task> content = new HashMap<>();

        if (stringifiedJson.equals(""))

            return content;

        String[] dataRows = stringifiedJson
                .replaceAll("\t\t", "")
                .replaceAll("\t", "")
                .replace("[", "")
                .replace("]", "")
                .split("\\},\\s*\\{");

        for (int i = 0; i < dataRows.length; i++) {

            String row = dataRows[i];

            if (i == 0)
                row = row.replace("{", "");

            if (i == dataRows.length - 1)
                row = row.replace("}", "");

            dataRows[i] = row;

        }

        for (String row : dataRows) {

            String[] values = row.split(",");

            Task task = new Task();

            for (String data : values) {

                String[] keyValues = data.replaceFirst("\s", "").split(":", 2);

                String key = keyValues[0].replace("\"", "");

                String value = keyValues[1].replace("\"", "");

                try {
                    switch (key) {
                        case "id":

                            task.setTaskId(Integer.parseInt(value));
                            break;

                        case "description":

                            task.setDescription(value);
                            break;

                        case "status":

                            task.setStatus(value);
                            break;

                        case "createdAt":

                            task.setCreatedAt(new SimpleDateFormat(Task.DATE_FORMAT).parse(value));
                            break;

                        case "updatedAt":

                            task.setUpdatedAt(new SimpleDateFormat(Task.DATE_FORMAT).parse(value));
                            break;

                        default:

                            throw new KeyNotAvailableException(key);

                    }
                } catch (KeyNotAvailableException e) {

                    System.err.println("Attribute " + e.getMessage() + " is not available in model");

                } catch (Exception e) {

                    System.err.println(e.getMessage());

                }

            }

            content.put(task.getTaskId(), task);

        }

        return content;
    }

}