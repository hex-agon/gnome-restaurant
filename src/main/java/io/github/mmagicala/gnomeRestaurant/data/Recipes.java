package io.github.mmagicala.gnomeRestaurant.data;

import io.github.mmagicala.gnomeRestaurant.recipe.Ingredient;
import io.github.mmagicala.gnomeRestaurant.recipe.RecipeInstruction;
import io.github.mmagicala.gnomeRestaurant.recipe.Recipe;
import io.github.mmagicala.gnomeRestaurant.recipe.RecipeStep;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import net.runelite.api.gameval.ItemID;

public class Recipes {

    private static final int NO_PRODUCED_ITEM_ID = -1;

    public static final ArrayList<Recipe> list = new ArrayList<Recipe>() {
        {
            // Gnomebowls

            add(new Recipe("worm hole", createBakedRecipe(
                    BakedRecipeType.GNOMEBOWL,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.KING_WORM, 4));
                            add(new Ingredient(ItemID.ONION, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_WORM_HOLE,
                    ItemID.ALUFT_BAKED_WORM_HOLE,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.WORM_HOLE)
            ));

            add(new Recipe("vegetable ball", createBakedRecipe(
                    BakedRecipeType.GNOMEBOWL,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.POTATO, 2));
                            add(new Ingredient(ItemID.ONION, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_VEG_BALL,
                    ItemID.ALUFT_BAKED_VEG_BALL,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.VEG_BALL)
            ));

            add(new Recipe("tangled toads legs", createBakedRecipe(
                    BakedRecipeType.GNOMEBOWL,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.TOADS_LEGS, 4));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                            add(new Ingredient(ItemID.CHEESE, 2));
                            add(new Ingredient(ItemID.DWELLBERRIES, 1));
                            add(new Ingredient(ItemID.EQUA_LEAVES, 2));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_TANGLED_TOADS_LEGS,
                    ItemID.TANGLED_TOADS_LEGS)
            ));

