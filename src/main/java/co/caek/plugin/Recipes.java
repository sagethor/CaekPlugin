package co.caek.plugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import static co.caek.plugin.CaekPlugin.plugin;
import static co.caek.plugin.CaekPlugin.server;
import static org.bukkit.Material.*;

public class Recipes implements Listener {
    // Quick-make a new recipe key.
    static public NamespacedKey newKey(String key) {
        NamespacedKey newKey = new NamespacedKey(plugin, key);
        CaekPlugin.recipes.add(newKey); return newKey;
    }

    // Step 1: Create a new recipe with target material. (newShaped / newShapeless)
    // Step 2: Add ingredients to recipe. (setIngredient / addIngredient)
    // Step 3: Set shape and add recipe. (setShape & addRecipe)
    
    // Example on how to use Material when ItemStack is required:
    // newShaped("dirt", new ItemStack(Material.DIRT, <amount - default 1>));
    // For shaped recipes avoid numbered ItemStacks as ingredients. Both shaped and shapeless recipes should limit items to 9, going along with potential limitations.

    // Add shaped recipe with (1) ItemStack
    static public void addShaped(String key, ItemStack result, ItemStack A, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, mid, bot).setIngredient('A', A));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, bot).setIngredient('A', A));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(row).setIngredient('A', A));
    }
    // Add shaped recipe with (1) Material
    static public void addShaped(String key, Material result, int amt, Material A, String top, String mid, String bot) {
        addShaped(key, IS(result, amt), IS(A), top, mid, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, String top, String bot) {
        addShaped(key, IS(result, amt), IS(A), top, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, String row) {
        addShaped(key, IS(result, amt), IS(A), row);
    }

    // Add shaped recipe with (2) ItemStacks
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, mid, bot).setIngredient('A', A).setIngredient('B', B));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, bot).setIngredient('A', A).setIngredient('B', B));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(row).setIngredient('A', A).setIngredient('B', B));
    }
    // Add shaped recipe with (2) Materials
    static public void addShaped(String key, Material result, int amt, Material A, Material B, String top, String mid, String bot) {
        addShaped(key, IS(result, amt), IS(A), IS(B), top, mid, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, Material B, String top, String bot) {
        addShaped(key, IS(result, amt), IS(A), IS(B), top, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, Material B, String row) {
        addShaped(key, IS(result, amt), IS(A), IS(B), row);
    }

    // Add shaped recipe with (3) ItemStacks
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, mid, bot).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(top, bot).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).shape(row).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C));
    }
    // Add shaped recipe with (3) Materials
    static public void addShaped(String key, Material result, int amt, Material A, Material B, Material C, String top, String mid, String bot) {
        addShaped(key, IS(result, amt), IS(A), IS(B), IS(C), top, mid, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, Material B, Material C, String top, String bot) {
        addShaped(key, IS(result, amt), IS(A), IS(B), IS(C), top, bot);
    }
    static public void addShaped(String key, Material result, int amt, Material A, Material B, Material C, String row) {
        addShaped(key, IS(result, amt), IS(A), IS(B), IS(C), row);
    }

    // Add shapeless recipe with (1) ItemStack type
    static public void addShapeless(String key, ItemStack result, ItemStack A) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A));
    }
    // Add shapeless recipe with (1) Material type
    static public void addShapeless(String key, Material result, int amt, Material A, int amtA) {
        addShapeless(key, IS(result, amt), IS(A, amtA));
    }
    // Add shapeless recipe with (2) ItemStack types
    static public void addShapeless(String key, ItemStack result, ItemStack A, ItemStack B) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A).addIngredient(B));
    }
    // Add shapeless recipe with (2) Material types
    static public void addShapeless(String key, Material result, int amt, Material A, int amtA, Material B, int amtB) {
        addShapeless(key, IS(result, amt), IS(A, amtA), IS(B, amtB));
    }
    // Add shapeless recipe with (3) ItemStack types
    static public void addShapeless(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A).addIngredient(B).addIngredient(C));
    }
    // Add shapeless recipe with (3) Material types
    static public void addShapeless(String key, Material result, int amt, Material A, int amtA, Material B, int amtB, Material C, int amtC) {
        addShapeless(key, IS(result, amt), IS(A, amtA), IS(B, amtB), IS(C, amtC));
    }

    // Add more where necessary...

    // ADD IN VERSIONS WITH CUSTOM TIME
    // Placeholder - duration 10s, efficiency 10s; maybe we do item by item smelt times.
    // To implement - cook something on top while the furnace runs... (5 seconds)?
    static int furnaceTime = 200;
    static public void addFurnace(String key, ItemStack result, RecipeChoice input) {
        server.addRecipe(new FurnaceRecipe(Recipes.newKey(key), result, input, 0.0F, furnaceTime));
    }
    static public void addFurnace(String key, ItemStack result, Material input) {
        server.addRecipe(new FurnaceRecipe(Recipes.newKey(key), result, input, 0.0F, furnaceTime));
    }
    static public void addFurnace(String key, Material result, int amt, RecipeChoice input) {
        addFurnace(key, IS(result, amt), input);
    }
    static public void addFurnace(String key, Material result, int amt, Material input) {
        addFurnace(key, IS(result, amt), input);
    }
    static public void addFurnace(String key, Material result, RecipeChoice input) {
        addFurnace(key, IS(result), input);
    }
    static public void addFurnace(String key, Material result, Material input) {
        addFurnace(key, IS(result), input);
    }

    // ADD IN VERSIONS WITH CUSTOM TIME
    // Placeholder - duration 5s, efficiency 10s; maybe we do item by item smelt times.
    // To implement - cook something on top while the blast furnace runs... (5 seconds)?
    static int blastingTime = 100;
    static public void addBlasting(String key, ItemStack result, Material input) {
        server.addRecipe(new BlastingRecipe(Recipes.newKey(key), result, input, 0.0F, blastingTime));
    }
    static public void addBlasting(String key, ItemStack result, RecipeChoice input) {
        server.addRecipe(new BlastingRecipe(Recipes.newKey(key), result, input, 0.0F, blastingTime));
    }
    static public void addBlasting(String key, Material result, int amt, Material input) {
        addBlasting(key, IS(result, amt), input);
    }
    static public void addBlasting(String key, Material result, int amt, RecipeChoice input) {
        addBlasting(key, IS(result, amt), input);
    }
    static public void addBlasting(String key, Material result, Material input) {
        addBlasting(key, IS(result), input);
    }
    static public void addBlasting(String key, Material result, RecipeChoice input) {
        addBlasting(key, IS(result), input);
    }

    // ADD IN VERSIONS WITH CUSTOM TIME
    // Placeholder - duration 20s; maybe we do item by item smelt times.
    static int campfireTime = 400;
    static public void addCampfire(String key, ItemStack result, Material input) {
        server.addRecipe(new CampfireRecipe(Recipes.newKey(key), result, input, 0.0F, campfireTime));
    }
    static public void addCampfire(String key, ItemStack result, RecipeChoice input) {
        server.addRecipe(new CampfireRecipe(Recipes.newKey(key), result, input, 0.0F, campfireTime));
    }
    static public void addCampfire(String key, Material result, int amt, Material input) {
        addCampfire(key, IS(result, amt), input);
    }
    static public void addCampfire(String key, Material result, int amt, RecipeChoice input) {
        addCampfire(key, IS(result, amt), input);
    }
    static public void addCampfire(String key, Material result, Material input) {
        addCampfire(key, IS(result), input);
    }
    static public void addCampfire(String key, Material result, RecipeChoice input) {
        addCampfire(key, IS(result), input);
    }

    // ADD IN VERSIONS WITH CUSTOM TIME
    // IDEA: Food made by the smoker are tagged to stack more...
    // Placeholder - duration 5s, efficiency 20s; maybe we do item by item smelt times.
    static int smokingTime = 100;
    static public void addSmoking(String key, ItemStack result, Material input) {
        server.addRecipe(new SmokingRecipe(Recipes.newKey(key), result, input, 0.0F, smokingTime));
    }
    static public void addSmoking(String key, ItemStack result, RecipeChoice input) {
        server.addRecipe(new SmokingRecipe(Recipes.newKey(key), result, input, 0.0F, smokingTime));
    }
    static public void addSmoking(String key, Material result, int amt, Material input) {
        addSmoking(key, IS(result, amt), input);
    }
    static public void addSmoking(String key, Material result, int amt, RecipeChoice input) {
        addSmoking(key, IS(result, amt), input);
    }
    static public void addSmoking(String key, Material result, Material input) {
        addSmoking(key, IS(result), input);
    }
    static public void addSmoking(String key, Material result, RecipeChoice input) {
        addSmoking(key, IS(result), input);
    }

    // Quick function to make typing stuff out less annoying.
    static public ItemStack IS(Material material) { return new ItemStack(material); }
    static public ItemStack IS(Material material, int amt) {
        return new ItemStack(material, amt);
    }
    // Okay, I should probably rewrite again...
    static public RecipeChoice.MaterialChoice RCMC(Material ... material) { return new RecipeChoice.MaterialChoice(material); }

    static public void campfireRecipes() { // GOAL - 64 RECIPES
        // other misc recipes
        addCampfire("campfire_torch", TORCH, STICK);
        addCampfire("campfire_bonemeal", BONE_MEAL, BONE);
        addCampfire("campfire_brick", BRICK, 1, CLAY_BALL);

        // several dyes are missing - mixing recipes
        addCampfire("campfire_white_dye", WHITE_DYE, LILY_OF_THE_VALLEY);
        addCampfire("campfire_black_dye", BLACK_DYE, INK_SAC); // wither rose is an alternative
        addCampfire("campfire_red_dye", RED_DYE, RED_TULIP); // poppy / rose bush / beetroot are alternatives
        addCampfire("campfire_orange_dye", ORANGE_DYE, ORANGE_TULIP);
        addCampfire("campfire_yellow_dye", YELLOW_DYE, DANDELION); // sunflower is an alternative
        addCampfire("campfire_light_blue_dye", LIGHT_BLUE_DYE, BLUE_ORCHID);
        addCampfire("campfire_blue_dye", BLUE_DYE, CORNFLOWER);
        addCampfire("campfire_magenta_dye", MAGENTA_DYE, RCMC(ALLIUM, LILAC));
        addCampfire("campfire_brown_dye", BROWN_DYE, COCOA_BEANS);
        addCampfire("campfire_green_dye", GREEN_DYE, CACTUS);
        addCampfire("campfire_lime_dye", LIME_DYE, SEA_PICKLE); // add sea pickles to Blocks, use wiki as reference

        addCampfire("campfire_porkchop", COOKED_PORKCHOP, PORKCHOP);
        addCampfire("campfire_mutton", COOKED_MUTTON, MUTTON);
        addCampfire("campfire_beef", COOKED_BEEF, BEEF);
        addCampfire("campfire_chicken", COOKED_CHICKEN, CHICKEN);
        addCampfire("campfire_cod", COOKED_COD, COD);
        addCampfire("campfire_rabbit", COOKED_RABBIT, RABBIT);
        addCampfire("campfire_salmon", COOKED_SALMON, SALMON);
        addCampfire("campfire_potato", BAKED_POTATO, POTATO);
    }
    
    static public void shapedRecipes() {
        addShaped("coal_torch", TORCH, 4, COAL, STICK, "A", "B");
        addShaped("charcoal_torch", TORCH, 4, CHARCOAL, STICK, "A", "B");
    }

    static public void addRecipes() {
        campfireRecipes();
        shapedRecipes();
    }
}
