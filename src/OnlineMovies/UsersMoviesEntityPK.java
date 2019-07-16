package OnlineMovies;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersMoviesEntityPK implements Serializable {
    private int userId;
    private int moviesId;

    @Column(name = "UserID")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "MoviesID")
    @Id
    public int getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(int moviesId) {
        this.moviesId = moviesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersMoviesEntityPK that = (UsersMoviesEntityPK) o;
        return userId == that.userId &&
                moviesId == that.moviesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, moviesId);
    }
}
