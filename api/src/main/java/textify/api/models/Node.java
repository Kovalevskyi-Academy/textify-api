package textify.api.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name = "nodes")
public class Node {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "node_uuid", updatable = false, nullable = false, unique = true, length = 36)
  private UUID nodeUuid;
  @Column(name = "node_title", updatable = true, nullable = false, unique = false, length = 150)
  private String nodeTitle;
  @Column(name = "content", updatable = true, nullable = false, unique = false, length = 1500)
  private String content;

  /* embedded collection example:
  https://javabydeveloper.com/mapping-collection-of-embeddablecomposite-types-jpa-with-hibernate/
   */
  // TODO maybe use Map<String, UUID>
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "choices")
  private Set<Choice> choices = new HashSet<>(2);

  public Node() {}

  private Node(Builder builder) {
    nodeUuid = builder.nodeUuid;
    nodeTitle = builder.nodeTitle;
    content = builder.content;
    setChoices(builder.choices);
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

  public void setChoices(Set<Choice> choices) {
    this.choices.addAll(choices);
  }

  public void setOneChoice(Choice choice) {
    this.choices.add(choice);
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

  public Builder builder() {
    return new Builder(this);
  }


  public static final class Builder {

    private UUID nodeUuid;
    private String nodeTitle;
    private String content;
    private Set<Choice> choices;

    public Builder() {
    }

    public Builder(Node copy) {
      this.nodeUuid = copy.getNodeUuid();
      this.nodeTitle = copy.getNodeTitle();
      this.content = copy.getContent();
      this.choices = copy.getChoices();
    }

    public Builder nodeTitle(String val) {
      nodeTitle = val;
      return this;
    }

    public Builder content(String val) {
      content = val;
      return this;
    }

    public Builder choices(Set<Choice> val) {
      choices = val;
      return this;
    }

    public Node build() {
      return new Node(this);
    }
  }
}
