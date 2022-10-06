package textify.api.dao;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import textify.api.models.Node;
import textify.api.models.Story;

public class StoryDao implements Dao<Story> {


  @Override
  public UUID save(Story story) {
    Transaction transaction = null;
    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      session.persist(story);
      transaction.commit();
    } catch (RollbackException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return story.getStoryUuid();
  }

  @Override
  public Story get(UUID storyUuid) {
    // TODO Should I close the session after usage?
    // https://www.youtube.com/watch?v=emg94BI2Jao
    return SESSION_FACTORY.openSession().get(Story.class, storyUuid);
  }

  @Override
  public boolean merge(Story story) {
    Transaction transaction = null;
    try (var session = SESSION_FACTORY.openSession()) {
      transaction = session.beginTransaction();
      session.merge(story);
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
  public boolean delete(UUID storyUuid) throws EntityNotFoundException {
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
      var story = session.get(Story.class, storyUuid);
      if (story != null) {
        session.delete(story);
      } else {
        throw new EntityNotFoundException("Story with specified UUID not found!");
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
