package textify.api.dao;

import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaDelete;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.Transaction;
import textify.api.models.Node;

public class NodeDao implements Dao<Node> {


  @Override
  public Optional<Node> get(UUID uuid) {
    var session = SESSION_FACTORY.openSession();
    return Optional.ofNullable(session.get(Node.class, uuid));
  }

  @Override
  public UUID save(Node node) {
    UUID id = null;
    Transaction transaction = null;
    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      id = (UUID) session.save(node);
      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public void delete(UUID[] nodeUuid) {


    Transaction transaction = null;

    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      // TODO найти способ создавать запрос с удалением по айдишнику
     /* CriteriaDelete<Node> criteriaDelete =
      session.createQuery();*/

      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
