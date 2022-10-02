package textify.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import textify.api.models.Node;

public class NodeDao implements Dao<Node> {


  @Override
  public Optional<Node> get(UUID nodeUuid) {
    // TODO Should I close the session after usage?
    // https://www.youtube.com/watch?v=emg94BI2Jao
    return Optional.ofNullable(SESSION_FACTORY.openSession().get(Node.class, nodeUuid));
  }

  @Override
  public UUID save(Node node) {
    Transaction transaction = null;
    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      session.persist(node);
      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return node.getNodeUuid();
  }

  @Override
  public void delete(List<UUID> nodeUuids) {

    Transaction transaction = null;

    try (Session session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      // TODO I need to delete all records in DB what satisfy all of the list uuids
      //  example native query: `DELETE FROM table WHERE id IN (1, 4, 6, 7)`
      //  example of HQL:
      /*
      List<Long> ids = new ArrayList<Long>();
      ids.add(1L);
      ids.add(2L);

      Query q = session.createQuery("DELETE FROM YourEntityName ye WHERE ye.id IN (:list)");

      q.setParameterList("list", ids);
      q.executeUpdate();

       */
      for (var uuid : nodeUuids) {
        var node = session.get(Node.class, uuid);
        if (node != null) {
          session.delete(node);

        }
      }

      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
