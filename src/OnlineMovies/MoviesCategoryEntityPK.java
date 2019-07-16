package OnlineMovies;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MoviesCategoryEntityPK implements Serializable {
    private int moviesId;
    private int categoryId;

    @Column(name = "MoviesID")
    @Id
    public int getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(int moviesId) {
        this.moviesId = moviesId;
    }

    @Column(name = "CategoryID")
    @Id
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
        MoviesCategoryEntityPK that = (MoviesCategoryEntityPK) o;
        return moviesId == that.moviesId &&
                categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moviesId, categoryId);
    }
}
