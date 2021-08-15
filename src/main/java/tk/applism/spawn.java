package tk.applism;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage("Sending you to Spawn...");
            Location spawn = player.getLocation();
            //Set Spawn Location
            spawn.setX(0.5);
            spawn.setY(51);
            spawn.setZ(0.5);
            player.teleport(spawn);

        }
        return true;
    }
}
