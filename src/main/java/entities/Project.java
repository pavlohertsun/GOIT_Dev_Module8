package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Project {
    private long id;
    private int clientId;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Project(long id, int clientId, LocalDate startDate, LocalDate finishDate) {
        this.id = id;
        this.clientId = clientId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Project(int clientId, LocalDate startDate, LocalDate finishDate) {
        this.clientId = clientId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
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
        Project project = (Project) o;
        return clientId == project.clientId && Objects.equals(startDate, project.startDate) && Objects.equals(finishDate, project.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, startDate, finishDate);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}