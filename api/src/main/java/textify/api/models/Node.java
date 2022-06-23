package textify.api.models;

import java.util.Set;
import java.util.UUID;

public class Node {

  private UUID nodeUuid;
  private String nodeTitle;
  private String content;
  private Set<Choice> choices;

  public Node() {}

  public Node(UUID nodeUuid, String nodeTitle, String content, Set<Choice> choices) {
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

  public Set<Choice> getChoices() {
    return choices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Node node)) {
      return false;
    }

    if (!getNodeTitle().equals(node.getNodeTitle())) {
      return false;
    }
    if (!getContent().equals(node.getContent())) {
      return false;
    }
    return getChoices() != null ? getChoices().equals(node.getChoices())
        : node.getChoices() == null;
  }

  @Override
  public int hashCode() {
    int result = getNodeTitle().hashCode();
    result = 31 * result + getContent().hashCode();
    result = 31 * result + (getChoices() != null ? getChoices().hashCode() : 0);
    return result;
  }
}
