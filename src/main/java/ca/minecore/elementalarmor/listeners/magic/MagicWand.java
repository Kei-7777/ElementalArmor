package ca.minecore.elementalarmor.listeners.magic;

import ca.minecore.elementalarmor.magic.Magic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class MagicWand implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        try {
            Magic.run(e.getPlayer(), e.getItem(), e.getClickedBlock(), e.getAction());
        } catch (Exception exception) {
        }
    }

}
