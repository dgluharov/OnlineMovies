import OnlineMovies.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;


public class Main {
    private static final SessionFactory ourSessionFactory;

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

    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }

        Methods methods = new Methods();
        //methods.registerUser("Pesho", "pesho@abv.bg");
        //methods.insertCategory("XXX");
        //methods.insertMovie("The Last Samurai", new BigDecimal(3.25));
        //methods.makeDeposit(methods.getUserEntity("Pesho"),new BigDecimal(32));
        //methods.insertOperationType("Test Operation");
//        methods.insertOperation(methods.getOperationType("Test Operation"),
//                methods.getUserEntity("Pesho"),new BigDecimal(2));
//        methods.purchaseMovie(methods.getMovieEntity("Snatch"),
//                methods.getUserEntity("Pesho"));
//        methods.watchMovie(methods.getMovieEntity("Genesys"), methods.getUserEntity("Pesho"));

        //System.out.println(methods.getUserEntity("Pesho").getMoviesEntityList().toString());
//        methods.watchMovie(methods.getMovieEntity("GOT"), methods.getUserEntity("Kalin Georgiev"));
        //methods.makeDeposit(methods.getUserEntity("Bokio Borisov"), new BigDecimal(18));
//

        //methods.setMovieCategory("Genesys", "Fantasy");
//        System.out.println(methods.getMovieEntity("Genesys").getCategoryEntityList().toString());

//        MoviesEntity movieEntity = methods.getMovieEntity("Genesys");
//        movieEntity.getCategoryEntityList().add(methods.getCategory("Fantasy"));
//        methods.updateMovie(movieEntity);

//        methods.insertCategory("Action");

//        methods.setMovieCategory("Armagedon", "Drama");
//        methods.setMovieCategory("Snatch", "Action");
//        methods.setMovieCategory("Schindler's List", "Drama");
//        methods.setMovieCategory("The Last Samurai", "Action");
//        methods.registerUser("Monika Ivanova", "mivanova@mail.bg");

//        methods.purchaseMovie(methods.getMovieEntity("The Hangover"),methods.getUserEntity("Monika Ivanova"));
//        methods.insertMovie("The Hangover",new BigDecimal(2.2));
//        methods.setMovieCategory("The Hangover","Comedy");
//        methods.watchMovie(methods.getMovieEntity("The Hangover"),methods.getUserEntity("Kalin Georgiev"));

        //methods.showMostWatched();
//    methods.registerUser("Tosho Metala", "metala@yahoo.com");
        //    methods.makeDeposit(methods.getUserEntity("Tosho Metala"), new BigDecimal(12));
        //methods.purchaseMovie(methods.getMovieEntity("Schindler's List"),methods.getUserEntity("Tosho Metala"));
//        methods.watchMovie(methods.getMovieEntity("The Last Samurai"), methods.getUserEntity("Tosho Metala"));


        //Select all//
//        Session session = getSession();
//        session.beginTransaction();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<UsersEntity> query = builder.createQuery(UsersEntity.class);
//        Root<UsersEntity> root = query.from(UsersEntity.class);
//        query.select(root);
//        Query<UsersEntity> q = session.createQuery(query);
//        List<UsersEntity> usersEntities = q.getResultList();
//        for (UsersEntity usersEntity: usersEntities) {
//            System.out.printf("name: %20s   email: %20s   balance: %s lv.\n",usersEntity.getName(),
//                    usersEntity.getEmail(), usersEntity.getBalance().toString());
//        }
//        session.getTransaction().commit();

        //Select * from ... where ...//
//        Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<UsersEntity> query = builder.createQuery(UsersEntity.class);
//        Root<UsersEntity> root = query.from(UsersEntity.class);
//        query.select(root).where(builder.equal(root.get("id"), 11));
//        Query<UsersEntity> q = session.createQuery(query);
//        UsersEntity usersEntity = q.getSingleResult();
//        System.out.println(usersEntity.getName());
//
//        session.getTransaction().commit();

        //Select ____ from ____///
