package co.caek.plugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

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
        if (item.getType() == AIR) return; // CANNOT DROP AIR ERROR
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), item);
            loc.getBlock().setType(AIR);
        }, 0); // delay dropping item
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

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), A);
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), B);
            loc.getBlock().setType(AIR);
        }, 0);

    }
    // Drop (2) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(B, amtB));
            loc.getBlock().setType(AIR);
        }, 0);
    }
    public static void dropItems(Location loc, Material A, Material B) {
        dropItems(loc, A, 1, B, 1);
    }
    // Drop (3) ItemStacks when a particular block is broken.
    public static void dropItems(Location loc, ItemStack A, ItemStack B, ItemStack C) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), A);
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), B);
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), C);
            loc.getBlock().setType(AIR);
        }, 0);
    }
    // Drop (3) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB, Material C, int amtC) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(0.5,0.5,0.5), IS(B, amtB));
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(C, amtC));
            loc.getBlock().setType(AIR);
        }, 0);
    }
    public static void dropItems(Location loc, Material A, Material B, Material C) {
        dropItems(loc, A, 1, B, 1, C, 1);
    }

    // Add more where necessary...

    // IMPLEMENTED QUICKLY FOR MINING
    // Drop (1) ItemStack when a particular block is broken - NOT REPLACED WITH AIR
    public static void harvestItem(Location loc, ItemStack item, Material block) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> loc.getWorld().dropItem(loc.add(0.5,0.5,0.5), item), 0); // delay dropping item
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> loc.getBlock().setType(block), 5); // delay dropping item
    }
    // Drop (1) Material when a particular block is broken.
    public static void harvestItem(Location loc, Material A, int amt, Material block) {
        if (amt != 0) { harvestItem(loc, IS(A, amt), block); }
    }
    public static void harvestItem(Location loc, Material A, Material block) {
        harvestItem(loc, A, 1, block);
    }
    
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
        switch (block.getType()) {
            // how do we handle getting sticks
            // LEAVES
            case ACACIA_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, ACACIA_SAPLING);
                else dropItem(loc, ACACIA_SAPLING);
            }
            case BIRCH_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, BIRCH_SAPLING);
                else dropItem(loc, BIRCH_LEAVES);
            }
            case DARK_OAK_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, DARK_OAK_SAPLING);
                else dropItem(loc, DARK_OAK_LEAVES);
            }
            case JUNGLE_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, JUNGLE_SAPLING);
                else dropItem(loc, JUNGLE_LEAVES);
            }
            case OAK_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, OAK_SAPLING);
                else dropItem(loc, OAK_LEAVES);
            }
            case SPRUCE_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) dropItem(loc, SPRUCE_SAPLING);
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
            // MINING
            case COAL_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, COAL, 4, STONE);
            }
            case DEEPSLATE_COAL_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, COAL, 4, DEEPSLATE);
            }
            case COPPER_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_COPPER, 4, STONE);
            }
            case DEEPSLATE_COPPER_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_COPPER, 4, DEEPSLATE);
            }
            case LAPIS_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, LAPIS_LAZULI, 4, STONE);
            }
            case DEEPSLATE_LAPIS_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, LAPIS_LAZULI, 4, DEEPSLATE);
            }
            case IRON_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_IRON, 4, STONE);
            }
            case DEEPSLATE_IRON_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_IRON, 4, DEEPSLATE);
            }
            case GOLD_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_GOLD, 4, STONE);
            }
            case DEEPSLATE_GOLD_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, RAW_GOLD, 4, DEEPSLATE);
            }
            case REDSTONE_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, REDSTONE, 4, STONE);
            }
            case DEEPSLATE_REDSTONE_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, REDSTONE, 4, DEEPSLATE);
            }
            case DIAMOND_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, DIAMOND, 4, STONE);
            }
            case DEEPSLATE_DIAMOND_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, DIAMOND, 4, DEEPSLATE);
            }
            case EMERALD_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, EMERALD, 4, STONE);
            }
            case DEEPSLATE_EMERALD_ORE -> {
                if (MINE_TOOL.contains(tool.getType())) harvestItem(loc, EMERALD, 4, DEEPSLATE);
            }
        }



    }
    // Digging tool list (placeholder)
    public static final Set<Material> DIG_TOOL = new HashSet<>();
    static {
        DIG_TOOL.add(Material.WOODEN_SHOVEL);
        DIG_TOOL.add(Material.STONE_SHOVEL);
        DIG_TOOL.add(Material.IRON_SHOVEL);
        DIG_TOOL.add(Material.GOLDEN_SHOVEL);
        DIG_TOOL.add(Material.DIAMOND_SHOVEL);
        DIG_TOOL.add(Material.NETHERITE_SHOVEL);
    }

    // Chopping tool list (placeholder)
    public static final Set<Material> CHOP_TOOL = new HashSet<>();
    static {
        CHOP_TOOL.add(Material.WOODEN_AXE);
        CHOP_TOOL.add(Material.STONE_AXE);
        CHOP_TOOL.add(Material.IRON_AXE);
        CHOP_TOOL.add(Material.GOLDEN_AXE);
        CHOP_TOOL.add(Material.DIAMOND_AXE);
        CHOP_TOOL.add(Material.NETHERITE_AXE);
    }

    // Mining tool list (placeholder)
    public static final Set<Material> MINE_TOOL = new HashSet<>();
    static {
        MINE_TOOL.add(Material.WOODEN_PICKAXE);
        MINE_TOOL.add(Material.STONE_PICKAXE);
        MINE_TOOL.add(Material.IRON_PICKAXE);
        MINE_TOOL.add(Material.GOLDEN_PICKAXE);
        MINE_TOOL.add(Material.DIAMOND_PICKAXE);
        MINE_TOOL.add(Material.NETHERITE_PICKAXE);
    }

    // Cutting tool list (placeholder)
    public static final Set<Material> CUT_TOOL = new HashSet<>();
    static {
        CUT_TOOL.add(Material.WOODEN_SWORD);
        CUT_TOOL.add(Material.STONE_SWORD);
        CUT_TOOL.add(Material.IRON_SWORD);
        CUT_TOOL.add(Material.GOLDEN_SWORD);
        CUT_TOOL.add(Material.DIAMOND_SWORD);
        CUT_TOOL.add(Material.NETHERITE_SWORD);
        // CUT_TOOL.add(Material.SHEARS);
    }

    // Clearing tool list (placeholder)
    public static final Set<Material> CLEAR_TOOL = new HashSet<>();
    static {
        CLEAR_TOOL.add(Material.WOODEN_HOE);
        CLEAR_TOOL.add(Material.STONE_HOE);
        CLEAR_TOOL.add(Material.IRON_HOE);
        CLEAR_TOOL.add(Material.GOLDEN_HOE);
        CLEAR_TOOL.add(Material.DIAMOND_HOE);
        CLEAR_TOOL.add(Material.NETHERITE_HOE);
    }
}
