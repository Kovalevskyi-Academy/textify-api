package textify.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Check REST availability.
 */
@RestController
public class Availability {

  @GetMapping(path = "/test")
  public ResponseEntity<String> isServerOnline() {
    return new ResponseEntity<>("REST API IS STARTED", HttpStatus.OK);
  }

}
