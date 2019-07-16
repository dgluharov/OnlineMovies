package OnlineMovies;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Movies", schema = "dbo", catalog = "OnlineMovies")
public class MoviesEntity extends Base {
    //    private int id;
//    private String name;
    private BigDecimal price;
    private int watchedTimes;
    private List<CategoryEntity> categoryEntityList = new ArrayList<>();
    private List<UsersEntity> usersEntityList = new ArrayList<>();

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
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "WatchedTimes")
    public int getWatchedTimes() {
        return watchedTimes;
    }

    public void setWatchedTimes(int watchedTimes) {
        this.watchedTimes = watchedTimes;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Movies_Category",
            joinColumns = {
                    @JoinColumn(name = "MoviesID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CategoryID", nullable = false, updatable = false)})
    public List<CategoryEntity> getCategoryEntityList() {
        return categoryEntityList;
    }

    public void setCategoryEntityList(List<CategoryEntity> categoryEntityList) {
        this.categoryEntityList = categoryEntityList;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "moviesEntityList")
    public List<UsersEntity> getUsersEntityList() {
        return usersEntityList;
    }

    public void setUsersEntityList(List<UsersEntity> usersEntityList) {
        this.usersEntityList = usersEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesEntity that = (MoviesEntity) o;
        return id == that.id &&

                watchedTimes == that.watchedTimes &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, watchedTimes);
    }
}
