package textify.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import textify.api.controllers.MessageRepo;
import textify.api.models.Message;
import java.util.UUID;


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
      repo.deleteAll();
      var firstEntity = new Message(-1L, "ONE TEST MESSAGE:" + UUID.randomUUID());
      var actualFirstMessage = repo.save(firstEntity);
      //System.out.println("FIRST ENTITY: " + actualFirstMessage);
      repo.save(new Message(-1L, "SECOND TEST MESSAGE:" + UUID.randomUUID()
          + "trololo"));

      var count = repo.count();
      if (count != 2) {
        throw new RuntimeException(
            "The number of records in the database was 2, but in fact there are " + count);
      }
      actualFirstMessage = repo.findByMessage(firstEntity.getMessage());
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
