package textify.api.dao;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import textify.api.models.Node;

public class NodeDao implements Dao<Node> {


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
  public Node get(UUID nodeUuid) {
    // TODO Should I close the session after usage?
    // https://www.youtube.com/watch?v=emg94BI2Jao
    return SESSION_FACTORY.openSession().get(Node.class, nodeUuid);
  }

  @Override
  public boolean merge(Node node) {
    Transaction transaction = null;
    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      var result = (Node) session.merge(node);
      System.out.println(result.toString());
      session.getTransaction().commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      return false;
    }
    return true;
  }

  @Override
  public boolean delete(UUID nodeUuid) throws EntityNotFoundException {

    Transaction transaction = null;

    try (Session session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      // TODO I need to delete all records in DB what satisfy all the list uuids
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

      var node = session.find(Node.class, nodeUuid);
      if (node != null) {
        session.delete(node);
      } else {
        throw new EntityNotFoundException("Node with specified UUID not found!");
      }

      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
