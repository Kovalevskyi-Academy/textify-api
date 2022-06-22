package textify.api.models;

import java.util.UUID;

public class Choice {

  private final String choiceText;
  private final UUID nextNodeUuid;


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
}
