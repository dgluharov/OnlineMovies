package OnlineMovies;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Users_Movies", schema = "dbo", catalog = "OnlineMovies")
@IdClass(UsersMoviesEntityPK.class)
public class UsersMoviesEntity {
    private int userId;
    private int moviesId;

    @Id
    @Column(name = "UserID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "MoviesID")
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
        UsersMoviesEntity that = (UsersMoviesEntity) o;
        return userId == that.userId &&
                moviesId == that.moviesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, moviesId);
    }
}
