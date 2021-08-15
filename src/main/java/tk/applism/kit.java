package tk.applism;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;


import javax.swing.*;
import java.util.*;

public class kit implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String kitType, String[] args) {
        if (sender instanceof Player player) {
            ItemStack foob = new ItemStack(Material.COOKED_BEEF);
            ItemStack arow = new ItemStack(Material.ARROW);
            arow.setAmount(64);
            foob.setAmount(32);
            if (args[0] == null) {
                player.sendMessage("Please enter a valid kit (sword, archer, brute, mage)");
                return false;
            } else {
                switch (args[0]) {
                    case "sword" -> {
                        player.getInventory().clear();
                        if (player.getInventory().isEmpty()) {
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD), foob);
                            player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                            player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                            player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                            player.sendMessage("Sword Kit Given!");
                            return true;
                        }
                    }
                    case "archer" -> {
                        player.getInventory().clear();
                        if (player.getInventory().isEmpty()) {
                            ItemStack crosbow = new ItemStack(Material.CROSSBOW);
                            crosbow.addEnchantment(Enchantment.MULTISHOT, 1);
                            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD), crosbow, foob);
                            player.getInventory().setItem(9, arow);
                            player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                            player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                            player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                            player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                            player.sendMessage("Archer Kit Given!");
                            return true;
                        }
                    }
                    case "brute" -> {
                        player.getInventory().clear();
                        if (player.getInventory().isEmpty()) {
                            ItemStack wobax = new ItemStack(Material.STONE_AXE);
                            wobax.addEnchantment(Enchantment.DURABILITY, 3);
                            ItemStack goldpants = new ItemStack(Material.GOLDEN_LEGGINGS);
                            ItemStack goldshirt = new ItemStack(Material.GOLDEN_CHESTPLATE);
                            ItemStack goldhat = new ItemStack(Material.GOLDEN_HELMET);
                            goldpants.addEnchantment(Enchantment.DURABILITY, 3);
                            goldshirt.addEnchantment(Enchantment.DURABILITY, 3);
                            goldhat.addEnchantment(Enchantment.DURABILITY, 3);
                            player.getInventory().addItem(wobax, foob);
                            player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                            player.getInventory().setLeggings(goldpants);
                            player.getInventory().setChestplate(goldshirt);
                            player.getInventory().setHelmet(goldhat);
                            player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                            player.sendMessage("Brute Kit Given!");
                            return true;
                        }
                    }
                    case "mage" -> {
                        player.getInventory().clear();
                        if (player.getInventory().isEmpty()) {
                            ItemStack wand = new ItemStack(Material.STICK);
                            ItemStack woobsword = new ItemStack(Material.WOODEN_SWORD);
                            woobsword.addEnchantment(Enchantment.DURABILITY, 3);
                            ItemStack goldpants = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack goldshirt = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack goldshoes = new ItemStack(Material.LEATHER_BOOTS);
                            goldpants.addEnchantment(Enchantment.DURABILITY, 3);
                            goldshirt.addEnchantment(Enchantment.DURABILITY, 3);
                            goldshoes.addEnchantment(Enchantment.DURABILITY, 3);
                            player.getInventory().addItem(wand, woobsword, foob);
                            player.getInventory().setBoots(goldshoes);
                            player.getInventory().setLeggings(goldpants);
                            player.getInventory().setChestplate(goldshirt);
                            player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                            player.sendMessage("Mage Kit Given!");
                            return true;
                        }
                    }
                    default -> {
                        player.sendMessage("Please enter a valid kit (sword, archer, brute)");
                        return false;
                    }
                }
        }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        Iterable<String> COMMANDS = Arrays.asList("sword", "archer", "brute", "mage");
        StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
        Collections.sort(completions);
        return completions;
    }
}
