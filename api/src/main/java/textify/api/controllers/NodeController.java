package textify.api.controllers;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import textify.api.dao.Dao;
import textify.api.dao.NodeDao;
import textify.api.models.Node;

@RestController
public class NodeController {


  private final Dao<Node> nodeDao = new NodeDao();


  @PostMapping(path = "/nodes")
  public ResponseEntity<UUID> createNode(@RequestBody Node node) {
    UUID result = null;
    try {
      result = nodeDao.save(node);
    } catch (Exception ignore) {
      // maybe remove this try-catch?
    }
    return result != null ? new ResponseEntity<>(result, HttpStatus.CREATED)
        : new ResponseEntity<>(HttpStatus.INSUFFICIENT_STORAGE);
  }

  @GetMapping(path = "/nodes/{nodeUuid}")
  public ResponseEntity<Node> readNode(@PathVariable(name = "nodeUuid") UUID nodeUuid) {
    final var node = nodeDao.get(nodeUuid);
    return node != null ? new ResponseEntity<>(node, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping(path = "/nodes")
  public ResponseEntity<Node> updateNode(@RequestBody Node node) {
    return nodeDao.merge(node) ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping(path = "/nodes/{nodeUuid}")
  public ResponseEntity<Node> deleteNode(@PathVariable(name = "nodeUuid") UUID nodeUuid) {
    boolean isDeleted = false;
    try {
      isDeleted = nodeDao.delete(nodeUuid);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return isDeleted ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
