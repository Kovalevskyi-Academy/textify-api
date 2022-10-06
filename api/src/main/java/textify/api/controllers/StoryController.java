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
import textify.api.dao.StoryDao;
import textify.api.models.Story;

@RestController
@RequestMapping(path = "/stories")
public class StoryController {

  private static final Dao<Story> storyDao = new StoryDao();

  @PostMapping(path = "/stories")
  private static @ResponseBody UUID createStory(@RequestBody Story story) {
    return storyDao.save(story);
  }

  @GetMapping(path = "/stories/{storyUuid}")
  private static @ResponseBody Story readStory(@PathVariable UUID storyUuid) {
    return storyDao.get(storyUuid);
  }

  @PutMapping(path = "/stories")
  private static ResponseEntity<?> updateStory(@RequestBody Story story) {
    var isUpdated = storyDao.merge(story);
    return isUpdated ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping(path = "/nodes/{storyUuid}")
  private static ResponseEntity<?> deleteStory(@PathVariable(name = "storyUuid") UUID storyUuid) {
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
