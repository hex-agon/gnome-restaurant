package io.github.mmagicala.gnomeRestaurant.recipe;

import java.util.ArrayList;
import lombok.Getter;

public class Recipe
{
	@Getter
	private String name;
	@Getter
	private ArrayList<RecipeStep> steps;

	public Recipe(String name, ArrayList<RecipeStep> steps)
	{
		this.name = name;
		this.steps = steps;
	}

	/**
	 * Return the item id of the final product
	 **/
	public int getItemId()
	{
		int numSteps = steps.size();
		RecipeStep deliveryStep = steps.get(numSteps - 1);
		return deliveryStep.getIngredients().get(0).getItemId();
	}

	/**
	 * Return raw ingredients used in a recipe at step number @stepIdx + 1
	 **/
	public ArrayList<Ingredient> getNextRawIngredients(int stepIdx)
	{
		ArrayList<Ingredient> ingredients = new ArrayList<>();

		// Collect raw ingredients from each step
		int numSteps = steps.size();
		for (int i = stepIdx + 1; i < numSteps; i++)
		{
			ArrayList<Ingredient> rawIngredients = steps.get(i).getRawIngredients();
			ingredients.addAll(rawIngredients);
		}

		return ingredients;
	}
}
