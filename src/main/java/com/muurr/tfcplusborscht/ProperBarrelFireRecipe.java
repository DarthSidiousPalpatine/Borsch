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

	public boolean isDistillationRecipe()
	{
		return this.distillationRecipe;
	}

	public Boolean isInFluid(FluidStack item)
	{
		return recipeFluid.isFluidEqual(item);
	}

	public ItemStack getInItem()
	{
		return recipeIS;
	}

	public FluidStack getInFluid()
	{
		return recipeFluid;
	}

	public ItemStack getRecipeOutIS()
	{
		return recipeOutIS;
	}

	public FluidStack getRecipeOutFluid()
	{
		return recipeOutFluid;
	}

	public int getSealTime()
	{
		return sealTime;
	}

	public boolean isRemovesLiquid()
	{
		return removesLiquid;
	}

	public int getMinTechLevel()
	{
		return minTechLevel;
	}

	public boolean isAllowAnyStack()
	{
		return allowAnyStack;
	}

	public String getRecipeName()
	{
		String s = "";
		if (this.recipeOutIS != null)
		{
			if (recipeOutIS.stackSize > 1)
				s += recipeOutIS.stackSize + "x ";
			s += recipeOutIS.getDisplayName();
		}
		if (recipeOutFluid != null && (this.recipeFluid == null || !this.recipeFluid.isFluidEqual(recipeOutFluid)))
			s = recipeOutFluid.getFluid().getLocalizedName(recipeOutFluid);
		return s;
	}

	public int getFireTicksRequired(FluidStack fs, ItemStack is)
	{

		if (this.fireTickScales)
		{
			if (fs != null && this.recipeFluid != null && fs.amount > 0 && this.recipeFluid.amount > 0)
			{
				// The ratio of how much fluid there is to how much fluid we
				// have
				// per run
				// This allows us to require more heating time for more liquid
				float ratio = (float) fs.amount / (float) this.recipeFluid.amount;
				return (int) (this.fireTicksRequired * ratio);
			}
			else if (this.recipeFluid == null && this.recipeIS != null)
			{
				float ratio = (float) is.stackSize / (float) this.recipeIS.stackSize;
				return (int) (this.fireTicksRequired * ratio);
			}
		}
		return this.fireTicksRequired;
	}

	public boolean isSealedRecipe()
	{
		return this.sealedRecipe;
	}

	protected int getnumberOfRuns(ItemStack inIS, FluidStack inFS)
	{
		int runs = 0;
		int div = 0;
		if (inIS != null && recipeIS != null)
		{
			runs = inIS.stackSize / this.recipeIS.stackSize;
			div = inFS.amount / this.getInFluid().amount;
		}
		else if (recipeIS == null && this.recipeOutIS != null && inIS != null && inIS.getItem() == recipeOutIS.getItem())
		{
			// runs = inIS.getMaxStackSize() - inIS.stackSize;
			div = inFS.amount / this.getInFluid().amount;
			return div;
		}
		else if (recipeIS == null)
		{
			div = inFS.amount / this.getInFluid().amount;
			return distillationRecipe ? Math.min(div, 1) : div;
		}
		if (distillationRecipe)
		{
			return Math.min(runs, Math.min(div, 1));
		}
		return Math.min(runs, div);
	}

	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime, int fireTime)
	{
		Stack<ItemStack> stackList = new Stack();
		ItemStack outStack = null;

		if (recipeOutIS != null)
		{
			stackList.clear();
			outStack = recipeOutIS.copy();
			int outputCount = outStack.stackSize * this.getnumberOfRuns(inIS, inFS);
			int maxStackSize = outStack.getMaxStackSize();
			Item item = outStack.getItem();
			int damage = outStack.getItemDamage();
			int initialItems = 0 + 0;

			if (this.recipeIS != null && recipeIS.getItem() instanceof ItemFoodTFC && this.recipeOutIS != null && this.recipeOutIS.getItem() instanceof ItemFoodTFC)
			{
				outputCount = (int) Math.min(Food.getWeight(inIS) / Food.getWeight(this.recipeIS), inFS.amount / this.recipeFluid.amount);

				// we did
				float weights = outputCount * Food.getWeight(this.recipeOutIS);
				maxStackSize = 160; // Max food weight
				while (weights >= maxStackSize) // Add as many full-sized
				// stacks
				// as possible to stackList.
				{
					ItemStack outputs = new ItemStack(item, 1, damage);
					outputs.stackTagCompound = new NBTTagCompound();
					Food.setWeight(outputs, 160);
					stackList.push(outputs);
					weights -= maxStackSize;
				}
				float remainder = weights % maxStackSize; // The
				// amount
				// remaining
				// after
				// making
				// full-sized
				// stacks.
				if (remainder > 0)
				{
					ItemStack outputs = new ItemStack(item, 1, damage);
					outputs.stackTagCompound = new NBTTagCompound();
					Food.setWeight(outputs, remainder);
					stackList.push(outputs); // Push
												// this
												// on
												// first,
												// so
												// it
												// doesn't
												// end
												// up
												// in
												// the
												// input
												// slot.
					weights -= remainder;
				}
				return stackList;
			}
			else
			{
				if (this.recipeIS == null && inIS != null && recipeOutIS != null && inIS.getItem() == recipeOutIS.getItem())
				{
					initialItems = inIS.stackSize;
				}

				while (outputCount >= maxStackSize) // Add as many full-sized
													// stacks
													// as possible to stackList.
				{
					stackList.push(new ItemStack(item, maxStackSize, damage));
					outputCount -= maxStackSize;
				}

				int remainder = (outputCount + initialItems) % maxStackSize; // The
																				// amount
																				// remaining
																				// after
																				// making
																				// full-sized
																				// stacks.
				if (remainder > 0)
				{
					stackList.push(new ItemStack(item, remainder, damage)); // Push
																			// this
																			// on
																			// first,
																			// so
																			// it
																			// doesn't
																			// end
																			// up
																			// in
																			// the
																			// input
																			// slot.
					outputCount -= remainder;
				}

				if (outputCount > 0)
				{
					stackList.push(new ItemStack(item, outputCount, damage));
				}
			}
			return stackList;

		}
		if (!removesLiquid && inIS != null && this.recipeFluid == null)
		{
			stackList.clear();
			outStack = null;
			stackList.push(outStack);
		}
		else if (!removesLiquid && inIS != null && inFS != null)
		{
			stackList.clear();
			outStack = inIS.copy();
			outStack.stackSize -= inFS.amount / this.recipeOutFluid.amount;
			stackList.push(outStack);
		}
		if (outStack == null)
		{
			stackList.clear();
			stackList.push(outStack);
		}
		return stackList;
	}

	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime, int fireTime)
	{
		if (recipeOutFluid != null)
		{
			FluidStack fs = null;
			// The FluidStack .copy() method does not make a copy of the NBT
			// tag, which may have been the cause of the quantum entanglement
			if (recipeOutFluid.tag != null)
			{
				fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount, (NBTTagCompound) recipeOutFluid.tag.copy());
			}
			else
			{
				fs = new FluidStack(recipeOutFluid.getFluid(), recipeOutFluid.amount);
			}

			if (!removesLiquid && inFS != null && this.recipeFluid != null)
			{
				fs.amount = inFS.amount;
			}
			else if (!removesLiquid && inFS != null && this.recipeFluid == null)
			{
				fs.amount *= inIS.stackSize;
			}
			else if (inIS == null && this.recipeIS == null)
			{
				fs.amount *= this.getnumberOfRuns(inIS, inFS);
			}
			else if (inIS != null && this.recipeIS != null)
			{
				fs.amount *= inIS.stackSize;
			}
			else if (inIS != null && this.recipeOutIS != null && inIS.getItem() == this.recipeOutIS.getItem())
			{
				int numRuns = this.getnumberOfRuns(inIS, inFS);
				fs.amount *= numRuns;
			}
			return fs;
		}
		return null;
	}

}

