package OnlineMovies;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Movies_Category", schema = "dbo", catalog = "OnlineMovies")
@IdClass(MoviesCategoryEntityPK.class)
public class MoviesCategoryEntity {
    private int moviesId;
    private int categoryId;

    @Id
    @Column(name = "MoviesID")
    public int getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(int moviesId) {
        this.moviesId = moviesId;
    }

    @Id
    @Column(name = "CategoryID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesCategoryEntity that = (MoviesCategoryEntity) o;
        return moviesId == that.moviesId &&
                categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moviesId, categoryId);
    }
}