            add(new Recipe("chocolate bomb", createBakedRecipe(
                    BakedRecipeType.GNOMEBOWL,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.CHOCOLATE_BAR, 4));
                            add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_CHOC_BOMB,
                    ItemID.ALUFT_BAKED_CHOC_BOMB,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.CHOCOLATE_DUST, 1));
                            add(new Ingredient(ItemID.POT_OF_CREAM, 2));
                        }
                    },
                    ItemID.CHOCOLATE_BOMB)
            ));

            // Battas

            add(new Recipe("fruit batta", createBakedRecipe(
                    BakedRecipeType.BATTA,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.EQUA_LEAVES, 4));
                            add(new Ingredient(ItemID.LIME_CHUNKS, 1));
                            add(new Ingredient(ItemID.ORANGE_CHUNKS, 1));
                            add(new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_MADE_FRUIT_BATTA,
                    ItemID.ALUFT_BAKED_FRUIT_BATTA,
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    ItemID.FRUIT_BATTA)
            ));

            add(new Recipe("toad batta", createBakedRecipe(
                    BakedRecipeType.BATTA,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                            add(new Ingredient(ItemID.CHEESE, 1));
                            add(new Ingredient(ItemID.TOADS_LEGS, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_MADE_TOAD_BATTA,
                    ItemID.TOAD_BATTA)
            ));

            add(new Recipe("worm batta", createBakedRecipe(
                    BakedRecipeType.BATTA,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.KING_WORM, 1));
                            add(new Ingredient(ItemID.CHEESE, 1));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_MADE_WORM_BATTA,
                    ItemID.ALUFT_BAKED_WORM_BATTA,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.WORM_BATTA)
            ));

            add(new Recipe("vegetable batta", createBakedRecipe(
                    BakedRecipeType.BATTA,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.TOMATO, 2));
                            add(new Ingredient(ItemID.DWELLBERRIES, 1));
                            add(new Ingredient(ItemID.ONION, 1));
                            add(new Ingredient(ItemID.CHEESE, 1));
                            add(new Ingredient(ItemID.CABBAGE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_MADE_VEG_BATTA,
                    ItemID.ALUFT_BAKED_VEG_BATTA,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.VEGETABLE_BATTA)
            ));

            add(new Recipe("cheese and tomato batta", createBakedRecipe(
                    BakedRecipeType.BATTA,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.CHEESE, 1));
                            add(new Ingredient(ItemID.TOMATO, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_MADE_CHEESE_TOM_BATTA,
                    ItemID.ALUFT_BAKED_CHEESE_TOM_BATTA,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.CHEESE_TOM_BATTA)
            ));

            // Crunchies

            add(new Recipe("choc chip crunchies", createBakedRecipe(
                    BakedRecipeType.CRUNCHIES,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.CHOCOLATE_BAR, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_CHOC_CHIP_CRUNCHY,
                    ItemID.ALUFT_BAKED_CHOC_CHIP_CRUNCHY,
                    new Ingredient(ItemID.CHOCOLATE_DUST, 1),
                    ItemID.CHOCCHIP_CRUNCHIES)
            ));

            add(new Recipe("spicy crunchies", createBakedRecipe(
                    BakedRecipeType.CRUNCHIES,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.EQUA_LEAVES, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_SPICY_CRUNCHY,
                    ItemID.ALUFT_BAKED_SPICY_CRUNCHY,
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    ItemID.SPICY_CRUNCHIES)
            ));

            add(new Recipe("toad crunchies", createBakedRecipe(
                    BakedRecipeType.CRUNCHIES,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.TOADS_LEGS, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_TOAD_CRUNCHY,
                    ItemID.ALUFT_BAKED_TOAD_CRUNCHY,
                    new Ingredient(ItemID.EQUA_LEAVES, 1),
                    ItemID.TOAD_CRUNCHIES)
            ));

            add(new Recipe("worm crunchies", createBakedRecipe(
                    BakedRecipeType.CRUNCHIES,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.KING_WORM, 2));
                            add(new Ingredient(ItemID.GNOME_SPICE, 1));
                            add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                        }
                    },
                    ItemID.ALUFT_HALF_BAKED_WORM_CRUNCHY,
                    ItemID.ALUFT_BAKED_WORM_CRUNCHY,
                    new Ingredient(ItemID.GNOME_SPICE, 1),
                    ItemID.WORM_CRUNCHIES)
            ));

            // Cocktails

            add(new Recipe("fruit blast", createCocktailRecipe(
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.PINEAPPLE, 1));
                            add(new Ingredient(ItemID.LEMON, 1));
                            add(new Ingredient(ItemID.ORANGE, 1));
                        }
                    },
                    ItemID.ALUFT_SHAKER_FRUIT_BLAST,
                    new Ingredient(ItemID.LEMON_SLICES, 1),
                    ItemID.FRUIT_BLAST)
            ));

            add(new Recipe("pineapple punch", createCocktailRecipe(
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.PINEAPPLE, 2));
                            add(new Ingredient(ItemID.LEMON, 1));
                            add(new Ingredient(ItemID.ORANGE, 1));
                        }
                    },
                    ItemID.ALUFT_SHAKER_PINEAPPLE_PUNCH,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.LIME_CHUNKS, 1));
                            add(new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1));
                            add(new Ingredient(ItemID.ORANGE_SLICES, 1));
                        }
                    },
                    ItemID.PINEAPPLE_PUNCH)
            ));

            add(new Recipe("wizard blizzard", createCocktailRecipe(
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.VODKA, 2));
                            add(new Ingredient(ItemID.GIN, 1));
                            add(new Ingredient(ItemID.LIME, 1));
                            add(new Ingredient(ItemID.LEMON, 1));
                            add(new Ingredient(ItemID.ORANGE, 1));
                        }
                    },
                    ItemID.ALUFT_SHAKER_WIZZARD_BLIZZARD,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1));
                            add(new Ingredient(ItemID.LIME_SLICES, 1));
                        }
                    },
                    ItemID.WIZARD_BLIZZARD)
            ));

            add(new Recipe("short green guy", createCocktailRecipe(
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.VODKA, 1));
                            add(new Ingredient(ItemID.LIME, 3));
                        }
                    },
                    ItemID.ALUFT_SHAKER_SGG,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.LIME_SLICES, 1));
                            add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                        }
                    },
                    ItemID.ALUFT_SGG)
            ));

            add(new Recipe("drunk dragon", createDrunkDragonRecipe()));
            add(new Recipe("chocolate saturday", createChocSaturdayRecipe()));

            add(new Recipe("Blurberry special", createCocktailRecipe(
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.VODKA, 1));
                            add(new Ingredient(ItemID.BRANDY, 1));
                            add(new Ingredient(ItemID.GIN, 1));
                            add(new Ingredient(ItemID.LEMON, 2));
                            add(new Ingredient(ItemID.ORANGE, 1));
                        }
                    },
                    ItemID.ALUFT_SHAKER_BLURBERRY_SPECIAL,
                    new ArrayList<Ingredient>() {
                        {
                            add(new Ingredient(ItemID.LEMON_CHUNKS, 1));
                            add(new Ingredient(ItemID.ORANGE_CHUNKS, 1));
                            add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                            add(new Ingredient(ItemID.LIME_SLICES, 1));
                        }
                    },
                    ItemID.BLURBERRY_SPECIAL)
            ));
        }
    };

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
    private static ArrayList<RecipeStep> createBakedRecipeHelper(BakedRecipeType bakedRecipeType,
            ArrayList<Ingredient> ingredients, int halfMadeId) {
        ArrayList<RecipeStep> unfinishedRecipe = new ArrayList<>();

        // Create raw item

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.CREATE_RAW,
                new ArrayList<Ingredient>() {
                    {
                        add(new Ingredient(ItemID.GIANNE_DOUGH, 1));
                        add(new Ingredient(bakedRecipeType.getToolId(), 1));
                    }
                },
                bakedRecipeType.getRawId())
        );

        // Raw item -> half baked item

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.CREATE_HALF_BAKED,
                new Ingredient(bakedRecipeType.getRawId(), 1, true),
                bakedRecipeType.getHalfBakedId())
        );

        // Half baked item -> half made item

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.COMBINE_INGREDIENTS,
                new ArrayList<Ingredient>() {
                    {
                        addAll(ingredients);
                        add(new Ingredient(bakedRecipeType.getHalfBakedId(), 1, true));
                    }
                },
                halfMadeId));

        return unfinishedRecipe;
    }

    /**
     * Create baked recipe with no topped ingredients
     *
     * @param ingredients Combined with half-baked item to create half-made item
     * @param finishedId  Final product of recipe
     */
    private static ArrayList<RecipeStep> createBakedRecipe(BakedRecipeType bakedRecipeType, ArrayList<Ingredient> ingredients,
            int halfMadeId, int finishedId) {
        // Create raw item -> half baked item -> half made item

        ArrayList<RecipeStep> recipe = createBakedRecipeHelper(bakedRecipeType, ingredients, halfMadeId);

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
    private static ArrayList<RecipeStep> createBakedRecipe(BakedRecipeType bakedRecipeType,
            ArrayList<Ingredient> firstIngredients, int halfMadeId,
            int unfinishedId, ArrayList<Ingredient> toppingIngredients,
            int finishedId) {
        // Create raw item -> half baked item -> half made item

        ArrayList<RecipeStep> recipe = createBakedRecipeHelper(bakedRecipeType, firstIngredients, halfMadeId);

        // Half made item -> unfinished item

        recipe.add(new RecipeStep(RecipeInstruction.BAKE_HALF_MADE,
                new Ingredient(halfMadeId, 1, true),
                unfinishedId));

        // unfinished item -> finished item

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                new ArrayList<Ingredient>() {
                    {
                        add(new Ingredient(unfinishedId, 1, true));
                        addAll(toppingIngredients);
                    }
                },
                finishedId));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(finishedId, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    /**
     * Create baked recipe with one topped ingredient
     *
     * @param firstIngredients  Combined with half-baked item to create half-made item
     * @param toppingIngredient Combined with unfinished item to create finished item
     * @param finishedId        Final product of recipe
     */
    private static ArrayList<RecipeStep> createBakedRecipe(BakedRecipeType bakedRecipeType,
            ArrayList<Ingredient> firstIngredients, int halfMadeId,
            int unfinishedId, Ingredient toppingIngredient,
            int finishedId) {
        ArrayList<Ingredient> toppingIngredients = new ArrayList<>();
        toppingIngredients.add(toppingIngredient);

        return createBakedRecipe(bakedRecipeType, firstIngredients, halfMadeId, unfinishedId, toppingIngredients,
                finishedId);
    }

    // Cocktail recipe builders

    /**
     * Partially create cocktail recipe. Only adds the step to create mixed cocktail.
     **/
    private static ArrayList<RecipeStep> createCocktailRecipeHelper(ArrayList<Ingredient> shakedIngredients,
            int mixedItem) {
        ArrayList<RecipeStep> unfinishedRecipe = new ArrayList<>();

        unfinishedRecipe.add(new RecipeStep(RecipeInstruction.MIX_COCKTAIL,
                new ArrayList<Ingredient>() {
                    {
                        addAll(shakedIngredients);
                        add(new Ingredient(ItemID.COCKTAIL_SHAKER, 1, true));
                    }
                },
                mixedItem)
        );

        return unfinishedRecipe;
    }

    /**
     * Partially creates cocktail recipe. The steps are: mix cocktail -> pour
     *
     * @param isCompleteRecipe Flag that tells whether this is a complete or partial recipe
     */
    private static ArrayList<RecipeStep> createCocktailRecipeHelper(ArrayList<Ingredient> shakedIngredients,
            int mixedItem,
            ArrayList<Ingredient> pouredIngredients,
            int pouredItem, boolean isCompleteRecipe) {
        // Create mixed cocktail

        ArrayList<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, mixedItem);

        // Mixed cocktail -> poured cocktail

        recipe.add(new RecipeStep(RecipeInstruction.POUR, new ArrayList<Ingredient>() {
                    {
                        add(new Ingredient(ItemID.COCKTAIL_GLASS_EMPTY, 1, true));
                        add(new Ingredient(mixedItem, 1, true));
                        addAll(pouredIngredients);
                    }
                },
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
    private static ArrayList<RecipeStep> createCocktailRecipe(ArrayList<Ingredient> shakedIngredients,
            int mixedItem,
            ArrayList<Ingredient> pouredIngredients,
            int finishedItem) {
        return createCocktailRecipeHelper(shakedIngredients, mixedItem, pouredIngredients, finishedItem, true);
    }

    /**
     * Simple cocktail recipe with one poured ingredient
     **/
    private static ArrayList<RecipeStep> createCocktailRecipe(ArrayList<Ingredient> shakedIngredients, int mixedItem,
            Ingredient pouredIngredient, int finishedItem) {
        ArrayList<Ingredient> pouredIngredients = new ArrayList<>();
        pouredIngredients.add(pouredIngredient);

        return createCocktailRecipe(shakedIngredients, mixedItem, pouredIngredients, finishedItem);
    }

    // Heated cocktail recipes

    private static ArrayList<RecipeStep> createDrunkDragonRecipe() {
        // Create mixed cocktail -> poured cocktail

        ArrayList<Ingredient> shakedIngredients = new ArrayList<Ingredient>() {
            {
                add(new Ingredient(ItemID.VODKA, 1));
                add(new Ingredient(ItemID.GIN, 1));
                add(new Ingredient(ItemID.DWELLBERRIES, 1));
            }
        };

        ArrayList<Ingredient> toppedIngredients = new ArrayList<Ingredient>() {
            {
                add(new Ingredient(ItemID.PINEAPPLE_CHUNKS, 1));
                add(new Ingredient(ItemID.POT_OF_CREAM, 1));
            }
        };

        ArrayList<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, ItemID.ALUFT_SHAKER_DRUNK_DRAGON,
                new ArrayList<>(), ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREFINISH, false);

        // Poured cocktail -> unfinished item

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                new ArrayList<Ingredient>() {
                    {
                        add(new Ingredient(ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREFINISH, 1, true));
                        addAll(toppedIngredients);
                    }
                },
                ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREHEAT));

        // Unfinished item -> finished item

        recipe.add(new RecipeStep(RecipeInstruction.HEAT_COCKTAIL,
                new Ingredient(ItemID.ALUFT_SHAKER_DRUNK_DRAGON_PREHEAT, 1, true), ItemID.DRUNK_DRAGON));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(ItemID.DRUNK_DRAGON, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    private static ArrayList<RecipeStep> createChocSaturdayRecipe() {
        // Create mixed cocktail -> poured cocktail

        ArrayList<Ingredient> shakedIngredients = new ArrayList<Ingredient>() {
            {
                add(new Ingredient(ItemID.WHISKY, 1));
                add(new Ingredient(ItemID.CHOCOLATE_BAR, 1));
                add(new Ingredient(ItemID.EQUA_LEAVES, 1));
                add(new Ingredient(ItemID.BUCKET_MILK, 1));
            }
        };

        ArrayList<RecipeStep> recipe = createCocktailRecipeHelper(shakedIngredients, ItemID.ALUFT_SHAKER_CHOC_SATURDAY,
                new ArrayList<>(), ItemID.ALUFT_SHAKER_CHOC_SATURDAY_PREHEAT, false);

        // poured cocktail -> unfinished item

        recipe.add(new RecipeStep(RecipeInstruction.HEAT_COCKTAIL,
                new Ingredient(ItemID.ALUFT_SHAKER_CHOC_SATURDAY_PREHEAT, 1, true), ItemID.ALUFT_SHAKER_CHOC_SATURDAY_HEATED));

        // unfinished item -> finished item

        recipe.add(new RecipeStep(RecipeInstruction.ADD_TOPPINGS,
                new ArrayList<Ingredient>() {
                    {
                        add(new Ingredient(ItemID.ALUFT_SHAKER_CHOC_SATURDAY_HEATED, 1, true));
                        add(new Ingredient(ItemID.CHOCOLATE_DUST, 1));
                        add(new Ingredient(ItemID.POT_OF_CREAM, 1));
                    }
                }
                , ItemID.CHOCOLATE_SATURDAY));

        // Deliver finished item

        recipe.add(new RecipeStep(RecipeInstruction.DELIVER, new Ingredient(ItemID.ALUFT_CHOC_SATURDAY, 1,
                true), NO_PRODUCED_ITEM_ID));

        return recipe;
    }

    /**
     * Get recipe by name
     **/
    public static Recipe getRecipe(String name) {
        for (Recipe recipe : list) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }

        throw new InvalidParameterException("Recipe with name " + name + " not found");
    }
}
