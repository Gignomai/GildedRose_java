package com.alexaitken.gildedrose;

public class Inventory {

    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item(AGED_BRIE, 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80),
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

    }

    public void updateQuality() {
        for (Item item : items) {
            dailyQualityReduction(item);

            dailySellInReduction(item);

            reduceQualityOfNonSoldItems(item);
        }
    }

    private void dailyQualityReduction(Item item) {
        if (!item.getName().equals(AGED_BRIE)
                && !item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)
                && !item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
            if (item.getQuality() > 0 && item.getName().toLowerCase().contains("conjured")) {
                item.setQuality(item.getQuality() - 1);
            }
        } else {
            if (item.getQuality() < 50){
                item.setQuality(item.getQuality() + 1);
                if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    updateQualityForBackstagePass(item);
                }
            }
        }
    }

    private static void updateQualityForBackstagePass(Item item) {
        if (item.getSellIn() < 11 && item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }

        if (item.getSellIn() < 6 && item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void dailySellInReduction(Item item) {
        if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.setSellIn(item.getSellIn() - 1);
        }
    }

    private void reduceQualityOfNonSoldItems(Item item) {
        if (item.getSellIn() < 0 && !item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            if (item.getName().equals(AGED_BRIE) && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
            if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                item.setQuality(0);
            }
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
            if (item.getQuality() > 0 && item.getName().toLowerCase().contains("conjured")) {
                item.setQuality(item.getQuality() - 1);
            }
        }

    }
}
