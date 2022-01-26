package me.sudologic.disableelytraonhit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.logging.Level;

public class OnPlayerDamage implements Listener {
    Main main = Main.plugin;
    HashMap recentlyHit = main.recentlyHit;

    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent e) {
        Entity victim = e.getEntity();
        Entity damager = e.getDamager();
        if(!(victim instanceof Player) && !(damager instanceof Player)) {
            return;
        }
        recentlyHit.put((Player)victim, System.currentTimeMillis());
        Bukkit.getLogger().log(Level.INFO, "[DisableElytraOnHit] " + victim.getName() + "'s eytra is disabled for " + main.timer + " seconds.");
    }
}