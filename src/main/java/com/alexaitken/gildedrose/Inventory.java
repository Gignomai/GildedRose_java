package com.alexaitken.gildedrose;

public class Inventory {

    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item(DEXTERITY_VEST, 10, 20),
                new Item(AGED_BRIE, 2, 0),
                new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7),
                new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80),
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 15, 20),
                new Item(CONJURED_MANA_CAKE, 3, 6)
        };

    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.getName()) {
                case AGED_BRIE -> updateBrie(item);
                case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT -> updateBackStagePass(item);
                case CONJURED_MANA_CAKE -> updateConjured(item);
                case SULFURAS_HAND_OF_RAGNAROS -> updateLegendary(item);
                default -> updateDefault(item);
            }
        }
    }

    private void updateLegendary(Item item) {
        System.out.println("This item is legendary " + item.getName());
    }

    private void updateBrie(Item item) {
        if (item.getQuality() < 50){
            item.setQuality(item.getQuality() + 1);
        }
        dailySellInReduction(item);
        if (item.getSellIn() < 0 && item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void updateBackStagePass(Item item) {
        if (item.getQuality() < 50){
            item.setQuality(item.getQuality() + 1);
            if (item.getSellIn() < 11 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }

            if (item.getSellIn() < 6 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }
        dailySellInReduction(item);
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        }
    }

    private void updateConjured(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }

        dailySellInReduction(item);

        if (item.getSellIn() < 0) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }

    private void updateDefault(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
        dailySellInReduction(item);
        if (item.getSellIn() < 0) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }

    private void dailySellInReduction(Item item) {
        if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.setSellIn(item.getSellIn() - 1);
        }
    }

}
