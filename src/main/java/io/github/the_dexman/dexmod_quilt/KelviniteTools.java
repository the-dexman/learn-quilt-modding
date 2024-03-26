package io.github.the_dexman.dexmod_quilt;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class KelviniteTools implements ToolMaterial
{

	@Override
	public int getDurability() {
		return 256;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 4;
	}

	@Override
	public float getAttackDamage() {
		return 0;
	}

	@Override
	public int getMiningLevel() {
		return 3;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}
}
