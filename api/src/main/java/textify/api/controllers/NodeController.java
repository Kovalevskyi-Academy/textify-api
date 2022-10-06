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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import textify.api.dao.Dao;
import textify.api.dao.NodeDao;
import textify.api.models.Node;

@RestController
@RequestMapping(path = "/nodes")
public class NodeController {


  private static final Dao<Node> nodeDao = new NodeDao();


  @PostMapping(path = "/nodes")
  private static @ResponseBody UUID createNode(@RequestBody Node node) {
    return nodeDao.save(node);
  }

  @GetMapping(path = "/nodes/{nodeUuid}")
  private static ResponseEntity<Node> readNode(@PathVariable(name = "nodeUuid") UUID nodeUuid) {
    System.out.println("GET NODE");
    final var node = nodeDao.get(nodeUuid);
    return node != null ? new ResponseEntity<>(node, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping(path = "/nodes")
  private static ResponseEntity<?> updateNode(@RequestBody Node node) {
    var isUpdated = nodeDao.merge(node);
    return isUpdated ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping(path = "/nodes/{nodeUuid}")
  private static ResponseEntity<?> deleteNode(@PathVariable(name = "nodeUuid") UUID nodeUuid) {
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
