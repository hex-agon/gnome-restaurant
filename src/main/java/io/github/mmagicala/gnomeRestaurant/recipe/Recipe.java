package io.github.mmagicala.gnomeRestaurant.recipe;

import net.runelite.api.gameval.ItemID;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public enum Recipe {

    WORM_HOLE("worm hole", createBakedRecipe(
            BakedRecipeType.GNOMEBOWL,
            List.of(
                    new Ingredient(ItemID.KING_WORM, 4),
                    new Ingredient(ItemID.ONION, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_WORM_HOLE,
            ItemID.ALUFT_BAKED_WORM_HOLE,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.WORM_HOLE)
    ),

    VEGETABLE_BALL("vegetable ball", createBakedRecipe(
            BakedRecipeType.GNOMEBOWL,
            List.of(
                    new Ingredient(ItemID.POTATO, 2),
                    new Ingredient(ItemID.ONION, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_VEG_BALL,
            ItemID.ALUFT_BAKED_VEG_BALL,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.VEG_BALL)
    ),

    TANGLED_TOADS_LEGS("tangled toads legs", createBakedRecipe(
            BakedRecipeType.GNOMEBOWL,
            List.of(
                    new Ingredient(ItemID.TOADS_LEGS, 4),
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    new Ingredient(ItemID.CHEESE, 2),
                    new Ingredient(ItemID.DWELLBERRIES, 1),
                    new Ingredient(ItemID.EQUA_LEAVES, 2)
            ),
            ItemID.ALUFT_HALF_BAKED_TANGLED_TOADS_LEGS,
            ItemID.TANGLED_TOADS_LEGS)
    ),

    CHOCOLATE_BOMB("chocolate bomb", createBakedRecipe(
            BakedRecipeType.GNOMEBOWL,
            List.of(
                    new Ingredient(ItemID.CHOCOLATE_BAR, 4),
                    new Ingredient(ItemID.EQUA_LEAVES, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_CHOC_BOMB,
            ItemID.ALUFT_BAKED_CHOC_BOMB,
            List.of(
                    new Ingredient(ItemID.CHOCOLATE_DUST, 1),
                    new Ingredient(ItemID.POT_OF_CREAM, 2)
            ),
            ItemID.CHOCOLATE_BOMB)
    ),

    FRUIT_BATTA("fruit batta", createBakedRecipe(
            BakedRecipeType.BATTA,
            List.of(
                    new Ingredient(ItemID.EQUA_LEAVES, 4),
                    new Ingredient(ItemID.LIME_CHUNKS, 1),
                    new Ingredient(ItemID.ORANGE_CHUNKS, 1),
                    new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1)
            ),
            ItemID.ALUFT_HALF_MADE_FRUIT_BATTA,
            ItemID.ALUFT_BAKED_FRUIT_BATTA,
            new Ingredient(ItemID.GNOME_SPICE, 1),
            ItemID.FRUIT_BATTA)
    ),

    TOAD_BATTA("toad batta", createBakedRecipe(
            BakedRecipeType.BATTA,
            List.of(
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    new Ingredient(ItemID.CHEESE, 1),
                    new Ingredient(ItemID.TOADS_LEGS, 1)
            ),
            ItemID.ALUFT_HALF_MADE_TOAD_BATTA,
            ItemID.TOAD_BATTA)
    ),

    WORM_BATTA("worm batta", createBakedRecipe(
            BakedRecipeType.BATTA,
            List.of(
                    new Ingredient(ItemID.KING_WORM, 1),
                    new Ingredient(ItemID.CHEESE, 1),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_MADE_WORM_BATTA,
            ItemID.ALUFT_BAKED_WORM_BATTA,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.WORM_BATTA)
    ),

    VEGETABLE_BATTA("vegetable batta", createBakedRecipe(
            BakedRecipeType.BATTA,
            List.of(
                    new Ingredient(ItemID.TOMATO, 2),
                    new Ingredient(ItemID.DWELLBERRIES, 1),
                    new Ingredient(ItemID.ONION, 1),
                    new Ingredient(ItemID.CHEESE, 1),
                    new Ingredient(ItemID.CABBAGE, 1)
            ),
            ItemID.ALUFT_HALF_MADE_VEG_BATTA,
            ItemID.ALUFT_BAKED_VEG_BATTA,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.VEGETABLE_BATTA)
    ),

    CHEESE_AND_TOMATO_BATTA("cheese and tomato batta", createBakedRecipe(
            BakedRecipeType.BATTA,
            List.of(
                    new Ingredient(ItemID.CHEESE, 1),
                    new Ingredient(ItemID.TOMATO, 1)
            ),
            ItemID.ALUFT_HALF_MADE_CHEESE_TOM_BATTA,
            ItemID.ALUFT_BAKED_CHEESE_TOM_BATTA,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.CHEESE_TOM_BATTA)
    ),

    CHOC_CHIP_CRUNCHIES("choc chip crunchies", createBakedRecipe(
            BakedRecipeType.CRUNCHIES,
            List.of(
                    new Ingredient(ItemID.CHOCOLATE_BAR, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_CHOC_CHIP_CRUNCHY,
            ItemID.ALUFT_BAKED_CHOC_CHIP_CRUNCHY,
            new Ingredient(ItemID.CHOCOLATE_DUST, 1),
            ItemID.CHOCCHIP_CRUNCHIES)
    ),

    SPICY_CRUNCHIES("spicy crunchies", createBakedRecipe(
            BakedRecipeType.CRUNCHIES,
            List.of(
                    new Ingredient(ItemID.EQUA_LEAVES, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_SPICY_CRUNCHY,
            ItemID.ALUFT_BAKED_SPICY_CRUNCHY,
            new Ingredient(ItemID.GNOME_SPICE, 1),
            ItemID.SPICY_CRUNCHIES)
    ),

    TOAD_CRUNCHIES("toad crunchies", createBakedRecipe(
            BakedRecipeType.CRUNCHIES,
            List.of(
                    new Ingredient(ItemID.TOADS_LEGS, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_TOAD_CRUNCHY,
            ItemID.ALUFT_BAKED_TOAD_CRUNCHY,
            new Ingredient(ItemID.EQUA_LEAVES, 1),
            ItemID.TOAD_CRUNCHIES)
    ),

    WORM_CRUNCHIES("worm crunchies", createBakedRecipe(
            BakedRecipeType.CRUNCHIES,
            List.of(
                    new Ingredient(ItemID.KING_WORM, 2),
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    new Ingredient(ItemID.EQUA_LEAVES, 1)
            ),
            ItemID.ALUFT_HALF_BAKED_WORM_CRUNCHY,
            ItemID.ALUFT_BAKED_WORM_CRUNCHY,
            new Ingredient(ItemID.GNOME_SPICE, 1),
            ItemID.WORM_CRUNCHIES)
    ),

    FRUIT_BLAST("fruit blast", createCocktailRecipe(
            List.of(
                    new Ingredient(ItemID.PINEAPPLE, 1),
                    new Ingredient(ItemID.LEMON, 1),
                    new Ingredient(ItemID.ORANGE, 1)
            ),
            ItemID.ALUFT_SHAKER_FRUIT_BLAST,
            new Ingredient(ItemID.LEMON_SLICES, 1),
            ItemID.FRUIT_BLAST)
    ),

    PINEAPPLE_PUNCH("pineapple punch", createCocktailRecipe(
            List.of(
                    new Ingredient(ItemID.PINEAPPLE, 2),
                    new Ingredient(ItemID.LEMON, 1),
                    new Ingredient(ItemID.ORANGE, 1)
            ),
            ItemID.ALUFT_SHAKER_PINEAPPLE_PUNCH,
            List.of(
                    new Ingredient(ItemID.LIME_CHUNKS, 1),
                    new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1),
                    new Ingredient(ItemID.ORANGE_SLICES, 1)
            ),
            ItemID.PINEAPPLE_PUNCH)
    ),

    WIZARD_BLIZZARD("wizard blizzard", createCocktailRecipe(
            List.of(
                    new Ingredient(ItemID.VODKA, 2),
                    new Ingredient(ItemID.GIN, 1),
                    new Ingredient(ItemID.LIME, 1),
                    new Ingredient(ItemID.LEMON, 1),
                    new Ingredient(ItemID.ORANGE, 1)
            ),
            ItemID.ALUFT_SHAKER_WIZZARD_BLIZZARD,
            List.of(
                    new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1),
                    new Ingredient(ItemID.LIME_SLICES, 1)
            ),
            ItemID.WIZARD_BLIZZARD)
    ),

    SHORT_GREEN_GUY("short green guy", createCocktailRecipe(
            List.of(
                    new Ingredient(ItemID.VODKA, 1),
                    new Ingredient(ItemID.LIME, 3)
            ),
            ItemID.ALUFT_SHAKER_SGG,
            List.of(
                    new Ingredient(ItemID.LIME_SLICES, 1),
                    new Ingredient(ItemID.EQUA_LEAVES, 1)
            ),
            ItemID.ALUFT_SGG)
    ),

    DRUNK_DRAGON("drunk dragon", createDrunkDragonRecipe()),
    CHOCOLATE_SATURDAY("chocolate saturday", createChocSaturdayRecipe()),

    BLURBERRY_SPECIAL("Blurberry special", createCocktailRecipe(
            List.of(
                    new Ingredient(ItemID.VODKA, 1),
                    new Ingredient(ItemID.BRANDY, 1),
                    new Ingredient(ItemID.GIN, 1),
                    new Ingredient(ItemID.LEMON, 2),
                    new Ingredient(ItemID.ORANGE, 1)
            ),
            ItemID.ALUFT_SHAKER_BLURBERRY_SPECIAL,
            List.of(
                    new Ingredient(ItemID.LEMON_CHUNKS, 1),
                    new Ingredient(ItemID.ORANGE_CHUNKS, 1),
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    new Ingredient(ItemID.LIME_SLICES, 1)
            ),
            ItemID.BLURBERRY_SPECIAL)
    );

    private static final int NO_PRODUCED_ITEM_ID = -1;

    private final String name;
    private final List<RecipeStep> steps;

    Recipe(String name, List<RecipeStep> steps) {
        this.name = name;
        this.steps = steps;
    }

    /**
     * Return the item id of the final product
     **/
    public int getItemId() {
        int numSteps = steps.size();
        RecipeStep deliveryStep = steps.get(numSteps - 1);
        return deliveryStep.getIngredients().get(0).getItemId();
    }

    /**
     * Return raw ingredients used in a recipe at step number @stepIdx + 1
     **/
    public List<Ingredient> getNextRawIngredients(int stepIdx) {
        List<Ingredient> ingredients = new ArrayList<>();

        // Collect raw ingredients from each step
        int numSteps = steps.size();
        for (int i = stepIdx + 1; i < numSteps; i++) {
            List<Ingredient> rawIngredients = steps.get(i).getRawIngredients();
            ingredients.addAll(rawIngredients);
        }

        return ingredients;
    }

    public String getName() {
        return this.name;
    }

    public List<RecipeStep> getSteps() {
        return this.steps;
    }

    /**
     * Get recipe by name
     **/
    public static Recipe getRecipe(String name) {
        for (Recipe recipe : values()) {
            if (recipe.name.equals(name)) {
                return recipe;
            }
        }

        throw new InvalidParameterException("Recipe with name " + name + " not found");
    }
    // Cocktail recipe builders

    // Internal enum to create baked recipes

    private enum BakedRecipeType {
        CRUNCHIES(ItemID.RAW_CRUNCHIES, ItemID.CRUNCHY_TRAY, ItemID.HALF_BAKED_CRUNCHY),
        BATTA(ItemID.RAW_BATTA, ItemID.BATTA_TIN, ItemID.HALF_BAKED_BATTA),
        GNOMEBOWL(ItemID.RAW_GNOMEBOWL, ItemID.GNOMEBOWL_MOULD, ItemID.HALF_BAKED_BOWL);

        private final int rawId, toolId, halfBakedId;

        BakedRecipeType(int rawId, int toolId, int halfBakedId) {
            this.rawId = rawId;
            this.halfBakedId = halfBakedId;
            this.toolId = toolId;
        }

        public int getRawId() {
            return this.rawId;
        }

        public int getToolId() {
            return this.toolId;
        }

        public int getHalfBakedId() {
            return this.halfBakedId;
        }
    }

    /**
     * Partially create baked recipe. The steps are: raw item -> half baked item -> half made item
     *
     * @param ingredients Combined with half-baked item to create half-made item
     * @return Unfinished recipe
     */
    private static List<RecipeStep> createBakedRecipeHelper(BakedRecipeType bakedRecipeType, List<Ingredient> ingredients, int halfMadeId) {
        List<RecipeStep> unfinishedRecipe = new ArrayList<>();

        // Create raw item

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.CREATE_RAW,
                List.of(
                        new Ingredient(ItemID.GIANNE_DOUGH, 1),
                        new Ingredient(bakedRecipeType.getToolId(), 1)
                ),
                bakedRecipeType.getRawId())
        );

        // Raw item -> half baked item

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.CREATE_HALF_BAKED,
                new Ingredient(bakedRecipeType.getRawId(), 1, true),
                bakedRecipeType.getHalfBakedId())
        );

        // Half baked item -> half made item

        List<Ingredient> halfBakedIngredients = new ArrayList<>(ingredients);
        halfBakedIngredients.add(new Ingredient(bakedRecipeType.getHalfBakedId(), 1, true));

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.COMBINE_INGREDIENTS,
                halfBakedIngredients,
                halfMadeId));

        return unfinishedRecipe;
    }

    /**
     * Create baked recipe with no topped ingredients
     *
     * @param ingredients Combined with half-baked item to create half-made item
     * @param finishedId  Final product of recipe
     */
    private static List<RecipeStep> createBakedRecipe(BakedRecipeType bakedRecipeType, List<Ingredient> ingredients, int halfMadeId, int finishedId) {
        // Create raw item -> half baked item -> half made item

        List<RecipeStep> recipe = createBakedRecipeHelper(bakedRecipeType, ingredients, halfMadeId);

        // Half made item -> finished item

        recipe.add(new RecipeStep(RecipeInstruction.BAKE_HALF_MADE,
                new Ingredient(halfMadeId, 1, true),
                finishedId));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(finishedId, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    /**
     * Create baked recipe with multiple topped ingredients
     *
     * @param firstIngredients   Combined with half-baked item to create half-made item
     * @param toppingIngredients Combined with unfinished item to create finished item
     * @param finishedId         Final product of recipe
     */
    private static List<RecipeStep> createBakedRecipe(
            BakedRecipeType bakedRecipeType,
            List<Ingredient> firstIngredients,
            int halfMadeId,
            int unfinishedId,
            List<Ingredient> toppingIngredients,
            int finishedId
    ) {
        // Create raw item -> half baked item -> half made item

        List<RecipeStep> recipe = createBakedRecipeHelper(bakedRecipeType, firstIngredients, halfMadeId);

        // Half made item -> unfinished item

        recipe.add(new RecipeStep(RecipeInstruction.BAKE_HALF_MADE,
                new Ingredient(halfMadeId, 1, true),
                unfinishedId));

        // unfinished item -> finished item

        List<Ingredient> unfinishedIngredients = new ArrayList<>();
        unfinishedIngredients.add(new Ingredient(unfinishedId, 1, true));
        unfinishedIngredients.addAll(toppingIngredients);

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                unfinishedIngredients,
                finishedId));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(finishedId, 1, true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    /**
     * Create baked recipe with one topped ingredient
     *
     * @param firstIngredients  Combined with half-baked item to create half-made item
     * @param toppingIngredient Combined with unfinished item to create finished item
     * @param finishedId        Final product of recipe
     */
    private static List<RecipeStep> createBakedRecipe(
            BakedRecipeType bakedRecipeType,
            List<Ingredient> firstIngredients,
            int halfMadeId,
            int unfinishedId,
            Ingredient toppingIngredient,
            int finishedId
    ) {
        List<Ingredient> toppingIngredients = new ArrayList<>();
        toppingIngredients.add(toppingIngredient);

        return createBakedRecipe(bakedRecipeType, firstIngredients, halfMadeId, unfinishedId, toppingIngredients, finishedId);
    }

    // Cocktail recipe builders

    /**
     * Partially create cocktail recipe. Only adds the step to create mixed cocktail.
     **/
    private static List<RecipeStep> createCocktailRecipeHelper(List<Ingredient> shakedIngredients, int mixedItem) {
        List<RecipeStep> unfinishedRecipe = new ArrayList<>();

        List<Ingredient> mixIngredients = new ArrayList<>(shakedIngredients);
        mixIngredients.add(new Ingredient(ItemID.COCKTAIL_SHAKER, 1, true));

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.MIX_COCKTAIL,
                mixIngredients,
                mixedItem)
        );

        return unfinishedRecipe;
    }

    /**
     * Partially creates cocktail recipe. The steps are: mix cocktail -> pour
     *
     * @param isCompleteRecipe Flag that tells whether this is a complete or partial recipe
     */
    private static List<RecipeStep> createCocktailRecipeHelper(
            List<Ingredient> shakedIngredients,
            int mixedItem,
            List<Ingredient> pouredIngredients,
            int pouredItem,
            boolean isCompleteRecipe
    ) {
        // Create mixed cocktail

        List<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, mixedItem);

        // Mixed cocktail -> poured cocktail

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(ItemID.COCKTAIL_GLASS_EMPTY, 1, true));
        ingredients.add(new Ingredient(mixedItem, 1, true));
        ingredients.addAll(pouredIngredients);

        recipe.add(new RecipeStep(RecipeInstruction.POUR,
                ingredients,
                pouredItem)
        );

        // Deliver poured item if we finished making the recipe item

        if (isCompleteRecipe) {
            recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(pouredItem, 1,
                    true), NO_PRODUCED_ITEM_ID));
        }

        return recipe;
    }

    /**
     * Simple cocktail recipe with several poured ingredients
     **/
    private static List<RecipeStep> createCocktailRecipe(
            List<Ingredient> shakedIngredients,
            int mixedItem,
            List<Ingredient> pouredIngredients,
            int finishedItem
    ) {
        return createCocktailRecipeHelper(shakedIngredients, mixedItem, pouredIngredients, finishedItem, true);
    }

    /**
     * Simple cocktail recipe with one poured ingredient
     **/
    private static List<RecipeStep> createCocktailRecipe(
            List<Ingredient> shakedIngredients,
            int mixedItem,
            Ingredient pouredIngredient,
            int finishedItem
    ) {
        List<Ingredient> pouredIngredients = new ArrayList<>();
        pouredIngredients.add(pouredIngredient);

        return createCocktailRecipe(shakedIngredients, mixedItem, pouredIngredients, finishedItem);
    }

    // Heated cocktail recipes

    private static List<RecipeStep> createDrunkDragonRecipe() {
        // Create mixed cocktail -> poured cocktail

        List<Ingredient> shakedIngredients = List.of(
                new Ingredient(ItemID.VODKA, 1),
                new Ingredient(ItemID.GIN, 1),
                new Ingredient(ItemID.DWELLBERRIES, 1)
        );

        List<Ingredient> toppedIngredients = List.of(
                new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1),
                new Ingredient(ItemID.POT_OF_CREAM, 1)
        );

        List<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, ItemID.ALUFT_SHAKER_DRUNK_DRAGON,
                List.of(), ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREFINISH, false);

        // Poured cocktail -> unfinished item

        List<Ingredient> unfinishedIngredients = new ArrayList<>();
        unfinishedIngredients.add(new Ingredient(ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREFINISH, 1, true));
        unfinishedIngredients.addAll(toppedIngredients);

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                unfinishedIngredients,
                ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREHEAT));

        // Unfinished item -> finished item

        recipe.add(new RecipeStep(RecipeInstruction.HEAT_COCKTAIL,
                new Ingredient(ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREHEAT, 1, true), ItemID.DRUNK_DRAGON));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(ItemID.DRUNK_DRAGON, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    private static List<RecipeStep> createChocSaturdayRecipe() {
        // Create mixed cocktail -> poured cocktail

        List<Ingredient> shakedIngredients = List.of(
                new Ingredient(ItemID.WHISKY, 1),
                new Ingredient(ItemID.CHOCOLATE_BAR, 1),
                new Ingredient(ItemID.EQUA_LEAVES, 1),
                new Ingredient(ItemID.BUCKET_MILK, 1)
        );

        List<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, ItemID.ALUFT_SHAKER_CHOC_SATURDAY, List.of(), ItemID.ALUFT_SHAKER_CHOC_SATURDAY_PREHEAT, false);

        // poured cocktail -> unfinished item

        recipe.add(new RecipeStep(RecipeInstruction.HEAT_COCKTAIL,
                new Ingredient(ItemID.ALUFT_SHAKER_CHOC_SATURDAY_PREHEAT, 1, true), ItemID.ALUFT_SHAKER_CHOC_SATURDAY_HEATED));

        // unfinished item -> finished item

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(ItemID.ALUFT_SHAKER_CHOC_SATURDAY_HEATED, 1, true));
        ingredients.add(new Ingredient(ItemID.CHOCOLATE_DUST, 1));
        ingredients.add(new Ingredient(ItemID.POT_OF_CREAM, 1));

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                ingredients,
                ItemID.CHOCOLATE_SATURDAY));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(ItemID.ALUFT_CHOC_SATURDAY, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }
}
