/*
 * Copyright (c) 2020, MMagicala <https://github.com/MMagicala>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.github.mmagicala.gnomeRestaurant.recipe;

public enum RecipeInstruction {
    CREATE_RAW("Create the raw batter"),
    CREATE_HALF_BAKED("Bake the raw batter"),
    COMBINE_INGREDIENTS("Combine ingredients"),
    BAKE_HALF_MADE("Bake item again"),
    ADD_TOPPINGS("Add topping ingredients"),

    MIX_COCKTAIL("Mix ingredients in the cocktail shaker"),
    POUR("Pour mix into the cocktail glass"),
    HEAT_COCKTAIL("Heat the cocktail"),

    DELIVER("Deliver the item to recipient");

    private final String overlayDirections;

    RecipeInstruction(String overlayDirections) {
        this.overlayDirections = overlayDirections;
    }

    public String getOverlayDirections() {
        return this.overlayDirections;
    }
}
