package kr.cosine.cosmeticscorebridge.data;

import org.bukkit.inventory.ItemStack;

public class Cosmetics {

    public boolean isNotNull = true;

    private String key;

    private String displayName;

    private ItemStack itemStack;

    public String getKey() {
        return key;
    }

    public Cosmetics setKey(String key) {
        this.key = key;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Cosmetics setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Cosmetics setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }
}
