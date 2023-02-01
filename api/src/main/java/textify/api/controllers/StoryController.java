package textify.api.controllers;


import java.util.Date;
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
import textify.api.dao.StoryDao;
import textify.api.models.Story;

@RestController
public class StoryController {

  private final Dao<Story> storyDao = new StoryDao();

  @PostMapping(path = "/stories")
  public ResponseEntity<UUID> createStory(@RequestBody Story story) {
    UUID result = null;
    try {
      result = storyDao.save(story);
    } catch (Exception ignore) {
      // is it need to remove this try-catch?
    }
    return result != null ? new ResponseEntity<>(result, HttpStatus.CREATED)
        : new ResponseEntity<>(HttpStatus.INSUFFICIENT_STORAGE);
  }

  @GetMapping(path = "/stories/{storyUuid}")
  public ResponseEntity<Story> readStory(@PathVariable UUID storyUuid) {
    var story = storyDao.get(storyUuid);
    return story != null ? new ResponseEntity<>(story, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping(path = "/stories")
  public ResponseEntity<Story> updateStory(@RequestBody Story story) {
    return storyDao.merge(story) ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping(path = "/stories/{storyUuid}")
  public ResponseEntity<Story> deleteStory(@PathVariable(name = "storyUuid") UUID storyUuid) {
    boolean isDeleted = false;
    try {
      isDeleted = storyDao.delete(storyUuid);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return isDeleted ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

}
