package textify.api.controllers;

import java.sql.Date;
import java.util.Comparator;
import java.util.Random;
import java.util.UUID;
import textify.api.dao.Dao;
import textify.api.dao.StoryDao;
import textify.api.models.Story;

public class Exp {

  public static void main(String[] args) {
    //Date someDate = new Date("1970-01-01T00:00:00.001+00:00");
    Dao<Story> storyDao = new StoryDao();
    var result = storyDao.get(UUID.fromString("2d2f2499-2ac0-4924-82b2-a90d4cd23103"));
    System.out.println(result);
  }
}

