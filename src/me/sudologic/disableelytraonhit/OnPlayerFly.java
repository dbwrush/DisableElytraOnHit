package me.sudologic.disableelytraonhit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import java.util.HashMap;
import java.util.logging.Level;

public class OnPlayerFly implements Listener {
    Main main = Main.plugin;
    int timer = main.timer;
    HashMap<Player, Long> recentlyHit = main.recentlyHit;

    @EventHandler
    public void playerFly(EntityToggleGlideEvent e) {
        if(!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player)e.getEntity();
        if(recentlyHit.containsKey(p)) {
            long hitTime = recentlyHit.get(p);
            if(System.currentTimeMillis() - hitTime > timer * 1000) {
                recentlyHit.remove(p);
                Bukkit.getLogger().log(Level.INFO, "[DisableElytraOnHit] " + p.getName() + "'s elytra is restored");
                return;
            } else {
                e.setCancelled(true);
                p.setGliding(false);
            }
        }
    }
}
