package textify.api.models;


import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Must strictly comply with the story.json scheme.
 */
public class Story {

  private UUID storySeriesUuid;
  private UUID storyUuid;
  private UUID startingNodeUuid;
  private String storyTitle;
  private String storyDescription;
  private Set<String> authors;
  private Set<String> genres;
  private Set<String> tags;
  private Date productionDateTime;
  private Date updatingDateTime;

  public Story() {}

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
    private UUID storyUuid;
    private UUID startingNodeUuid;
    private String storyTitle;
    private String storyDescription;
    private Set<String> authors;
    private Set<String> genres;
    private Set<String> tags;
    private Date productionDateTime;
    private Date updatingDateTime;

    public Builder() {
    }

    public Builder storySeriesUuid(UUID val) {
      storySeriesUuid = val;
      return this;
    }

    public Builder storyUuid(UUID val) {
      storyUuid = val;
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
      authors = val;
      return this;
    }

    public Builder genres(Set<String> val) {
      genres = val;
      return this;
    }

    public Builder tags(Set<String> val) {
      tags = val;
      return this;
    }

    public Builder productionDateTime(Date val) {
      productionDateTime = val;
      return this;
    }

    public Builder updatingDateTime(Date val) {
      updatingDateTime = val;
      return this;
    }

    public Story build() {
      return new Story(this);
    }
  }
}
