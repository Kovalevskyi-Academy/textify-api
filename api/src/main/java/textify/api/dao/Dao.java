package textify.api.dao;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import textify.api.models.Node;
import textify.api.models.Story;


/**
 * Using EntityManager in Hibernate? NO! A.
 *
 * @param <T> Node or Story.
 */
public interface Dao<T> {

  static final SessionFactory SESSION_FACTORY = initSessionFactoryFromCode();

  private static SessionFactory initSessionFactoryFromCode() {
    var serviceBuilder = new StandardServiceRegistryBuilder();

    var standardRegistry = serviceBuilder
        .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
        .applySetting("hibernate.connection.url", "jdbc:postgresql://postgres:5432/"
            + System.getenv("POSTGRES_DB"))
        .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
        .applySetting("hibernate.show_sql", "true")
        .applySetting("hibernate.hbm2ddl.auto", /*"update"*/ "create")
        .applySetting("hibernate.connection.username", System.getenv("POSTGRES_USER"))
        .applySetting("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"))
        .build();

    // Here we tell Hibernate that we annotated our Message class & open session
    var tempSessionFactory = new MetadataSources(standardRegistry)
        .addAnnotatedClass(Node.class)
        .addAnnotatedClass(Story.class)
        .buildMetadata()
        .getSessionFactoryBuilder().build();
    //StandardServiceRegistryBuilder.destroy(standardRegistry);
    return tempSessionFactory;
  }


  UUID save(T entity);

  T get(UUID uuid);

  boolean merge(T entity);

  boolean delete(UUID uuid) throws EntityNotFoundException;

}
