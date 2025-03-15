package domain;

import java.util.Date;

import domain.interfaces.Timestamp;

public class Task implements Timestamp {

    private Integer taskId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task(Integer taskId, String description, String status, Date createdAt, Date updatedAt) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task() {

    }

    public String toString() {
        return String.format(
                "\n\t{\n\t\t\"id\": %d,\n\t\t\"description\": \"%s\",\n\t\t\"status\": \"%s\",\n\t\t\"createdAt\": \"%s\",\n\t\t\"updatedAt\": \"%s\"\n\t}",
                this.taskId, this.description, this.status, this.createdAt.toString(),
                this.updatedAt.toString());
    }

}