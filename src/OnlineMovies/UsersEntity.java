package OnlineMovies;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "OnlineMovies")
public class UsersEntity extends Base {
    //    private int id;
//    private String name;
    private String email;
    private BigDecimal balance;
    private List<MoviesEntity> moviesEntityList = new ArrayList<>();
    private List<OperationsEntity> operationsEntityList = new ArrayList<>();

    //    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "Name")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "Users_Movies",
            joinColumns = {
                    @JoinColumn(name = "UserID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "MoviesID", nullable = false, updatable = false)})
    public List<MoviesEntity> getMoviesEntityList() {
        return moviesEntityList;
    }

    public void setMoviesEntityList(List<MoviesEntity> moviesEntityList) {
        this.moviesEntityList = moviesEntityList;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "UserID")
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
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, balance);
    }
}
