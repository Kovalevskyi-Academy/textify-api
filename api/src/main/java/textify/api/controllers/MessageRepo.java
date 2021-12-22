package textify.api.controllers;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import textify.api.models.Message;

//@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessageRepo extends PagingAndSortingRepository<Message, Long> {

  Message findByMessage(@Param("message") String message);

}
