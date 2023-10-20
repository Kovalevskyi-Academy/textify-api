package textify.api;

import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import textify.api.controllers.ForTests;
import textify.api.dao.Dao;
import textify.api.dao.NodeDao;
import textify.api.dao.StoryDao;
import textify.api.models.Node;
import textify.api.models.Story;

@SpringBootApplication
public class Starter {

  public static void main(String[] args) {
    // testDaoLayer();
    SpringApplication.run(Starter.class, args);
    // shortDaoTest();
  }

  private static void shortDaoTest() {
    System.out.println("PREPARING DATABASE");

    Dao<Node> nodeDao = new NodeDao();
    System.out.println("Create & save one node: ");
    var firstNodeUuid = nodeDao.save(ForTests.getFirstNode(UUID.randomUUID()));
    System.out.println(firstNodeUuid);
    System.out.println("Try to find FIRST NODE in DB: ");
    System.out.println(nodeDao.get(firstNodeUuid));

  }

  private static void testDaoLayer() {
    System.out.println("DAO TESTS");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Dao<Story> storyDao = new StoryDao();
    var uuidOfStorySeries = UUID.randomUUID();
    var expectedFirstStory = ForTests.getFirstStory(uuidOfStorySeries);
    var expectedSecondStory = ForTests.getSecondStory(uuidOfStorySeries);

    System.out.println("Try to save stories!");
    var storyUuid1 = storyDao.save(expectedFirstStory);
    var storyUuid2 = storyDao.save(expectedSecondStory);
    System.out.println("Try to get stories!");
    Story actualStory1 = storyDao.get(storyUuid1);
    Story actualStory2 = storyDao.get(storyUuid2);
    System.out.println("First actual story: \n" + actualStory1);
    System.out.println(
        "Equals actual FIRST STORY vs EXPECTED: " + expectedFirstStory.equals(actualStory1));
    System.out.println("Second actual story: \n" + actualStory2);
    System.out.println(
        "Equals actual SECOND STORY vs EXPECTED: " + expectedSecondStory.equals(actualStory2));
    System.out.println("test UPDATE");
    System.out.println("Try to update stories!");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    var updatedStory1 = new Story.Builder(actualStory1).storyTitle("UPDATED STORY #1").build();
    var updatedStory2 = new Story.Builder(actualStory2).storyTitle("UPDATED STORY #2").build();
    System.out.println(
        "Equals first story before update: " + expectedFirstStory.equals(updatedStory1));
    System.out.println(
        "Equals second story before update: " + expectedSecondStory.equals(updatedStory2));
    System.out.println("Update first story success: " + storyDao.merge(updatedStory1));
    System.out.println("Update second story success: " + storyDao.merge(updatedStory2));
    System.out.println("Equals first story after update: " + actualStory1.equals(updatedStory1));
    System.out.println("OLD1: " + actualStory1);
    System.out.println("UPDATED1: " + updatedStory1);
    System.out.println("Equals second story after update: " + actualStory2.equals(updatedStory2));
    System.out.println("OLD2: " + actualStory2);
    System.out.println("UPDATED2: " + updatedStory2);

    System.out.println("Creating nodes!");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    Node expectedFirstNode = ForTests.getFirstNode(storyUuid1);
    Node expectedSecondNode = ForTests.getSecondNode(storyUuid2);
    Dao<Node> nodeDao = new NodeDao();
    System.out.println("Try to save nodes!");
    var nodeUuid1 = nodeDao.save(expectedFirstNode);
    var nodeUuid2 = nodeDao.save(expectedSecondNode);
    System.out.println("Try to get nodes!");
    var actualNode1 = nodeDao.get(nodeUuid1);
    var actualNode2 = nodeDao.get(nodeUuid2);
    System.out.println("First NODE: " + actualNode1);
    System.out.println(
        "First NODE is equals to expected: " + expectedFirstNode.equals(actualNode1));
    System.out.println("Second NODE: " + actualNode2);
    System.out.println(
        "Second NODE is equals to expected: " + expectedSecondNode.equals(actualNode2));

    System.out.println("\n#####Add to stories starting nodes!");
    expectedFirstStory = ForTests.addStartingNodeToStory(expectedFirstStory, nodeUuid1);
    expectedSecondStory = ForTests.addStartingNodeToStory(expectedSecondStory, nodeUuid2);

    System.out.println("First Story with start node: " + expectedFirstStory);
    System.out.println("Second Story with start node: " + expectedSecondStory);

    System.out.println("test DELETE");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    nodeDao.delete(nodeUuid1);
    nodeDao.delete(nodeUuid2);
    System.out.println("Existing nodes deleting successful!");

    storyDao.delete(storyUuid1);
    storyDao.delete(storyUuid2);
    System.out.println("Existing nodes deleting successful!");

    System.out.println("Try to delete non-existent object:");
    try {
      nodeDao.delete(UUID.randomUUID());
      storyDao.delete(UUID.randomUUID());
    } catch (EntityNotFoundException ignore) { }

    System.out.println("DELETE SUCCESS");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
