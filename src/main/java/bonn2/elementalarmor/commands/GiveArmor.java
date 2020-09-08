package bonn2.elementalarmor.commands;

import bonn2.elementalarmor.util.ArmorManager;
import bonn2.elementalarmor.util.CustomArmor;
import bonn2.elementalarmor.util.emums.ArmorPiece;
import bonn2.elementalarmor.util.emums.ArmorType;
import bonn2.elementalarmor.util.emums.Charm;
import bonn2.elementalarmor.util.exceptions.InvalidCharmException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveArmor implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        switch (args.length) {
            case 2: {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                ArmorType type;
                try {
                    type = ArmorType.valueOf(args[1].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return false;
                }
                for (CustomArmor item : ArmorManager.getFullSet(type))
                    target.getInventory().addItem(item);
                sender.sendMessage("Successfully gave " + target.getDisplayName() + " the " + type.getFormattedName() + ChatColor.WHITE + " armor set.");
                return true;
            }
            case 3: {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                ArmorType type;
                ArmorPiece piece;
                try {
                    type = ArmorType.valueOf(args[1].toUpperCase());
                    piece = ArmorPiece.valueOf(args[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return false;
                }
                target.getInventory().addItem(ArmorManager.getFullSet(type).get(piece.getIndex()));
                return true;
            }
            case 4: {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) return false;
                ArmorType type;
                ArmorPiece piece;
                Charm charm;
                try {
                    type = ArmorType.valueOf(args[1].toUpperCase());
                    piece = ArmorPiece.valueOf(args[2].toUpperCase());
                    charm = Charm.valueOf(args[3].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return false;
                }
                CustomArmor armor = ArmorManager.getFullSet(type).get(piece.getIndex());
                try {
                    armor.setCharm(charm);
                } catch (InvalidCharmException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid Charm Combination!");
                    return false;
                }
                target.getInventory().addItem(armor);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> output = new ArrayList<>();
        switch (args.length) {
            case 0: {
                for (Player player : Bukkit.getOnlinePlayers())
                    output.add(player.getName());
                break;
            }
            case 1: {
                for (Player player : Bukkit.getOnlinePlayers())
                    if (player.getName().startsWith(args[0]))
                        output.add(player.getName());
                break;
            }
            case 2: {
                for (ArmorType type : ArmorType.values())
                    if (!type.equals(ArmorType.NONE) && type.name().toLowerCase().startsWith(args[1]))
                        output.add(type.name().toLowerCase());
                break;
            }
            case 3: {
                for (ArmorPiece piece : ArmorPiece.values())
                    if (piece.name().toLowerCase().startsWith(args[2].toLowerCase()))
                        output.add(piece.name().toLowerCase());
                break;
            }
            case 4: {
                ArmorType type;
                try {
                    type = ArmorType.valueOf(args[1].toUpperCase());
                } catch (IllegalArgumentException e) {
                    break;
                }
                for (Charm charm : Charm.values())
                    if (charm.getType().equals(type) && charm.name().toLowerCase().startsWith(args[3].toLowerCase()))
                        output.add(charm.name().toLowerCase());
                break;
            }
        }
        return output;
    }
}
