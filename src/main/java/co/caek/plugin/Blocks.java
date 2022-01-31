package co.caek.plugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static co.caek.plugin.CaekPlugin.plugin;
import static org.bukkit.Material.*;

public class Blocks implements Listener {
    // Quick function to make typing stuff out less annoying.
    static public ItemStack IS(Material material) {
        return new ItemStack(material);
    }
    static public ItemStack IS(Material material, int amt) {
        return new ItemStack(material, amt);
    }
    
    // Drop (1) ItemStack when a particular block is broken.
    public static void dropItem(Location loc, ItemStack item) {
        loc.getBlock().setType(AIR);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->
                loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), item), 0); // delay dropping item
    }
    // Drop (1) Material when a particular block is broken.
    public static void dropItem(Location loc, Material A, int amt) {
        if (amt != 0) { dropItem(loc, IS(A, amt)); }
    }
    public static void dropItem(Location loc, Material A) {
        dropItem(loc, A, 1);
    }
    // Drop (2) ItemStacks when a particular block is broken.
    public static void dropItems(Location loc, ItemStack A, ItemStack B) {
        loc.getBlock().setType(AIR);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), A);
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), B);
        }, 0);
    }
    // Drop (2) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB) {
        loc.getBlock().setType(AIR);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(B, amtB));
        }, 0);
    }
    public static void dropItems(Location loc, Material A, Material B) {
        dropItems(loc, A, 1, B, 1);
    }
    // Drop (3) ItemStacks when a particular block is broken.
    public static void dropItems(Location loc, ItemStack A, ItemStack B, ItemStack C) {
        loc.getBlock().setType(AIR);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), A);
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), B);
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), C);
        }, 0);
    }
    // Drop (3) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB, Material C, int amtC) {
        loc.getBlock().setType(AIR);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(B, amtB));
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(C, amtC));
        }, 0);
    }
    public static void dropItems(Location loc, Material A, Material B, Material C) {
        dropItems(loc, A, 1, B, 1, C, 1);
    }

    // Add more where necessary...
    
    // Quick helper function
    public static boolean typeNSEW(Location loc, Material material) {
        return loc.add(1,0,0).getBlock().getType() == material
                | loc.subtract(1,0,0).getBlock().getType() == material
                | loc.add(0,1,0).getBlock().getType() == material
                | loc.subtract(0,1,0).getBlock().getType() == material
                | loc.add(0,0,1).getBlock().getType() == material
                | loc.subtract(0,0,1).getBlock().getType() == material;
    }

    @EventHandler
    // CALLED WHEN BLOCK BROKEN BY PLAYER.
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        BlockData data = block.getBlockData();
        Location loc = block.getLocation();
        Player player = event.getPlayer();
        ItemStack tool = player.getEquipment().getItemInMainHand();

        // Enforce survival mode on players in main plugin class.
        event.setCancelled(true);
        boolean destroy = false;
        switch (block.getType()) {
            // how do we handle getting sticks
            // LEAVES
            case ACACIA_LEAVES -> {
                if (typeNSEW(loc, ACACIA_LOG)) dropItem(loc, ACACIA_SAPLING);
                else dropItem(loc, ACACIA_LEAVES);
            }
            case BIRCH_LEAVES -> {
                if (typeNSEW(loc, BIRCH_LOG)) dropItem(loc, BIRCH_SAPLING);
                else dropItem(loc, BIRCH_LEAVES);
            }
            case DARK_OAK_LEAVES -> {
                if (typeNSEW(loc, DARK_OAK_LOG)) dropItem(loc, DARK_OAK_SAPLING);
                else dropItem(loc, DARK_OAK_LEAVES);
            }
            case JUNGLE_LEAVES -> {
                if (typeNSEW(loc, JUNGLE_LOG)) dropItem(loc, JUNGLE_SAPLING);
                else dropItem(loc, JUNGLE_LEAVES);
            }
            case OAK_LEAVES -> {
                if (typeNSEW(loc, OAK_LOG)) dropItem(loc, OAK_SAPLING);
                else dropItem(loc, OAK_LEAVES);
            }
            case SPRUCE_LEAVES -> {
                if (typeNSEW(loc, SPRUCE_LOG)) dropItem(loc, SPRUCE_SAPLING);
                else dropItem(loc, SPRUCE_LEAVES);
            }
            case AZALEA_LEAVES -> dropItem(loc, AZALEA_LEAVES);
            // FLORA
            case GRASS -> dropItem(loc, GRASS);
            case FERN -> dropItem(loc, FERN);
            case TALL_GRASS -> dropItem(loc, TALL_GRASS);
            case LARGE_FERN -> dropItem(loc, LARGE_FERN);
            case DEAD_BUSH -> dropItem(loc, DEAD_BUSH);
            case SEAGRASS -> dropItem(loc, SEAGRASS);
            case TALL_SEAGRASS -> dropItem(loc, TALL_SEAGRASS);

            // FLOWERS
            case DANDELION -> dropItem(loc, DANDELION);
            case POPPY -> dropItem(loc, POPPY);
            case BLUE_ORCHID -> dropItem(loc, BLUE_ORCHID);
            case ALLIUM -> dropItem(loc, ALLIUM);
            case AZURE_BLUET -> dropItem(loc, AZURE_BLUET);
            case ORANGE_TULIP -> dropItem(loc, ORANGE_TULIP);
            case PINK_TULIP -> dropItem(loc, PINK_TULIP);
            case RED_TULIP -> dropItem(loc, RED_TULIP);
            case WHITE_TULIP -> dropItem(loc, WHITE_TULIP);
            case OXEYE_DAISY -> dropItem(loc, OXEYE_DAISY);
            case CORNFLOWER -> dropItem(loc, CORNFLOWER);
            case LILY_OF_THE_VALLEY -> dropItem(loc, LILY_OF_THE_VALLEY);
            case WITHER_ROSE -> dropItem(loc, WITHER_ROSE);
            case SUNFLOWER -> dropItem(loc, SUNFLOWER);
            case LILAC -> dropItem(loc, LILAC);
            case ROSE_BUSH -> dropItem(loc, ROSE_BUSH);
            case PEONY -> dropItem(loc, PEONY);
            // FARMING
            case WHEAT -> {
                if (((Ageable) data).getAge() == ((Ageable) data).getMaximumAge()) dropItems(loc, WHEAT, 1, WHEAT_SEEDS, 2);
                else dropItem(loc, WHEAT_SEEDS);
            }
            case BEETROOTS -> {
                if (((Ageable) data).getAge() == ((Ageable) data).getMaximumAge()) dropItems(loc, BEETROOT, 1, BEETROOT_SEEDS, 2);
                else dropItem(loc, BEETROOT_SEEDS);
            }
        }


    }
}
