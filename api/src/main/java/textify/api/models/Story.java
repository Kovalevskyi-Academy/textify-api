package textify.api.models;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Must strictly comply with the story.json scheme.
 */
@Entity
@Table(name = "stories")
public class Story {

  private static final int TITLE_LEN = 150;
  private static final int DESCRIPTION_LEN = 1500;

  @Column(name = "story_series_uuid", length = 36)
  private UUID storySeriesUuid;
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "story_uuid", updatable = false, nullable = true, unique = true, length = 36)
  private UUID storyUuid;
  @Column(name = "starting_node_uuid", updatable = true, nullable = true,
      unique = true, length = 36)
  private UUID startingNodeUuid;
  @Column(name = "story_title", updatable = true, nullable = false, unique = false,
      length = TITLE_LEN)
  private String storyTitle;
  @Column(name = "story_description", updatable = true, nullable = false,
      unique = false, length = DESCRIPTION_LEN)
  private String storyDescription;
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "authors", joinColumns = @JoinColumn(name = "story_uuid"))
  private Set<String> authors;
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "genres")
  private Set<String> genres;
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "tags")
  private Set<String> tags;

  // TODO Set<Node> or Set<UUID> thisStoryNodes? It will be huge object...
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "prod_time", updatable = false, nullable = true)
  private Date productionDateTime;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "upd_time", updatable = true, nullable = true)
  private Date updatingDateTime;

  public Story() {
  }

  private Story(Builder builder) {
    storySeriesUuid = builder.storySeriesUuid;
    storyUuid = builder.storyUuid;
    startingNodeUuid = builder.startingNodeUuid;
    storyTitle = builder.storyTitle;
    storyDescription = builder.storyDescription;
    authors = builder.authors;
    genres = builder.genres;
    tags = builder.tags;
    productionDateTime = builder.productionDateTime;
    updatingDateTime = builder.updatingDateTime;
  }

  public UUID getStorySeriesUuid() {
    return storySeriesUuid;
  }

  public UUID getStoryUuid() {
    return storyUuid;
  }

  public UUID getStartingNodeUuid() {
    return startingNodeUuid;
  }

  public String getStoryTitle() {
    return storyTitle;
  }

  public String getStoryDescription() {
    return storyDescription;
  }

  public Set<String> getAuthors() {
    return authors;
  }

  public Set<String> getGenres() {
    return genres;
  }

  public Set<String> getTags() {
    return tags;
  }

  public Date getProductionDateTime() {
    return productionDateTime;
  }

  public Date getUpdatingDateTime() {
    return updatingDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Story story)) {
      return false;
    }

    if (getStorySeriesUuid() != null ? !getStorySeriesUuid().equals(story.getStorySeriesUuid())
        : story.getStorySeriesUuid() != null) {
      return false;
    }
    if (getStoryUuid() != null ? !getStoryUuid().equals(story.getStoryUuid())
        : story.getStoryUuid() != null) {
      return false;
    }
    if (getStartingNodeUuid() != null ? !getStartingNodeUuid().equals(story.getStartingNodeUuid())
        : story.getStartingNodeUuid() != null) {
      return false;
    }
    if (!getStoryTitle().equals(story.getStoryTitle())) {
      return false;
    }
    if (!getStoryDescription().equals(story.getStoryDescription())) {
      return false;
    }
    if (!getAuthors().equals(story.getAuthors())) {
      return false;
    }
    if (!getGenres().equals(story.getGenres())) {
      return false;
    }
    if (!getTags().equals(story.getTags())) {
      return false;
    }
    if (!getProductionDateTime().equals(story.getProductionDateTime())) {
      return false;
    }
    return getUpdatingDateTime().equals(story.getUpdatingDateTime());
  }

  @Override
  public int hashCode() {
    int result = getStorySeriesUuid() != null ? getStorySeriesUuid().hashCode() : 0;
    result = 31 * result + (getStartingNodeUuid() != null ? getStartingNodeUuid().hashCode() : 0);
    result = 31 * result + getStoryTitle().hashCode();
    result = 31 * result + getStoryDescription().hashCode();
    result = 31 * result + getAuthors().hashCode();
    result = 31 * result + getGenres().hashCode();
    result = 31 * result + getTags().hashCode();
    result = 31 * result + getProductionDateTime().hashCode();
    result = 31 * result + getUpdatingDateTime().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return """
        Story series uuid: %s
        Story uuid: %s
        Starting node uuid: %s
        Story title: %s
        Story description: %s
        Authors: %s
        Genres: %s
        Tags: %s
        """.formatted(storySeriesUuid, storyUuid, startingNodeUuid, storyTitle, storyDescription,
        authors, genres, tags);
  }


  public static final class Builder {

    private final Date productionDateTime;
    private final Date updatingDateTime = new Timestamp(System.currentTimeMillis());
    private UUID storySeriesUuid;
    private UUID storyUuid;
    private UUID startingNodeUuid;
    private String storyTitle;
    private String storyDescription;
    private Set<String> authors;
    private Set<String> genres;
    private Set<String> tags;

    /**
     * For creating absolute clean story.
     */
    public Builder() {
      this.productionDateTime = new Timestamp(System.currentTimeMillis());
    }

    /**
     * For modification existing story.
     *
     * @param existing story
     */
    public Builder(Story existing) {
      this.storySeriesUuid = existing.getStorySeriesUuid();
      this.storyUuid = existing.getStoryUuid();
      this.startingNodeUuid = existing.getStartingNodeUuid();
      this.storyTitle = existing.getStoryTitle();
      this.storyDescription = existing.getStoryDescription();
      this.authors = existing.getAuthors();
      this.genres = existing.getGenres();
      this.tags = existing.getTags();
      this.productionDateTime = existing.getProductionDateTime();
    }

    public Builder storySeriesUuid(UUID val) {
      storySeriesUuid = Objects.requireNonNull(val);
      return this;
    }

    public Builder startingNodeUuid(UUID val) {
      startingNodeUuid = Objects.requireNonNull(val);
      return this;
    }

    public Builder storyTitle(String val) {
      if (val != null && (0 < val.length() && val.length() <= TITLE_LEN)) {
        storyTitle = val;
      } else {
        throw new IllegalArgumentException("Story's title length should be < 0 & > " + TITLE_LEN);
      }
      return this;
    }

    public Builder storyDescription(String val) {
      if (val != null && (0 < val.length() && val.length() <= DESCRIPTION_LEN)) {
        storyDescription = val;
      } else {
        throw new IllegalArgumentException("Story's description length should be < 0 & > "
            + DESCRIPTION_LEN);
      }
      return this;
    }

    public Builder authors(Set<String> val) {
      if (val.isEmpty()) {
        throw new IllegalArgumentException("Set of authors can't be empty!");
      }
      if (authors == null) {
        authors = val;
      } else {
        authors.addAll(val);
      }
      return this;
    }

    public Builder genres(Set<String> val) {
      if (val.isEmpty()) {
        throw new IllegalArgumentException("Set of genres can't be empty!");
      }
      if (genres == null) {
        genres = val;
      } else {
        genres.addAll(val);
      }
      return this;
    }

    public Builder tags(Set<String> val) {
      if (val.isEmpty()) {
        throw new IllegalArgumentException("Set of tags can't be empty!");
      }
      if (tags == null) {
        tags = val;
      } else {
        tags.addAll(val);
      }
      return this;
    }

    public Story build() {
      System.out.println("BUILDER POST: " + productionDateTime);
      Objects.requireNonNull(storyTitle);
      Objects.requireNonNull(storyDescription);
      Objects.requireNonNull(authors);
      return new Story(this);
    }
  }
}
