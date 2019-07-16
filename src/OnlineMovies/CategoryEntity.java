package OnlineMovies;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "OnlineMovies")
public class CategoryEntity extends Base {
    //    private int id;
//    private String name;
    private List<MoviesEntity> moviesEntityList = new ArrayList<>();

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "categoryEntityList")
    public List<MoviesEntity> getMoviesEntityList() {
        return moviesEntityList;
    }

    public void setMoviesEntityList(List<MoviesEntity> moviesEntityList) {
        this.moviesEntityList = moviesEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
