package ca.minecore.elementalarmor.util;

import ca.minecore.elementalarmor.Main;
import ca.minecore.elementalarmor.util.emums.ArmorPiece;
import ca.minecore.elementalarmor.util.emums.ArmorType;
import ca.minecore.elementalarmor.util.emums.Charm;
import ca.minecore.elementalarmor.util.persistence.PersistentArmorType;
import ca.minecore.elementalarmor.util.persistence.PersistentCharmType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArmorManager {

    private static final NamespacedKey TYPE = new NamespacedKey(Main.plugin, "TYPE");
    private static final NamespacedKey CHARM = new NamespacedKey(Main.plugin, "CHARM");

    public static boolean isWearingFullSet(Player player, ArmorType type) {
        boolean output = true;
        try {
            for (ItemStack item : player.getInventory().getArmorContents()) {
                ArmorType itemType = Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().getOrDefault(TYPE, new PersistentArmorType(), ArmorType.NONE);
                if (itemType.equals(type)) continue;
                output = false;
            }
            return output;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean isWearingCharm(Player player, Charm charm) {
        boolean output = false;
        for (ItemStack item : player.getInventory().getArmorContents()) {
            try {
                Charm itemCharm = Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().getOrDefault(CHARM, new PersistentCharmType(), Charm.NONE);
                if (itemCharm.equals(charm)) {
                    output = true;
                    break;
                }
            } catch (NullPointerException ignored) {}
        }
        return output;
    }

    public static boolean hasCharm(ItemStack item, Charm charm) {
        try {
            return item.getItemMeta().getPersistentDataContainer().getOrDefault(CHARM, new PersistentCharmType(), Charm.NONE).equals(charm);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static Charm getCharm(ItemStack item) {
        try {
            return item.getItemMeta().getPersistentDataContainer().getOrDefault(CHARM, new PersistentCharmType(), Charm.NONE);
        } catch (NullPointerException e) {
            return Charm.NONE;
        }
    }

    public static ArmorType getType(ItemStack item) {
        try {
            return item.getItemMeta().getPersistentDataContainer().getOrDefault(TYPE, new PersistentArmorType(), ArmorType.NONE);
        } catch (NullPointerException e) {
            return ArmorType.NONE;
        }
    }

    public static List<CustomArmor> getFullSet(ArmorType type) {
        List<CustomArmor> set = new ArrayList<>();
        set.add(new CustomArmor(type, ArmorPiece.HELMET));
        set.add(new CustomArmor(type, ArmorPiece.CHESTPLATE));
        set.add(new CustomArmor(type, ArmorPiece.LEGGINGS));
        set.add(new CustomArmor(type, ArmorPiece.BOOTS));
        return set;
    }
}
