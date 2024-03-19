package entities;

import java.util.Objects;

public class ProjectWorker {
    private long id;
    private int projectId;
    private int workerId;

    public ProjectWorker(long id, int projectId, int workerId) {
        this.id = id;
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public ProjectWorker(int projectId, int workerId) {
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getWorkerId() {
        return workerId;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWorker that = (ProjectWorker) o;
        return projectId == that.projectId && workerId == that.workerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, workerId);
    }

    @Override
    public String toString() {
        return "ProjectWorker{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", workerId=" + workerId +
                '}';
    }
}