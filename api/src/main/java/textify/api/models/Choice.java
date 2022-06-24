package textify.api.models;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Choice {

  @Column(name = "choice_text", updatable = true, nullable = false, unique = true, length = 500)
  private String choiceText;
  @Column(name = "next_node_uuid", updatable = true, unique = false, length = 36)
  private UUID nextNodeUuid;

  public Choice() {}

  public Choice(String choiceText, UUID nextNodeUuid) {
    this.choiceText = choiceText;
    this.nextNodeUuid = nextNodeUuid;
  }

  public String getChoiceText() {
    return choiceText;
  }

  public void setChoiceText(String choiceText) {
    this.choiceText = choiceText;
  }

  public UUID getNextNodeUuid() {
    return nextNodeUuid;
  }

  public void setNextNodeUuid(UUID nextNodeUuid) {
    this.nextNodeUuid = nextNodeUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Choice choice)) {
      return false;
    }

    if (!getChoiceText().equals(choice.getChoiceText())) {
      return false;
    }
    return getNextNodeUuid() != null ? getNextNodeUuid().equals(choice.getNextNodeUuid())
        : choice.getNextNodeUuid() == null;
  }

  @Override
  public int hashCode() {
    int result = getChoiceText().hashCode();
    result = 31 * result + (getNextNodeUuid() != null ? getNextNodeUuid().hashCode() : 0);
    return result;
  }
}
