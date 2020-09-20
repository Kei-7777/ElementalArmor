package ca.minecore.elementalarmor.util.emums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public enum WandType {

    BLINK("Blink", new ItemStack(Material.STICK, 1, (short) 0), ChatColor.RED + "Blink", Action.RIGHT_CLICK_AIR),
    SUMMON_LIGHTNING("Lightning", new ItemStack(Material.STICK, 1, (short) 0), ChatColor.YELLOW + "Lightning", Action.RIGHT_CLICK_AIR);


    String name;
    ItemStack item;
    public String displayname;
    Action action;

    WandType(String name, ItemStack itemStack, String displayname, Action action) {
        this.name = name;
        this.item = itemStack;
        this.displayname = displayname;
        this.action = action;
    }
}
