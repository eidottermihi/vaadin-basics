package de.akquinet.engineering.vaadin.grid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public final class PlayerGenerator
{
    private final Random random = new Random(System.nanoTime());

    private final List<String> femaleFirstNames = Arrays.asList(
                                                                "Sarah",
                                                                "Barbara",
                                                                "Anna",
                                                                "Christine",
                                                                "Zoey",
                                                                "Trisha",
                                                                "Fiona",
                                                                "Samantha",
                                                                "Martina",
                                                                "Vanessa",
                                                                "Eva",
                                                                "Claudia",
                                                                "Lisa",
                                                                "Vivian",
                                                                "Debrah",
                                                                "Elisabeth",
                                                                "Dora",
                                                                "Wilma",
                                                                "Mina",
                                                                "Kelly",
                                                                "Candy");
    private final List<String> maleFirstNames = Arrays.asList(
                                                              "Jim",
                                                              "Peter",
                                                              "Arnold",
                                                              "Bruno",
                                                              "Charlie",
                                                              "Dennis",
                                                              "Fritz",
                                                              "Hans",
                                                              "Michael",
                                                              "John",
                                                              "Frank",
                                                              "Steve",
                                                              "Gerd",
                                                              "Floyd",
                                                              "Raphael",
                                                              "Ulrich",
                                                              "Walter",
                                                              "Toby",
                                                              "Herrmann",
                                                              "Dirk",
                                                              "Fridolin",
                                                              "Kermit",
                                                              "Jack");
    private final List<String> lastNames = Arrays.asList(
                                                         "Jackson",
                                                         "Masterson",
                                                         "Gutschmeck",
                                                         "Schmelzer",
                                                         "Dreyfuss",
                                                         "Kraftmeyer",
                                                         "White",
                                                         "Weiss",
                                                         "Wurst",
                                                         "Hase",
                                                         "Wolke",
                                                         "Hammer",
                                                         "Trump",
                                                         "Drumpf",
                                                         "Bollwerk",
                                                         "Baum",
                                                         "Stein",
                                                         "Stieglitz",
                                                         "Miller",
                                                         "Schmidt",
                                                         "Schulze",
                                                         "Gutmensch",
                                                         "Turner");

    private PlayerGenerator()
    {
    }

    public static List<Player> createPlayers(final int count)
    {
        final PlayerGenerator playerGenerator = new PlayerGenerator();
        return playerGenerator.createPlayerList(count);
    }

    private Player createPlayer()
    {
        final Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
        final LocalDate startDate = LocalDate.of(1950, 1, 1);
        final LocalDate dateOfBirth = startDate.plusDays(random.nextInt(50 * 365));
        final int points = createPoints(100000);
        final int medals = Math.max(0, createPoints(points/1000) - 10);
        return new Player(String.format(Locale.ROOT, "%s %s", getFirstName(sex), pick(lastNames)),
                dateOfBirth, sex, points, medals);
    }

    private int createPoints(final int max){
        final float sum = max * ((random.nextFloat() + random.nextFloat() + random.nextFloat()) / 3.0f);
        return Math.round(sum);
    }

    private List<Player> createPlayerList(final int count)
    {
        final List<Player> playerList = new ArrayList<>(count);
        for (int i = 0; i < count; ++i)
        {
            playerList.add(createPlayer());
        }
        playerList.sort(Comparator.comparing(Player::getPoints).reversed());
        return playerList;
    }

    private <T> T pick(final List<? extends T> list)
    {
        return list.get(random.nextInt(list.size()));
    }

    private String getFirstName(final Sex sex)
    {
        final List<String> firstNames = sex == Sex.FEMALE
                ? femaleFirstNames
                : maleFirstNames;
        return pick(firstNames);
    }
}
