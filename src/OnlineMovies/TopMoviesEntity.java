package OnlineMovies;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TopMovies", schema = "dbo", catalog = "OnlineMovies")
@Immutable
public class TopMoviesEntity {
    private int id;
    private String category;
    private String movie;
    private Integer topWatched;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "Movie")
    public String getMovie() {
        return movie;
    }

    private void setMovie(String movie) {
        this.movie = movie;
    }

    @Basic
    @Column(name = "TopWatched")
    public Integer getTopWatched() {
        return topWatched;
    }

    private void setTopWatched(Integer topWatched) {
        this.topWatched = topWatched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopMoviesEntity that = (TopMoviesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(topWatched, that.topWatched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, movie, topWatched);
    }
}
