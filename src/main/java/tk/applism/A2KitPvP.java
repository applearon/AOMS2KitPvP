package tk.applism;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;


public class A2KitPvP extends JavaPlugin implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("kit").setExecutor(new kit());
        this.getCommand("a2join").setExecutor(new a2join());
        this.getCommand("spawn").setExecutor(new spawn());
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getEntity().getInventory().clear();
        event.getEntity().sendMessage("You died!");
    }
    //OnLogin Reset location and inv
    @EventHandler
    public void onSpawn(PlayerJoinEvent event) {
        Location spawn = event.getPlayer().getLocation();
        //Set Spawn location & Teleport
        spawn.setX(0.5);
        spawn.setY(51);
        spawn.setZ(0.5);
        event.getPlayer().teleport(spawn);
        event.getPlayer().getInventory().clear();
        event.getPlayer().sendMessage("Welcome to A2KitPvP!!");
    }
    //Mage Fireball
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getPlayer().getEquipment().getItemInMainHand().equals(new ItemStack(Material.STICK))) {
            if(event.getAction() == Action.LEFT_CLICK_AIR) {
            Location place = event.getPlayer().getLocation();
            Player player = event.getPlayer();

            int cooldownTime = 2;
            if(cooldowns.containsKey(player.getName())) {
                long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                    player.sendMessage("Fireball is on Cooldown for: " + secondsLeft + " seconds");
                } else {
                    place.setY(place.getY() + 1);
                    cooldowns.put(player.getName(), System.currentTimeMillis());
                    player.getWorld().spawnEntity(place, EntityType.FIREBALL);
                }
            } else {
                cooldowns.put(player.getName(), System.currentTimeMillis());
            }
        }
    }
    }
}
