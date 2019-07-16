package OnlineMovies;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Operations", schema = "dbo", catalog = "OnlineMovies")
public class OperationsEntity {
    private int id;
    private Timestamp timeStamp;
    private BigDecimal amount;
    private UsersEntity usersEntity;
    private OperationTypeEntity operationTypeEntity;

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
    @Column(name = "TimeStamp")
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Basic
    @Column(name = "Amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "OperationTypeID")
    public OperationTypeEntity getOperationTypeEntity() {
        return operationTypeEntity;
    }

    public void setOperationTypeEntity(OperationTypeEntity operationTypeEntity) {
        this.operationTypeEntity = operationTypeEntity;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationsEntity that = (OperationsEntity) o;
        return id == that.id &&
                Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(usersEntity, that.usersEntity) &&
                Objects.equals(operationTypeEntity, that.operationTypeEntity) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStamp, amount, usersEntity, operationTypeEntity);
    }
}
