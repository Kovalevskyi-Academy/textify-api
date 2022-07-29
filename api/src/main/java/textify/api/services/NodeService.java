package textify.api.services;

import java.util.Optional;
import java.util.UUID;
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


}
