package textify.api.services;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import javax.naming.OperationNotSupportedException;
import textify.api.models.Node;
import textify.api.models.Story;

public class ForTests {

  public static Node getFirstNode(UUID storyUuid) {
    Node result;

    var choices1 = new HashMap<String, UUID>() {
      {
        put("First choice for first node", UUID.randomUUID());
        put("Second choice for first node", UUID.randomUUID());
      }
    };
    try {
      result = new Node.Builder()
          .nodeTitle("First node")
          .storyUuid(storyUuid)
          .content("It is node's content of first node.")
          .choices(choices1)
          .build();
    } catch (OperationNotSupportedException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static Node getSecondNode(UUID storyUuid) {
    Node result;

    var choices2 = new HashMap<String, UUID>() {
      {
        put("First choice for second node", UUID.randomUUID());
        put("Second choice for second node", UUID.randomUUID());
      }
    };

    try {
      result = new Node.Builder()
          .nodeTitle("Second node")
          .storyUuid(storyUuid)
          .content("It is node's content of second node.")
          .choices(choices2)
          .build();
    } catch (OperationNotSupportedException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static Story getFirstStory(UUID storySeries) {
    Story result;
    result = new Story.Builder()
        .storySeriesUuid(storySeries)
        .storyTitle("It is a first Story.")
        .storyDescription("Description of first story")
        .authors(Set.of("Author#1", "Author#2"))
        .genres(Set.of("Genre#1", "Genre#2"))
        .tags(Set.of("Trololo1", "Trololo2"))
        .build();

    return result;
  }

  public static Story getSecondStory(UUID storySeries) {
    Story result;
    result = new Story.Builder()
        .storySeriesUuid(storySeries)
        .storyTitle("It is a second Story.")
        .storyDescription("Description of second story")
        .authors(Set.of("Author#11", "Author#22"))
        .genres(Set.of("Genre#11", "Genre#22"))
        .tags(Set.of("Trololo11", "Trololo22"))
        .build();

    return result;
  }

  public static Story addStartingNodeToStory(Story story, UUID startingNodeUuid) {
    return new Story.Builder(story).startingNodeUuid(startingNodeUuid).build();
  }


}
