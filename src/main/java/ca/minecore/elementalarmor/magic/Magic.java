package ca.minecore.elementalarmor.magic;

import ca.minecore.elementalarmor.util.emums.WandType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Magic {

    private static Map<String, Magic> spells = new HashMap<>();

    public static void reset() {
        register(WandType.BLINK.name(), new Blink());
    }

    public static void register(String resigterName, Magic clazz) {
        if (clazz.equals(Magic.class))
            throw new IllegalArgumentException("Do not resister \"Magic.class\". It will loop!!");
        spells.put(resigterName, clazz);
    }


    public static void run(Player player, ItemStack item, Block b, Action action) throws Exception {
        for (Map.Entry<String, Magic> m : spells.entrySet()) {
            for (WandType w : WandType.values()) {
                try {
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && w.displayname.equals(item.getItemMeta().getDisplayName())) {
                        m.getValue().call(player, item, b, action);
                        break;
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    public abstract void call (Player player, ItemStack item, Block b, Action action);

    }
