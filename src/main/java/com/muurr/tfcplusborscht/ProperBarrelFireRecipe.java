package com.muurr.tfcplusborscht;

import java.util.Stack;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.TileEntities.TEBarrel;
import com.dunk.tfc.api.Food;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Crafting.BarrelFireRecipe;
import com.dunk.tfc.api.Crafting.BarrelRecipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class ProperBarrelFireRecipe extends BarrelFireRecipe
{
	int fireTicksRequired;
	boolean fireTickScales = false;
	boolean distillationRecipe = false;

	public ProperBarrelFireRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int fireTicks)
	{
		super(inputItem, inputFluid, outIS, outputFluid, fireTicks);
		this.fireTicksRequired = fireTicks;
	}

	public ProperBarrelFireRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int seal, int fireTicks)
	{
		super(inputItem, inputFluid, outIS, outputFluid, seal, fireTicks);
		this.fireTicksRequired = fireTicks;
	}

	public Boolean matches(ItemStack item, FluidStack fluid, TEBarrel te)
	{
		if (this.distillationRecipe)
		{
			boolean receiver = te.isReceiverValidForRecipe(this);

			if (!receiver)
			{
				return false;
			}
		}
		if (item != null && this.recipeIS != null && fluid != null && this.getInFluid() != null)
		{
			float baseLiquid = this.recipeFluid.amount;
			float baseAmount = this.recipeIS.stackSize;
			float amountRatio = baseLiquid / baseAmount;
			float amount = item.stackSize;
			if (amountRatio * amount < fluid.amount && !(item.getItem() instanceof ItemFoodTFC))
			{
				return false;
			}
		}
		if (item != null && item.getItem() instanceof ItemFoodTFC && this.recipeIS != null && this.recipeIS.getItem() instanceof ItemFoodTFC && this.recipeFluid != null
		&& fluid != null)
		{
			float baseWeight = Food.getWeight(this.recipeIS);
			float baseLiquid = this.recipeFluid.amount;
			float weightRatio = baseLiquid / baseWeight;
			float weight = Food.getWeight(item);
			if (weightRatio * weight < fluid.amount)
			{
				return false;
			}
		}
		boolean itemsEqual = item == null && recipeIS == null || OreDictionary.itemMatches(recipeIS, item, false);
		if (!itemsEqual)
		{
			itemsEqual = recipeIS == null && recipeOutIS != null && item != null && item.getItem() == recipeOutIS.getItem();
		}
		if (this.distillationRecipe)
		{
			itemsEqual = item != null && item.getItem() == TFCItems.clayBlowpipe && item.getItemDamage() == 1;
		}
		if (recipeIS != null && !this.distillationRecipe && item != null && this.recipeOutFluid != null && recipeOutIS == null && !this.removesLiquid
		&& (item.stackSize / recipeIS.stackSize) * this.recipeOutFluid.amount + (fluid != null ? fluid.amount : 0) > te.getMaxLiquid())
		{
			return false;
		}
		// If the recipe has no fluid, accept either no fluid or the output
		// fluid as a match
		return (itemsEqual) && (((recipeFluid != null && recipeFluid.isFluidEqual(fluid))
		|| (recipeFluid == null && this.recipeOutFluid != null && (fluid == null || recipeOutFluid.isFluidEqual(fluid)))) || recipeFluid == null);
	}

	public ProperBarrelFireRecipe setFireTicksScale(boolean b)
	{
		this.fireTickScales = b;
		return this;
	}

	public ProperBarrelFireRecipe setDistillationRecipe(boolean b)
	{
		this.distillationRecipe = b;
		return this;
	}
}

