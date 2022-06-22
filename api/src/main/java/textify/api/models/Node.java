package textify.api.models;

import java.util.UUID;

public class Node {

  private final UUID nodeUuid;
  private final String nodeTitle;
  private final String content;
  private final Choice[] choices;


  public Node(UUID nodeUuid, String nodeTitle, String content, Choice[] choices) {
    this.nodeUuid = nodeUuid;
    this.nodeTitle = nodeTitle;
    this.content = content;
    this.choices = choices;
  }

  public UUID getNodeUuid() {
    return nodeUuid;
  }

  public String getNodeTitle() {
    return nodeTitle;
  }

  public String getContent() {
    return content;
  }

  public Choice[] getChoices() {
    return choices;
  }
}
