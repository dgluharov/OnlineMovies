package OnlineMovies;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "OperationType", schema = "dbo", catalog = "OnlineMovies")
public class OperationTypeEntity {
    private int id;
    private String name;
    private List<OperationsEntity> operationsEntityList = new ArrayList<>();

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "OperationTypeID")
    public List<OperationsEntity> getOperationsEntityList() {
        return operationsEntityList;
    }

    public void setOperationsEntityList(List<OperationsEntity> operationsEntityList) {
        this.operationsEntityList = operationsEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationTypeEntity that = (OperationTypeEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
