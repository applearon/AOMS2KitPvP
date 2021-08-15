package tk.applism;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

public class a2join implements CommandExecutor, TabCompleter {
    Player Player1 = null;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {

            if (args[0] == null) {
                player.sendMessage("Please enter a valid Arena: (arena1, arena2, spawn)");
                return false;
            } else switch (args[0]) {
                case "spawn" -> {
                    player.sendMessage("Sending you to Spawn...");
                    Location spawn = player.getLocation();
                    //Set Spawn Location
                    spawn.setX(0.5);
                    spawn.setY(51);
                    spawn.setZ(0.5);
                    player.teleport(spawn);
                }
                case "arena1" -> {
                    if (Player1 == null) {
                        Player1 = player;
                        player.sendMessage("Joined Queue 1/2");
                        return true;
                    } else if (Player1 != null && Player1 != player) {
                        Player Player2 = player;
                        Player[] Players = {Player1, Player2};
                        for (Player pvper : Players) {
                            pvper.sendMessage("Sending you to Arena 1...");
                            Location arena1 = pvper.getLocation();
                            //Set Arena Location
                            arena1.setX(-199.5);
                            arena1.setY(53);
                            arena1.setZ(0.5);
                            pvper.teleport(arena1);
                        }
                        Player1 = null;
                    } else if (Player1 == player) {
                        player.sendMessage("You already queued for Arena1!");

                    }
                    return true;
                }
                case "arena2" -> {
                    player.sendMessage("Sending you to Arena 2...");
                    Location arena2 = player.getLocation();
                    //Set Arena Location
                    arena2.setX(200.5);
                    arena2.setY(53);
                    arena2.setZ(0.5);
                    player.teleport(arena2);
                }
            }
        }
    return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        Iterable<String> COMMANDS = Arrays.asList("arena1","arena2", "spawn");
        StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
        Collections.sort(completions);
        return completions;
    }
}
