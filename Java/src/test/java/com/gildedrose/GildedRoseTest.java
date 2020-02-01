package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GildedRoseTest {

    @Test
    public void testStoreStateIsCorrectAfterTwentyDays() {
        var items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                // this conjured item does not work properly yet
                // Add it when you can!
                //new Item("Conjured Mana Cake", 3, 6),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        };
        var results = new ArrayList<String>();
        var app = new GildedRose(items);

        IntStream.rangeClosed(1, 20)
                .forEach(day -> {
                    Arrays.stream(items)
                            .map(Item::toString)
                            .collect(Collectors.toCollection(() -> results));
                    app.updateQuality();
                });
        var result = String.join("\n", results);

        Approvals.verify(result);
    }
}
