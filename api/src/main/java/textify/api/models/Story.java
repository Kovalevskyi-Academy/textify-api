package textify.api.models;


import java.util.Date;
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

/**
 * Must strictly comply with the story.json scheme.
 */
@Entity
@Table(name = "stories")
public class Story {

  @Column(name = "story_series_uuid", updatable = true, nullable = true, unique = true, length = 36)
  private UUID storySeriesUuid;
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "story_uuid", updatable = false, nullable = false, unique = true, length = 36)
  private UUID storyUuid;
  @Column(name = "starting_node_uuid", updatable = true, nullable = true,
      unique = true, length = 36)
  private UUID startingNodeUuid;
  @Column(name = "story_title", updatable = true, nullable = false, unique = false, length = 150)
  private String storyTitle;
  @Column(name = "story_description", updatable = true, nullable = false,
      unique = false, length = 1500)
  private String storyDescription;
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "authors")
  private Set<String> authors = new HashSet<>(2);
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "genres")
  private Set<String> genres = new HashSet<>(3);
  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "tags")
  private Set<String> tags = new HashSet<>(3);

  // TODO Set<Node>
  @Column(name = "prod_time", updatable = false, nullable = false, length = 24)
  private Date productionDateTime;
  @Column(name = "upd_time", updatable = true, nullable = false, length = 24)
  private Date updatingDateTime;

  public Story() {}

  private Story(Builder builder) {
    storySeriesUuid = builder.storySeriesUuid;
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
    result = 31 * result + (getStoryUuid() != null ? getStoryUuid().hashCode() : 0);
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


  public static final class Builder {

    private UUID storySeriesUuid;
    private UUID startingNodeUuid;
    private String storyTitle;
    private String storyDescription;
    private Set<String> authors;
    private Set<String> genres;
    private Set<String> tags;
    private final Date productionDateTime;
    private final Date updatingDateTime = new Date();

    /** For creating absolute clean story.
     */
    public Builder() {
      this.productionDateTime = new Date();
    }

    /** For modification existing story.
     *
     * @param existing story
     */
    public Builder(Story existing) {
      this.storySeriesUuid = existing.getStorySeriesUuid();
      this.startingNodeUuid = existing.getStartingNodeUuid();
      this.storyTitle = existing.getStoryTitle();
      this.storyDescription = existing.getStoryDescription();
      this.authors = existing.getAuthors();
      this.genres = existing.getGenres();
      this.tags = existing.getTags();
      this.productionDateTime = existing.getProductionDateTime();
    }

    public Builder storySeriesUuid(UUID val) {
      storySeriesUuid = val;
      return this;
    }

    public Builder startingNodeUuid(UUID val) {
      startingNodeUuid = val;
      return this;
    }

    public Builder storyTitle(String val) {
      storyTitle = val;
      return this;
    }

    public Builder storyDescription(String val) {
      storyDescription = val;
      return this;
    }

    public Builder authors(Set<String> val) {
      if (authors == null) {
        authors = val;
      } else {
        authors.addAll(val);
      }
      return this;
    }

    public Builder genres(Set<String> val) {
      if (genres == null) {
        genres = val;
      } else {
        genres.addAll(val);
      }
      return this;
    }

    public Builder tags(Set<String> val) {
      if (tags == null) {
        tags = val;
      } else {
        tags.addAll(val);
      }
      return this;
    }

    public Story build() {
      return new Story(this);
    }
  }
}
