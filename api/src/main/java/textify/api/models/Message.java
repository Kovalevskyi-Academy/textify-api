package textify.api.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public final class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @Column(name = "message")
  private String message;

  // Default empty constructor need for Spring JPA
  public Message() {}

  public Message(Long id, String message) {
    this.id = id;
    this.message = message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (Message) obj;
    return this.id == that.id &&
        Objects.equals(this.message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, message);
  }

  @Override
  public String toString() {
    return "Message[id=%d, message=%s]".formatted(id, message);
  }
}