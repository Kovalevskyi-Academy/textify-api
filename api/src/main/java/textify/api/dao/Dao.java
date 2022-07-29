package textify.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

  SessionFactory SESSION_FACTORY = initSessionFactory();

  private static SessionFactory initSessionFactory() {
    var serviceBuilder = new StandardServiceRegistryBuilder();

    var standardRegistry = serviceBuilder
        .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
        .applySetting("hibernate.connection.url", System.getenv("DB_URL"))
        .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
        .applySetting("hibernate.show_sql", "true")
        .applySetting("hibernate.hbm2ddl.auto", "update" /*"create"*/)
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

  Optional<T> get(UUID uuid);

  UUID save(T entity);

  void delete(List<UUID> uuids);

}
