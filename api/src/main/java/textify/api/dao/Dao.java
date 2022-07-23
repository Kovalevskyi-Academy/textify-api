package textify.api.dao;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import textify.api.models.Node;
import textify.api.models.Story;

public interface Dao<T> {

  SessionFactory SESSION_FACTORY = initSessionFactory();


  default Set<T> getAllStories() {
    return null;
  }

  Optional<T> get(UUID uuid);

  UUID save(T entity);

  void delete(UUID... uuids);


  private static SessionFactory initSessionFactory() {
    var serviceBuilder = new StandardServiceRegistryBuilder();

    var standardRegistry = serviceBuilder
        .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
        .applySetting("hibernate.connection.url", System.getenv("POSTGRES_DB"))
        .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
        .applySetting("show_sql", "true")
        .applySetting("hbm2ddl.auto", "update")
        .applySetting("hibernate.connection.username", System.getenv("POSTGRES_USER"))
        .applySetting("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"))
        .build();

    // Here we tell Hibernate that we annotated our Message class & open session
    var tempSessionFactory = new MetadataSources(standardRegistry)
        .addAnnotatedClass(Node.class)
        .addAnnotatedClass(Story.class)
        .buildMetadata()
        .buildSessionFactory();
    StandardServiceRegistryBuilder.destroy(standardRegistry);
    return tempSessionFactory;
  }

}
