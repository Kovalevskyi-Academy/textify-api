package textify.api.models;

import java.util.Objects;
import java.util.UUID;

public class Choice {

  private String choiceText;
  private UUID nextNodeUuid;

  public Choice() {}

  public Choice(String choiceText, UUID nextNodeUuid) {
    this.choiceText = choiceText;
    this.nextNodeUuid = nextNodeUuid;
  }

  public String getChoiceText() {
    return choiceText;
  }

  public UUID getNextNodeUuid() {
    return nextNodeUuid;
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
