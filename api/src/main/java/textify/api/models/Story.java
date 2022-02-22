package textify.api.models;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Must strictly comply with the story.json scheme.
 */
@Table(name = "stories")
public class Story {

  @Column(name = "uuid")
  private UUID storyId;
  @Column(name = "title")
  private String storyTitle;
  @Column(name = "description")
  private String storyDescription;
  @Column(name = "starting_node_id")
  private UUID startingNodeId;
  @Column(name = "metadata")
  private Metadata metadata;
}
