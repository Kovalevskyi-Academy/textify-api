package textify.api;

import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import textify.api.controllers.MessageRepo;
import textify.api.models.Message;


/**
 * This API must match the schema: "link to schema".
 */
@SpringBootApplication
public class TextifyApiStarter {

  public static void main(String[] args) {
    SpringApplication.run(TextifyApiStarter.class, args);
  }

  @Bean
  private static CommandLineRunner fillDataBase(MessageRepo repo) {
    return (args) -> {
      // repo.deleteAll();
      var firstEntity = new Message(-1L, "FIRST TEST MESSAGE:" + UUID.randomUUID());
      repo.save(firstEntity);
      repo.save(new Message(-1L, "SECOND TEST MESSAGE:" + UUID.randomUUID()
          + "trololo"));

      var actualFirstMessage = repo.findByMessage(firstEntity.getMessage());
      if (!firstEntity.getMessage().equals(actualFirstMessage.getMessage())) {
        var errorMessage = """
            Invalid response from DB!
            Expected: %s
            Actual: %s
            """.formatted(firstEntity.getMessage(), actualFirstMessage);
        throw new RuntimeException(errorMessage);
      }
    };
  }
}