//        Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<String> query = builder.createQuery(String.class);
//        Root<UsersEntity> root = query.from(UsersEntity.class);
//        query.select(root.get("email"));
//        Query<String> q = session.createQuery(query);
//        List<String> list = q.getResultList();
//        for (String email:list) {
//            System.out.println(email);
//        }
//        session.getTransaction().commit();

        //Select _____, _____ from _____//
//        Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root<UsersEntity> root = query.from(UsersEntity.class);
//        query.multiselect(root.get("name"), root.get("email"));
//        Query<Object[]> q = session.createQuery(query);
//        List<Object[]> list = q.getResultList();
//        for (Object[] objects: list) {
//            System.out.println("Name: " + objects[0]);
//            System.out.println("Email: " + objects[1]);
//        }
//
//        session.getTransaction().commit();

        //Aggregate functions//

//        Session session = getSession();
//        session.beginTransaction();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//        //Count number of users//
//        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
//        Root<UsersEntity> root = criteriaQuery.from(UsersEntity.class);
//        criteriaQuery.select(builder.count(root));
//        Query<Long> query = session.createQuery(criteriaQuery);
//        long count = query.getSingleResult();
//        System.out.println("Count = " + count);
//
//        //Get Max Balance//
//        CriteriaQuery<BigDecimal> criteriaQuery1 = builder.createQuery(BigDecimal.class);
//        Root<UsersEntity> root1 = criteriaQuery1.from(UsersEntity.class);
//        criteriaQuery1.select(builder.max(root1.get("balance")));
//        Query<BigDecimal> query1 = session.createQuery(criteriaQuery1);
//        BigDecimal maxBalance = query1.getSingleResult();
//        System.out.println("Max Balance: " + maxBalance);
//
//        //Get Average Balance//
//
//        CriteriaQuery<Double> criteriaQuery2 = builder.createQuery(Double.class);
//        Root<UsersEntity> root2 = criteriaQuery2.from(UsersEntity.class);
//        criteriaQuery2.select(builder.avg(root2.get("balance")));
//        Query<Double> query2 = session.createQuery(criteriaQuery2);
//        Double average = query2.getSingleResult();
//        System.out.println("Average balance: " + average);
//
//        //Count distinct users//
//        CriteriaQuery<Long> criteriaQuery3 = builder.createQuery(Long.class);
//        Root<UsersEntity> root3 = criteriaQuery3.from(UsersEntity.class);
//        criteriaQuery3.select(builder.countDistinct(root3));
//        Query<Long> query3 = session.createQuery(criteriaQuery3);
//        Long countDistinct = query3.getSingleResult();
//        System.out.println("Distinct count = " + countDistinct);
//
//        session.getTransaction().commit();


        //Using From and Join//
        Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<OperationsEntity> operationsEntityRoot = criteriaQuery.from(OperationsEntity.class);
        Root<OperationTypeEntity> operationTypeEntityRoot = criteriaQuery.from(OperationTypeEntity.class);
        Root<UsersEntity> usersEntityRoot = criteriaQuery.from(UsersEntity.class);
        criteriaQuery.multiselect(operationsEntityRoot, operationTypeEntityRoot, usersEntityRoot);
        criteriaQuery.where(builder.and(builder.equal(operationsEntityRoot.get("operationTypeEntity"),
                operationTypeEntityRoot.get("id")),
                builder.equal(usersEntityRoot.get("id"), operationsEntityRoot.get("usersEntity"))));

        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> list = query.getResultList();
        for (Object[] objects : list) {
            OperationsEntity operationsEntity = (OperationsEntity) objects[0];
            OperationTypeEntity operationTypeEntity = (OperationTypeEntity) objects[1];
            UsersEntity usersEntity = (UsersEntity) objects[2];
            System.out.printf("Operation ID = %d |\t Operation name = %20s |\t User: %s\n", operationsEntity.getId(),
                    operationTypeEntity.getName(), usersEntity.getName());
        }
        session.getTransaction().commit();
    }
}