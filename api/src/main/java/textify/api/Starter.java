package textify.api;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import textify.api.dao.NodeDao;
import textify.api.dao.StoryDao;
import textify.api.models.Node;
import textify.api.models.Story;
import textify.api.services.ForTests;

@SpringBootApplication
public class Starter {

  public static void main(String[] args) {
    System.out.println("helloWorld");
    // testing Node in DB
    StoryDao storyDao = new StoryDao();
    var uuidOfStorySeries = UUID.randomUUID();
    var expectedFirstStory = ForTests.getFirstStory(uuidOfStorySeries);
    var expectedSecondStory = ForTests.getSecondStory(uuidOfStorySeries);

    System.out.println("Try to save stories!");
    var storyUuid1 = storyDao.save(expectedFirstStory);
    var storyUuid2 = storyDao.save(expectedSecondStory);
    System.out.println("Try to get stories!");
    Optional<Story> actualStory1 = storyDao.get(storyUuid1);
    Optional<Story> actualStory2 = storyDao.get(storyUuid2);
    System.out.println("First actual story: " + actualStory1.orElse(null));
    System.out.println("Second actual story: " + actualStory2.orElse(null));

    System.out.println("Creating nodes!");

    Node expectedFirstNode = ForTests.getFirstNode(storyUuid1);
    Node expectedSecondNode = ForTests.getSecondNode(storyUuid2);
    var nodeDao = new NodeDao();
    System.out.println("Try to save nodes!");
    var nodeUuid1 = nodeDao.save(expectedFirstNode);
    var nodeUuid2 = nodeDao.save(expectedSecondNode);
    System.out.println("Try to get nodes!");
    var actualNode1 = nodeDao.get(nodeUuid1);
    var actualNode2 = nodeDao.get(nodeUuid2);
    Node emptyNode = new Node();
    System.out.println("First NODE: " + actualNode1.orElse(emptyNode).toString());
    System.out.println("First NODE is equals to expected: "
        + expectedFirstNode.equals(actualNode1.orElse(emptyNode)));
    System.out.println("Second NODE: " + actualNode2.orElse(emptyNode).toString());
    System.out.println("Second NODE is equals to expected: "
        + expectedSecondNode.equals(actualNode2.orElse(emptyNode)));

    System.out.println("Add to stories starting nodes!");
    expectedFirstStory = ForTests.addStartingNodeToStory(expectedFirstStory, nodeUuid1);
    expectedSecondStory = ForTests.addStartingNodeToStory(expectedSecondStory, nodeUuid2);

    System.out.println("First Story with start node: " + expectedFirstStory.toString());
    System.out.println("Second Story with start node: " + expectedSecondStory.toString());

    // TODO made update stories in DB

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("test DELETE");

    nodeDao.delete(new ArrayList<>(2) {
      {
        add(nodeUuid1);
        add(nodeUuid2);
      }
    });

    storyDao.delete(new ArrayList<>(2) {
      {
        add(storyUuid1);
        add(storyUuid2);
      }
    });
    System.out.println("DELETE SUCCESS");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
