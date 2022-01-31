package co.caek.plugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
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

    // Add shaped recipe with (1) ingredient type
    static public void addShaped(String key, ItemStack result, ItemStack A, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).shape(top, mid, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).shape(top, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).shape(row));
    }

    // Add shaped recipe with (2) ingredient types
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B)
                .shape(top, mid, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B)
                .shape(top, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B).shape(row));
    }

    // Add shaped recipe with (3) ingredient types
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String top, String mid, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C).shape(top, mid, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String top, String bot) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C).shape(top, bot));
    }
    static public void addShaped(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C, String row) {
        server.addRecipe(new ShapedRecipe(Recipes.newKey(key), result).setIngredient('A', A).setIngredient('B', B)
                .setIngredient('C', C).shape(row));
    }

    // Add more where necessary...

    // Add shapeless recipe with (1) ingredient type
    static public void addShapeless(String key, ItemStack result, ItemStack A) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A));
    }
    // Add shapeless recipe with (2) ingredient types
    static public void addShapeless(String key, ItemStack result, ItemStack A, ItemStack B) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A).addIngredient(B));
    }
    // Add shapeless recipe with (3) ingredient types
    static public void addShapeless(String key, ItemStack result, ItemStack A, ItemStack B, ItemStack C) {
        server.addRecipe(new ShapelessRecipe(Recipes.newKey(key), result).addIngredient(A).addIngredient(B).addIngredient(C));
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

    // ADD IN VERSIONS WITH CUSTOM TIME
    // Placeholder - duration 20s; maybe we do item by item smelt times.
    static int campfireTime = 400;
    static public void addCampfire(String key, ItemStack result, Material input) {
        server.addRecipe(new CampfireRecipe(Recipes.newKey(key), result, input, 0.0F, campfireTime));
    }
    static public void addCampfire(String key, ItemStack result, RecipeChoice input) {
        server.addRecipe(new CampfireRecipe(Recipes.newKey(key), result, input, 0.0F, campfireTime));
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

    // Quick function to make typing stuff out less annoying.
    static public ItemStack IS(Material material) {
        return new ItemStack(material);
    }
    static public ItemStack IS(Material material, int amt) {
        return new ItemStack(material, amt);
    }

    static public void campfireRecipes() { // GOAL - 64 RECIPES
        // other misc recipes
        addCampfire("campfire_torch", IS(TORCH), STICK);
        addCampfire("campfire_bonemeal", IS(BONE_MEAL), BONE);
        addCampfire("campfire_brick", IS(BRICK), CLAY_BALL);

        // several dyes are missing - mixing recipes
        addCampfire("campfire_white_dye", IS(WHITE_DYE), LILY_OF_THE_VALLEY);
        addCampfire("campfire_black_dye", IS(BLACK_DYE), INK_SAC); // wither rose is an alternative
        addCampfire("campfire_red_dye", IS(RED_DYE), RED_TULIP); // poppy / rose bush / beetroot are alternatives
        addCampfire("campfire_orange_dye", IS(ORANGE_DYE), ORANGE_TULIP);
        addCampfire("campfire_yellow_dye", IS(YELLOW_DYE), DANDELION); // sunflower is an alternative
        addCampfire("campfire_light_blue_dye", IS(LIGHT_BLUE_DYE), BLUE_ORCHID);
        addCampfire("campfire_blue_dye", IS(BLUE_DYE), CORNFLOWER);
        addCampfire("campfire_magenta_dye", IS(MAGENTA_DYE), ALLIUM); // lilac is an alternative
        addCampfire("campfire_brown_dye", IS(BROWN_DYE), COCOA_BEANS);
        addCampfire("campfire_green_dye", IS(GREEN_DYE), CACTUS);
        addCampfire("campfire_lime_dye", IS(LIME_DYE), SEA_PICKLE); // add sea pickles to Blocks, use wiki as reference

        addCampfire("campfire_porkchop", IS(COOKED_PORKCHOP), PORKCHOP);
        addCampfire("campfire_mutton", IS(COOKED_MUTTON), MUTTON);
        addCampfire("campfire_beef", IS(COOKED_BEEF), BEEF);
        addCampfire("campfire_chicken", IS(COOKED_CHICKEN), CHICKEN);
        addCampfire("campfire_cod", IS(COOKED_COD), COD);
        addCampfire("campfire_rabbit", IS(COOKED_RABBIT), RABBIT);
        addCampfire("campfire_salmon", IS(COOKED_SALMON), SALMON);
        addCampfire("campfire_potato", IS(BAKED_POTATO), POTATO);
    }
    
    static public void shapedRecipes() {
    }

    static public void addRecipes() {
        campfireRecipes();
        shapedRecipes();
    }
}
