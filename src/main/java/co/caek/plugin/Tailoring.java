package co.caek.plugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static org.bukkit.event.block.Action.LEFT_CLICK_BLOCK;

public class Tailoring implements Listener {
    // Fabric colors corresponding to Minecraft dye codes.
    public enum threadColor {
        RED(0), GREEN (1), PURPLE(2), CYAN(3), LIGHT_GRAY(4),
        GRAY(5), PINK(6), LIME(7), YELLOW(8), LIGHT_BLUE(9),
        MAGENTA(10), ORANGE(11), BLACK(12), BROWN(13), BLUE(14),
        WHITE(15);

        private final int colorCode;
        private static final Map<Object, Object> map = new HashMap<>();

        threadColor(int colorCode) { this.colorCode = colorCode; }
        public static threadColor colorOf(int colorCode) { return (threadColor) map.get(colorCode); }
        public String toString() { return name().charAt(0) + name().substring(1).toLowerCase(); }
    }

    // Get dye material corresponding to threadColor.
    static public Material getDye(threadColor color) {
        return Material.valueOf(color+"_DYE");
    }
    // Get thread material.
    private threadColor getThread(ItemStack item) {
        if (item.getItemMeta().hasCustomModelData()) return threadColor.colorOf(item.getItemMeta().getCustomModelData());
        else return threadColor.WHITE;
    }
    // Generate ItemStack of colored String
    static private ItemStack newString(threadColor color, int amt) {
        ItemStack newWoven = new ItemStack(Material.STRING, amt);
        ItemMeta wovenMeta = newWoven.getItemMeta();
        wovenMeta.setCustomModelData(color.colorCode);
        // can't be assed to figure out displayName() which is from net.kyori.adventure.text.Component
        wovenMeta.setDisplayName(properName(color.name() + " String"));
        newWoven.setItemMeta(wovenMeta);
        return newWoven;
    }
    // Generate ItemStack of colored Carpet
    static private ItemStack newCarpet(threadColor color, int amt) {
        return new ItemStack(Material.valueOf(color+"_CARPET"));
    }
    // Generate ItemStack of colored Wool
    static private ItemStack newWool(threadColor color, int amt) {
        return new ItemStack(Material.valueOf(color+"_WOOL"));
    }

    // Convert materials to proper names (MATERIAL_NAME versus "Material Name") - using name() method
    static public String properName(String name) {
        String[] namelets = name.split("_");
        StringBuilder result = new StringBuilder();
        for (short i = 0; i < namelets.length; i++) {
            result.append(namelets[0]);
            if (i != namelets.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @EventHandler
    public void onLoomInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        EntityEquipment equipment = player.getEquipment();
        Block block = event.getClickedBlock();
        // 1) Check if Loom was left-clicked by player with string in hand.
        if (event.getAction() == LEFT_CLICK_BLOCK && block.getType() == Material.LOOM
            && equipment.getItemInMainHand().getType() == Material.STRING) {
            // 2) Check if Loom has string & compare color with string in hand. If none, take color of string in-hand.


            // 3) Animate main hand, update Loom block data and remove from string ItemStack. Drop new Carpet if needed.
        }

    }


}
