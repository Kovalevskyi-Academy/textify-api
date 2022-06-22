package textify.api.models;


import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Must strictly comply with the story.json scheme.
 */
public class Story {

  private final UUID storySeriesUuid;
  private final UUID storyUuid;
  private final UUID startingNodeUuid;
  private final String storyTitle;
  private final String storyDescription;
  private final Set<String> authors;
  private final Set<String> genres;
  private final Set<String> tags;
  private final Date productionDateTime;
  private final Date updatingDateTime;

  public Story(UUID storySeriesUuid, UUID storyUuid, UUID startingNodeUuid, String storyTitle,
      String storyDescription, Set<String> authors, Set<String> genres, Set<String> tags,
      Date productionDateTime, Date updatingDateTime) {
    this.storySeriesUuid = storySeriesUuid;
    this.storyUuid = storyUuid;
    this.startingNodeUuid = startingNodeUuid;
    this.storyTitle = storyTitle;
    this.storyDescription = storyDescription;
    this.authors = authors;
    this.genres = genres;
    this.tags = tags;
    this.productionDateTime = productionDateTime;
    this.updatingDateTime = updatingDateTime;
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
}
