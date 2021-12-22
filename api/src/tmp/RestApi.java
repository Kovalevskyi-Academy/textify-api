package textify.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import textify.api.models.Message;

/**
 * Here I'm trying to handle REST requests.
 */
@RestController
@RequestMapping(path = "/messages")
public class RestApi {

  @Autowired
  private MessageRepo messageRepo;

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<Message> getAllMessages() {
    // This returns a JSON or XML with the messages
    return messageRepo.findAll();
  }

  @GetMapping(path = "/{id}")
  public @ResponseBody
  Message getOneMessage(@PathVariable Long id) {
    // This returns a JSON or XML with the one message
    return messageRepo.findById(id).orElse(new Message("NO MESSAGE"));
  }

  @PostMapping(path = "/add")
  public @ResponseBody Message greeting(@RequestParam(value = "message", defaultValue = "SILENCE") String message) {

    return messageRepo.save(new Message(message));
  }

}
