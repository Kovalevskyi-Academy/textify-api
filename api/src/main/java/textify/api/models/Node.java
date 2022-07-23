package textify.api.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.Table;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.naming.OperationNotSupportedException;
import javax.persistence.Column;

@Entity
@Table(name = "nodes")
public class Node {

  private static final int TITLE_LEN = 150;
  private static final int CONTENT_LEN = 1500;

  @Id
  @GeneratedValue
  @Column(name = "node_uuid", updatable = false, nullable = false, unique = true, length = 36)
  private UUID nodeUuid;
  @Column(name = "story_uuid", updatable = false, nullable = false, unique = false, length = 36)
  private UUID storyUuid;
  @Column(name = "node_title", updatable = true, nullable = false, unique = false,
      length = TITLE_LEN)
  private String nodeTitle;
  @Column(name = "content", updatable = true, nullable = false, unique = false,
      length = CONTENT_LEN)
  private String content;

  /* embedded collection example:
  https://javabydeveloper.com/mapping-collection-of-embeddablecomposite-types-jpa-with-hibernate/
   */

  // TODO maybe use @MapKeyJoinColumn(name = "current_node_id")
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "choices")
  @MapKey(name = "choice_description")
  private Map<String, UUID> choices;


  public Node() {
  }

  private Node(Builder builder) {
    nodeUuid = builder.nodeUuid;
    storyUuid = builder.storyUuid;
    nodeTitle = builder.nodeTitle;
    content = builder.content;
    this.choices = builder.choices;
  }


  public UUID getNodeUuid() {
    return nodeUuid;
  }

  public UUID getStoryUuid() {
    return storyUuid;
  }

  public String getNodeTitle() {
    return nodeTitle;
  }

  public String getContent() {
    return content;
  }

  public Map<String, UUID> getChoices() {
    return choices;
  }

  public void setChoices(Map<String, UUID> choices) {
    this.choices.putAll(choices);
  }

  public void setChoice(String essenceOfChoice, UUID destinationNode) {
    this.choices.put(essenceOfChoice, destinationNode);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Node node)) {
      return false;
    }

    if (!getStoryUuid().equals(node.getStoryUuid())) {
      return false;
    }
    if (!getNodeTitle().equals(node.getNodeTitle())) {
      return false;
    }
    if (!getContent().equals(node.getContent())) {
      return false;
    }
    return getChoices().equals(node.getChoices());
  }

  @Override
  public int hashCode() {
    int result = 0;
    result = 31 * result + getStoryUuid().hashCode();
    result = 31 * result + getNodeTitle().hashCode();
    result = 31 * result + getContent().hashCode();
    result = 31 * result + getChoices().hashCode();
    return result;
  }

  public static final class Builder {

    private UUID nodeUuid;
    private UUID storyUuid;
    private String nodeTitle;
    private String content;
    private Map<String, UUID> choices;

    public Builder() {
    }

    public Builder(Node copy) {
      this.nodeUuid = copy.getNodeUuid();
      this.storyUuid = copy.getStoryUuid();
      this.nodeTitle = copy.getNodeTitle();
      this.content = copy.getContent();
      this.choices = copy.getChoices();
    }

    public Builder storyUuid(UUID val) throws OperationNotSupportedException {
      if (this.storyUuid == null) {
        storyUuid = Objects.requireNonNull(val);
      } else {
        throw new OperationNotSupportedException("Story's UUID is unchangeable!");
      }
      return this;
    }

    public Builder nodeTitle(String val) {
      if (val != null && (0 < val.length() && val.length() <= TITLE_LEN)) {
        this.nodeTitle = val;
      } else {
        throw new IllegalArgumentException("Node's title length should be < 0 & > " + TITLE_LEN);
      }
      return this;
    }

    public Builder content(String val) {
      if (val != null && (0 < val.length() && val.length() <= CONTENT_LEN)) {
        this.content = val;
      } else {
        throw new IllegalArgumentException("Node's content length should be < 0 & > "
            + CONTENT_LEN);
      }
      return this;
    }

    public Builder choices(Map<String, UUID> val) {
      if (val.isEmpty()) {
        throw new IllegalArgumentException("Map of choices can't be empty!");
      }
      if (this.choices == null) {
        choices = val;
      } else {
        this.choices.putAll(val);
      }
      return this;
    }

    public Node build() {
      Objects.requireNonNull(storyUuid);
      nodeTitle = Objects.requireNonNullElse(nodeTitle, "Default Node title.");
      content = Objects.requireNonNullElse(content, "Default Node content.");
      choices = Objects.requireNonNullElse(choices, Collections.<String, UUID>emptyMap());
      return new Node(this);
    }
  }


}
