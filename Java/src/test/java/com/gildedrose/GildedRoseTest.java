package com.gildedrose;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GildedRoseTest {

    @Test
    public void testStoreIsCorrectAfterTenDays() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};
        List<String> results = new ArrayList<>();
        GildedRose app = new GildedRose(items);
        IntStream.rangeClosed(1, 20)
                .forEach(day -> {
                    Arrays.stream(items)
                            .map(Item::toString)
                            .collect(Collectors.toCollection(() -> results));
                    app.updateQuality();
                });
        String result = String.join("\n", results);

        assertEquals(getExpectedResult(), result);
    }

    private String getExpectedResult() {
        try {
            URL resource = getClass().getResource("/GoldMaster.txt");
            return Files.readString(Paths.get(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            fail("Unable to read resource file");
            return "";
        }
    }
}
