package OnlineMovies;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Methods {
    private static final SessionFactory ourSessionFactory;
    private static Methods object;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    public static Methods getInstance() {
        if (object == null) {
            object = new Methods();
        }
        return object;
    }

    public void showMostWatched() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TopMoviesEntity> query = builder.createQuery(TopMoviesEntity.class);
            Root<TopMoviesEntity> root = query.from(TopMoviesEntity.class);
            query.select(root);
            Query<TopMoviesEntity> q = session.createQuery(query);
            List<TopMoviesEntity> topMoviesEntityList = q.getResultList();
            for (TopMoviesEntity topMoviesEntity : topMoviesEntityList) {
                System.out.printf("Movie: %20s; | Category: %25s; | Watched times: %d \n", topMoviesEntity.getMovie(),
                        topMoviesEntity.getCategory(), topMoviesEntity.getTopWatched());
//                System.out.printf  ("Category: "     + topMoviesEntity.getCategory() + ";\t");
//                System.out.println("Watched times:" + topMoviesEntity.getTopWatched() + ";");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void registerUser(String name, String email) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            UsersEntity user = new UsersEntity();
            user.setName(name);
            user.setEmail(email);
            user.setBalance(new BigDecimal(0));
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Registered user - successful.");
    }

    public void insertCategory(String name) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            session.save(categoryEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Creating a new category - successful.");
    }

    public void insertMovie(String name, BigDecimal price) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            MoviesEntity moviesEntity = new MoviesEntity();
            moviesEntity.setName(name);
            moviesEntity.setPrice(price);
            moviesEntity.setWatchedTimes(0);
            session.save(moviesEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Adding a new movie - successful.");
    }


    public void makeDeposit(UsersEntity userEntity, BigDecimal amount) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            userEntity.setBalance(userEntity.getBalance().add(amount));
            OperationsEntity operationsEntity = insertOperation(
                    getOperationType("Make Deposit", session),
                    userEntity,
                    amount);
            userEntity.getOperationsEntityList().add(operationsEntity);
            session.update(userEntity);
            session.save(operationsEntity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Deposit successful.");
    }

    public void purchaseMovie(MoviesEntity moviesEntity, UsersEntity usersEntity) {
        if (usersEntity.getMoviesEntityList().contains(moviesEntity)) {
            System.out.println("This movie is in your collection!" + moviesEntity.getName());
        } else {
            Transaction transaction = null;
            try (Session session = getSession()) {
                transaction = session.beginTransaction();
                usersEntity.setBalance(usersEntity.getBalance().subtract(moviesEntity.getPrice()));
                usersEntity.getMoviesEntityList().add(moviesEntity);
                OperationTypeEntity operationTypeEntity = getOperationType("Purchase Movie", session);
                OperationsEntity operationsEntity = insertOperation(
                        operationTypeEntity,
                        usersEntity,
                        moviesEntity.getPrice());
                usersEntity.getOperationsEntityList().add(operationsEntity);
                session.save(operationsEntity);
                session.flush();
                session.getTransaction().commit();
                session.close();
                System.out.println("Purchase successful.");
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }


    public void watchMovie(MoviesEntity moviesEntity, UsersEntity usersEntity) {
        if (!usersEntity.getMoviesEntityList().contains(moviesEntity)) {
            purchaseMovie(moviesEntity, usersEntity);
        }
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            moviesEntity.setWatchedTimes(moviesEntity.getWatchedTimes() + 1);
            OperationTypeEntity operationTypeEntity = getOperationType("Watch movie", session);
            OperationsEntity operationsEntity = insertOperation(
                    operationTypeEntity,
                    usersEntity,
                    new BigDecimal(0));
            usersEntity.getOperationsEntityList().add(operationsEntity);
            session.update(moviesEntity);
            session.save(operationsEntity);
            session.getTransaction().commit();
            System.out.println("You have watched movie: " + moviesEntity.getName());
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void insertOperationType(String name) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            OperationTypeEntity operationTypeEntity = new OperationTypeEntity();
            operationTypeEntity.setName(name);
            session.save(operationTypeEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Adding a new Operation Type  - successful.");
    }

    public void setMovieCategory(String movieName, String categoryName) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            MoviesEntity movieEntity = getMovieEntity(movieName);
            movieEntity.getCategoryEntityList().add(getCategory(categoryName));
            session.saveOrUpdate(movieEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        System.out.println("Set movie category - successful.");
    }


    public OperationsEntity insertOperation(OperationTypeEntity operationTypeEntity,
                                            UsersEntity usersEntity,
                                            BigDecimal amount) {
        OperationsEntity operationsEntity = new OperationsEntity();
        operationsEntity.setOperationTypeEntity(operationTypeEntity);
        operationsEntity.setUsersEntity(usersEntity);
        operationsEntity.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        operationsEntity.setAmount(amount);
        return operationsEntity;
    }

    public List<OperationTypeEntity> getOperationType(Session ts) {
        List<OperationTypeEntity> operationTypeEntities = null;
        Session session = ts;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OperationTypeEntity> query = builder.createQuery(OperationTypeEntity.class);
        Root<OperationTypeEntity> root = query.from(OperationTypeEntity.class);
        query.select(root);

        operationTypeEntities = session.createQuery(query).getResultList();
        return operationTypeEntities;
    }

    public OperationTypeEntity getOperationType(String name, Session ts) {
        for (OperationTypeEntity operationTypeEntity : getOperationType(ts)) {
            if (operationTypeEntity.getName().equals(name)) {
                return operationTypeEntity;
            }
        }
        return null;
    }

    public List<CategoryEntity> getCategory() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
            Root<CategoryEntity> root = query.from(CategoryEntity.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    public CategoryEntity getCategory(String name) {
        for (CategoryEntity categoryEntity : getCategory()) {
            if (categoryEntity.getName().equals(name)) {
                return categoryEntity;
            }
        }
        return null;
    }

    public List<OperationsEntity> getOperation() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OperationsEntity> query = builder.createQuery(OperationsEntity.class);
            Root<OperationsEntity> root = query.from(OperationsEntity.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    public List<UsersEntity> getUserEntities() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UsersEntity> query = builder.createQuery(UsersEntity.class);
            Root<UsersEntity> root = query.from(UsersEntity.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    public UsersEntity getUserEntity(String name) {
        for (UsersEntity usersEntity : getUserEntities()) {
            if (usersEntity.getName().equals(name)) {
                return usersEntity;
            }
        }
        return null;
    }

    public List<MoviesEntity> getMoviesEntity() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MoviesEntity> query = builder.createQuery(MoviesEntity.class);
            Root<MoviesEntity> root = query.from(MoviesEntity.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    public MoviesEntity getMovieEntity(String name) {
        for (MoviesEntity moviesEntity : getMoviesEntity()) {
            if (moviesEntity.getName().equals(name)) {
                return moviesEntity;
            }
        }
        return null;
    }

    public void updateMovie(MoviesEntity moviesEntity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(moviesEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

