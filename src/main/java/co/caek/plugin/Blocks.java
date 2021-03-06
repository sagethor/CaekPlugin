package co.caek.plugin;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
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

import java.util.Arrays;
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
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), item);
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
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), A);
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), B);
            loc.getBlock().setType(AIR);
        }, 0);

    }
    // Drop (2) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(B, amtB));
            loc.getBlock().setType(AIR);
        }, 0);
    }
    public static void dropItems(Location loc, Material A, Material B) {
        dropItems(loc, A, 1, B, 1);
    }
    // Drop (3) ItemStacks when a particular block is broken.
    public static void dropItems(Location loc, ItemStack A, ItemStack B, ItemStack C) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), A);
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), B);
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), C);
            loc.getBlock().setType(AIR);
        }, 0);
    }
    // Drop (3) Materials when a particular block is broken.
    public static void dropItems(Location loc, Material A, int amtA, Material B, int amtB, Material C, int amtC) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(A, amtA));
            loc.getWorld().dropItemNaturally(loc.add(.5,.5,.5), IS(B, amtB));
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
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItem(loc.add(0.5,0.5,0.5), item);
            loc.getBlock().setType(block);
        }, 0); // delay dropping item
    }
    // Drop (1) Material when a particular block is broken.
    public static void harvestItem(Location loc, Material A, int amt, Material block) {
        if (amt != 0) { harvestItem(loc, IS(A, amt), block); }
    }
    public static void harvestItem(Location loc, Material A, Material block) {
        harvestItem(loc, A, 1, block);
    }

    // Drop (1) ItemStack when a particular block is broken - X in 16 CHANCE TO REPLACE WITH AIR
    public static void harvestItem(Location loc, ItemStack item, Material block, int X) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            loc.getWorld().dropItem(loc.add(0.5,0.5,0.5), item);
            loc.getBlock().setType(lrng8.XinSixten(X) ? AIR : block);
        }, 0); // delay dropping item
    }
    // Drop (1) Material when a particular block is broken - X in 16 CHANCE TO REPLACE WITH AIR
    public static void harvestItem(Location loc, Material A, int amt, Material block, int X) {
        if (amt != 0) { harvestItem(loc, IS(A, amt), block, X); }
    }
    public static void harvestItem(Location loc, Material A, Material block, int X) {
        harvestItem(loc, A, 1, block, X);
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

    private static void dropSelf(Block block) { dropItem(block.getLocation(), block.getType()); }
    private static boolean isMined(ItemStack tool) { return MINE_TOOL.contains(tool.getType()); }
    private static boolean isDug(ItemStack tool) { return DIG_TOOL.contains(tool.getType()); }
    private static boolean isCut(ItemStack tool) { return CUT_TOOL.contains(tool.getType()); }
    private static boolean isCleared(ItemStack tool) { return CLEAR_TOOL.contains(tool.getType()); }
    private static boolean isChopped(ItemStack tool) { return CHOP_TOOL.contains(tool.getType()); }

    @EventHandler
    public void onBlockBreakBlockEvent(BlockBreakBlockEvent event) {}

    @EventHandler
    // CALLED WHEN BLOCK BROKEN BY PLAYER.
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        BlockData data = block.getBlockData();
        Location loc = block.getLocation();
        Player player = event.getPlayer();
        ItemStack tool = player.getEquipment().getItemInMainHand();
        event.setCancelled(true);
        // Try to keep all cases as one-liners or combined
        switch (material) {
            // how do we handle getting sticks
            // LEAVES
            case ACACIA_LEAVES, BIRCH_LEAVES, DARK_OAK_LEAVES, JUNGLE_LEAVES, OAK_LEAVES, SPRUCE_LEAVES -> {
                Leaves leaves = (Leaves) data;
                if (leaves.getDistance() == 1 && !leaves.isPersistent()) {
                    dropItem(loc, Material.valueOf(material.name().split("_")[0] + "_SAPLING"));
                } else if (isCleared(tool)) {
                    dropItem(loc, STICK);
                } else {
                    dropSelf(block);
                }
            }
            case AZALEA_LEAVES -> dropSelf(block);
            // FLORA
            case GRASS, FERN, TALL_GRASS, LARGE_FERN, DEAD_BUSH, SEAGRASS, TALL_SEAGRASS, BAMBOO, SUGAR_CANE
                    -> dropSelf(block);
            // FLOWERS
            case DANDELION, POPPY, BLUE_ORCHID, ALLIUM, AZURE_BLUET, ORANGE_TULIP, PINK_TULIP, RED_TULIP, WHITE_TULIP,
                    OXEYE_DAISY, CORNFLOWER, LILY_OF_THE_VALLEY, WITHER_ROSE, SUNFLOWER, LILAC, ROSE_BUSH, PEONY ->
                    dropSelf(block);
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
            // Base blocks - to be refactored
            case BASALT, BLACKSTONE, DEEPSLATE, END_STONE, NETHERRACK, SANDSTONE, TERRACOTTA, COBBLESTONE,
                    MOSSY_COBBLESTONE -> { if (isMined(tool)) dropSelf(block); }
            // consider right click to quarry a block
            case STONE -> { if (isMined(tool)) dropItem(loc, COBBLESTONE); }

            // Stone Ores
            case LAPIS_ORE -> { if (isMined(tool)) harvestItem(loc, LAPIS_LAZULI, 4, STONE); }
            case COAL_ORE, REDSTONE_ORE, DIAMOND_ORE, EMERALD_ORE -> {
                if (isMined(tool)) harvestItem(loc, Material.valueOf(material.name().split("_")[0]), 4, STONE);
            }
            case COPPER_ORE, IRON_ORE, GOLD_ORE -> {
                if (isMined(tool)) harvestItem(loc, Material.valueOf("RAW_" + material.name().split("_")[0]), 4, STONE);
            }

            // Deepslate Ores
            case DEEPSLATE_LAPIS_ORE -> { if (isMined(tool)) harvestItem(loc, LAPIS_LAZULI, 4, DEEPSLATE); }
            case DEEPSLATE_COAL_ORE, DEEPSLATE_REDSTONE_ORE, DEEPSLATE_DIAMOND_ORE, DEEPSLATE_EMERALD_ORE -> {
                if (isMined(tool)) harvestItem(loc, Material.valueOf(material.name().split("_")[1]), 4, DEEPSLATE);
            }
            case DEEPSLATE_COPPER_ORE, DEEPSLATE_IRON_ORE, DEEPSLATE_GOLD_ORE -> {
                if (isMined(tool)) harvestItem(loc, Material.valueOf("RAW_" + material.name().split("_")[1]), 4, DEEPSLATE);
            }

            /*
            case ANDESITE_STAIRS, BLACKSTONE_STAIRS, BRICK_STAIRS, COBBLED_DEEPSLATE_STAIRS, COBBLESTONE_STAIRS,
                    CUT_COPPER_STAIRS, DARK_PRISMARINE_STAIRS, DEEPSLATE_BRICK_STAIRS, DEEPSLATE_TILE_STAIRS,
                    DIORITE_STAIRS, END_STONE_BRICK_STAIRS, MOSSY_STONE_BRICK_STAIRS, GRANITE_STAIRS, STONE_STAIRS,
                    NETHER_BRICK_STAIRS, EXPOSED_CUT_COPPER_STAIRS, OXIDIZED_CUT_COPPER_STAIRS, PRISMARINE_STAIRS,
                    MOSSY_COBBLESTONE_STAIRS, POLISHED_ANDESITE_STAIRS, POLISHED_BLACKSTONE_BRICK_STAIRS, PURPUR_STAIRS,
                    POLISHED_BLACKSTONE_STAIRS, POLISHED_DEEPSLATE_STAIRS, POLISHED_DIORITE_STAIRS, STONE_BRICK_STAIRS,
                    POLISHED_GRANITE_STAIRS, QUARTZ_STAIRS, SMOOTH_QUARTZ_STAIRS, PRISMARINE_BRICK_STAIRS,
                    SANDSTONE_STAIRS, RED_SANDSTONE_STAIRS, SMOOTH_RED_SANDSTONE_STAIRS, SMOOTH_SANDSTONE_STAIRS,
                    RED_NETHER_BRICK_STAIRS, WAXED_CUT_COPPER_STAIRS, WAXED_EXPOSED_CUT_COPPER_STAIRS,
                    WAXED_OXIDIZED_CUT_COPPER_STAIRS, WAXED_WEATHERED_CUT_COPPER_STAIRS, WEATHERED_CUT_COPPER_STAIRS ->
                    { if (isMined(tool)) dropSelf(block); }
             */

            // DIGGING
            // re-evaluate GRASS & MYCELIUM
            case DIRT, GRASS_BLOCK, MYCELIUM, SAND -> { if (isDug(tool)) dropSelf(block); }
            case SNOW -> {}
            case GRAVEL -> {
                if (isDug(tool)) dropSelf(block);
                else if (tool.getType() == AIR) harvestItem(loc, FLINT, 1, GRAVEL, 1);
            }
            case CLAY -> {

            }

            // CHOPPING
            // Regular Logs
            case ACACIA_LOG, BIRCH_LOG, CRIMSON_STEM, DARK_OAK_LOG, JUNGLE_LOG, OAK_LOG, SPRUCE_LOG, WARPED_STEM ->
                    { if (isChopped(tool)) dropSelf(block); }
            case ACACIA_PLANKS, BIRCH_PLANKS, CRIMSON_PLANKS, DARK_OAK_PLANKS, JUNGLE_PLANKS, OAK_PLANKS, SPRUCE_PLANKS,
                    WARPED_PLANKS -> { if (isChopped(tool)) dropSelf(block); }
            case STRIPPED_ACACIA_LOG, STRIPPED_BIRCH_LOG, STRIPPED_CRIMSON_STEM, STRIPPED_DARK_OAK_LOG,
                    STRIPPED_JUNGLE_LOG, STRIPPED_OAK_LOG, STRIPPED_SPRUCE_LOG, STRIPPED_WARPED_STEM ->
                    { if (isChopped(tool)) dropSelf(block); }
            case STRIPPED_ACACIA_WOOD, STRIPPED_BIRCH_WOOD, STRIPPED_CRIMSON_HYPHAE, STRIPPED_DARK_OAK_WOOD,
                    STRIPPED_JUNGLE_WOOD, STRIPPED_OAK_WOOD, STRIPPED_SPRUCE_WOOD, STRIPPED_WARPED_HYPHAE ->
                    { if (isChopped(tool)) dropSelf(block); }
            case ACACIA_STAIRS, BIRCH_STAIRS, CRIMSON_STAIRS, DARK_OAK_STAIRS, JUNGLE_STAIRS, SPRUCE_STAIRS,
                    WARPED_STAIRS, OAK_STAIRS -> { if (isCut(tool)) dropSelf(block); }
            case ACACIA_SLAB, BIRCH_SLAB, CRIMSON_SLAB, DARK_OAK_SLAB, JUNGLE_SLAB, SPRUCE_SLAB, WARPED_SLAB, OAK_SLAB
                    -> { if (isChopped(tool)) dropSelf(block); }
        }



    }

    // VERIFY TOOLS LOSE DURABILITY?
    
    // Generic HashSet mass constructor
    public static void makeMaterialList(Set<Material> set, Material ... materials) {
        set.addAll(Arrays.asList(materials));
    }
    // PLACEHOLDER TOOL LISTS
    public static final Set<Material> DIG_TOOL = new HashSet<>();
    public static final Set<Material> CHOP_TOOL = new HashSet<>();
    public static final Set<Material> MINE_TOOL = new HashSet<>();
    public static final Set<Material> CUT_TOOL = new HashSet<>();
    public static final Set<Material> CLEAR_TOOL = new HashSet<>();
    static {
        makeMaterialList(DIG_TOOL, FLINT, WOODEN_SHOVEL, STONE_SHOVEL, IRON_SHOVEL,
                GOLDEN_SHOVEL, DIAMOND_SHOVEL, NETHERITE_SHOVEL);
        makeMaterialList(CHOP_TOOL, FLINT, WOODEN_AXE, STONE_AXE, IRON_AXE,
                GOLDEN_AXE, DIAMOND_AXE, NETHERITE_AXE);
        makeMaterialList(MINE_TOOL, WOODEN_PICKAXE, STONE_PICKAXE, IRON_PICKAXE,
                GOLDEN_PICKAXE, DIAMOND_PICKAXE, NETHERITE_PICKAXE);
        makeMaterialList(CUT_TOOL, FLINT, WOODEN_SWORD, STONE_SWORD, IRON_SWORD,
                GOLDEN_SWORD, DIAMOND_SWORD, NETHERITE_SWORD);
        makeMaterialList(CLEAR_TOOL, FLINT, WOODEN_HOE, STONE_HOE, IRON_HOE,
                GOLDEN_HOE, DIAMOND_HOE, NETHERITE_HOE);
    }
}
