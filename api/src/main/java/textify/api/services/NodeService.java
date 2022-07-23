package textify.api.services;

import java.util.Optional;
import java.util.UUID;
import javax.naming.OperationNotSupportedException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import textify.api.dao.NodeDao;
import textify.api.models.Node;

/**
 * Думаю тут и будет REST который будет отвечать на внешние запросы CRUD.
 */
public class NodeService {


  // TODO Spring REST
  private static NodeDao nodeDao;

  private static Node getNode(UUID nodeUuid) throws NotFoundException {
    Optional<Node> node = nodeDao.get(nodeUuid);
    return node.orElseThrow(NotFoundException::new);
  }


  public static void main(String[] args) {
    nodeDao = new NodeDao();
    Node expectedFirstNode;
    Node expectedSecondNode;

    var storyUuid = UUID.randomUUID();
    try {
      expectedFirstNode = new Node.Builder()
          .nodeTitle("First node")
          .storyUuid(storyUuid)
          .content("It is node's content of first node.")
          .build();
      expectedSecondNode = new Node.Builder()
          .nodeTitle("Second node")
          .storyUuid(storyUuid)
          .content("It is node's content of second node.")
          .build();
    } catch (OperationNotSupportedException e) {
      throw new RuntimeException(e);
    }

    UUID nodeUuid1 = nodeDao.save(expectedFirstNode);
    UUID nodeUuid2 = nodeDao.save(expectedSecondNode);

    Node actualNode1;
    Node actualNode2;
    try {
      actualNode1 = getNode(nodeUuid1);
      actualNode2 = getNode(nodeUuid2);
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
    System.out.println("First NODE: " + expectedFirstNode.equals(actualNode1));
    System.out.println("Second NODE: " + expectedSecondNode.equals(actualNode2));

  }


}
