package com.muurr.tfcplusborscht;

import java.util.List;

import com.dunk.tfc.Reference;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.Devices.BlockBarrel;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Sounds;
import com.dunk.tfc.Core.Player.BodyTempStats;
import com.dunk.tfc.Core.Player.FoodStatsTFC;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.Handlers.Network.ItemPotterySmashPacket;
import com.dunk.tfc.Items.ItemAlcohol;
import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.dunk.tfc.api.Interfaces.ISmashable;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ProperItemDrink extends ItemDrink implements ISmashable
{
	boolean canDrinkInParts = false;
	float waterRestore = 0;
	float calories = 0;
	int tier = 0;
	//Volume in mB
	protected float volume;
	private long heatProtectionDuration = 1800;
	private long coldProtectionDuration = 0;
	private EnumFoodGroup foodGroup;
	protected boolean pottery;
	protected boolean skin;
	protected boolean bowl;

	public ProperItemDrink(float v)
	{
		super(v);
		this.volume = v;
		this.setFolder("food/");
		this.setContainerItem(TFCItems.glassBottle);
		this.maxStackSize = 4;
	}

	public ProperItemDrink(float v, int type)
	{
		this(v);
		this.pottery = type == 1;
		this.skin = type == 2;
		this.bowl = type == 3;
		this.maxStackSize = 4;
		if (pottery)
		{
			this.setContainerItem(TFCItems.potteryJug);
		}
		else if (skin)
		{
			this.setContainerItem(TFCItems.waterskinEmpty);
		}
		else if (bowl)
		{
			this.setContainerItem(TFCItems.potteryBowl);
		}
		if (type > 0)
		{
			this.maxStackSize = 1;
			this.stackable = false;
		}
	}

	public ProperItemDrink setCanDrinkInParts(boolean b)
	{
		this.canDrinkInParts = b;
		this.setMaxDamage((int) (volume / 50));
		return this;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		if (pottery || bowl)
		{
			if (!hasContainerItem(itemStack))
			{
				return null;
			}
			return new ItemStack(getContainerItem(), 1, 1);
		}
		return super.getContainerItem(itemStack);
	}

	@Override
	public boolean canSmash(ItemStack item)
	{
		//Always true?
		return !skin;

	}

	@Override
	public void smashAnimate(World world, double x, double y, double z)
	{
		String smashBlock = "blockdust_" + Block.getIdFromBlock(pottery||bowl ? TFCBlocks.pottery : Blocks.glass) + (pottery||bowl ? "_15" : "_0");
		for (double i = 0; i < Math.PI * 2; i += Math.PI / 4)
		{
			world.spawnParticle(smashBlock, x, y, z, Math.cos(i) * 0.2D, 0.15D + world.rand.nextDouble() * 0.1D, Math.sin(i) * 0.2D);
		}
	}

	@Override
	public int getItemStackLimit(ItemStack is)
	{
		if (pottery || skin || bowl)
		{
			return 1;
		}
		return 4;
	}

	public ProperItemDrink setWaterRestoreRatio(float f)
	{
		waterRestore = f;
		return this;
	}

	public ProperItemDrink setCalories(float c)
	{
		calories = c;
		return this;
	}

	public ProperItemDrink setTier(int i)
	{
		tier = i;
		return this;
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (canDrinkInParts && !world.isRemote && itemstack.getItemDamage() > 0)
		{
			MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
			if (mop == null)
			{

			}
			else
			{
				if (mop.typeOfHit == MovingObjectType.BLOCK)
				{
					int x = mop.blockX;
					int y = mop.blockY;
					int z = mop.blockZ;

					if (world.getBlock(x, y, z) instanceof BlockBarrel)
					{
						return itemstack;
					}

					// Handle flowing water
					int flowX = x;
					int flowY = y;
					int flowZ = z;
					switch (mop.sideHit)
					{
						case 0:
							flowY = y - 1;
							break;
						case 1:
							flowY = y + 1;
							break;
						case 2:
							flowZ = z - 1;
							break;
						case 3:
							flowZ = z + 1;
							break;
						case 4:
							flowX = x - 1;
							break;
						case 5:
							flowX = x + 1;
							break;
					}

					if (!player.isSneaking() && !world.isRemote && TFC_Core.isFreshWater(world.getBlock(x, y, z)) && TFC_Core.isFreshWater(world.getBlock(flowX, flowY, flowZ)) &&
							FluidContainerRegistry.getFluidForFilledItem(itemstack) != null && FluidContainerRegistry.getFluidForFilledItem(itemstack).getFluid() == TFCFluids.FRESHWATER)
					{

						if (itemstack.stackSize == 1)
						{
							itemstack = new ItemStack(itemstack.getItem());
							player.inventory.setInventorySlotContents(player.inventory.currentItem, itemstack);
							return itemstack;
						}
						else
						{
							itemstack.stackSize--;
							TFC_Core.giveItemToPlayer(new ItemStack(itemstack.getItem()), player);
							return itemstack;
						}
					}

					if (!world.canMineBlock(player, x, y, z))
					{
						return itemstack;
					}

					if (!player.canPlayerEdit(x, y, z, mop.sideHit, itemstack))
					{
						return itemstack;
					}

				}
			}
		}

		if (waterRestore > 0)
		{
			FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
			Boolean canDrink = fs.getMaxWater(player) - 400 > fs.waterLevel || this instanceof ProperItemAlcohol;
			if (canDrink)
			{
				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
			}
		}
		else
		{
			player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		}
		return itemstack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister registerer)
	{
		if (pottery)
		{
			this.itemIcon = registerer.registerIcon("terrafirmacraftplus" + ":Clay Jug Overlay");
		}
		else if (bowl)
		{
			this.itemIcon = registerer.registerIcon("terrafirmacraftplus" + ":Bowl Overlay");
		}
		else if (skin)
		{
			this.itemIcon = registerer.registerIcon("terrafirmacraftplus" + ":Waterskin Overlay");
		}
		else
		{
			this.itemIcon = registerer.registerIcon("terrafirmacraftplus" + ":Glass Bottle Overlay");
		}
	}

	public ProperItemDrink setFoodGroup(EnumFoodGroup group)
	{
		this.foodGroup = group;
		return this;
	}

	public ProperItemDrink setHeatProtection(int prot)
	{
		this.heatProtectionDuration = prot;
		return this;
	}

	public ProperItemDrink setColdProtection(int prot)
	{
		this.coldProtectionDuration = prot;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int pass)
	{
		if (pottery || bowl)
		{
			return pass == 1 ? this.itemIcon : this.getContainerItem().getIcon(getContainerItem(stack), pass);
		}
		return pass == 1 ? this.itemIcon : this.getContainerItem().getIcon(stack, pass);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int pass)
	{
		if (pottery)
		{
			return pass == 1 ? FluidContainerRegistry.getFluidForFilledItem(is).getFluid().getColor() : super.getColorFromItemStack(new ItemStack(TFCItems.potteryJug, 1, 1), pass);
		}
		if (bowl)
		{
			return pass == 1 ? FluidContainerRegistry.getFluidForFilledItem(is).getFluid().getColor() : super.getColorFromItemStack(new ItemStack(TFCItems.potteryBowl, 1, 1), pass);
		}
		if(pass == 1 && FluidContainerRegistry.getFluidForFilledItem(is) == null) {
			return super.getColorFromItemStack(is, pass);
		}
		return pass == 1 ? FluidContainerRegistry.getFluidForFilledItem(is).getFluid().getColor() : super.getColorFromItemStack(is, pass);
	}
	@Override
	public int getScaledWaterRestore(ItemStack is)
	{
		if (canDrinkInParts)
		{
			return (int) (waterRestore * 8f * volume * ((float) (this.getMaxDamage() - is.getItemDamage()) / (float) this.getMaxDamage()));
		}
		return (int) (waterRestore * 8f * volume);
	}
	@Override
	public float getScaledFoodRestore()
	{
		return calories * 0.012f * volume;
	}
	@Override
	protected ItemStack drinking(ItemStack is, World world, EntityPlayer player, int maxSips)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--is.stackSize;
		}
		if(maxSips == -1)
		{
			maxSips = (int) (volume/50);
		}
		if (waterRestore > 0)
		{
			FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
			Boolean canDrink = fs.getMaxWater(player) - 400 > fs.waterLevel;
			if (canDrink)
			{
				//to convert calories to hunger, we see that 24 = 2000 calories
				//tf restore = calories * 24/ 2000 = 0.012

				//water to restore = math.min(total water we could restore, thirst delta)
				int waterToRestore = getScaledWaterRestore(is) + 0;
				if (canDrinkInParts)
				{
					waterToRestore = (int) Math.min(Math.min(8f * maxSips * 50 * waterRestore, getScaledWaterRestore(is)), fs.getMaxWater(player) - fs.waterLevel);
					if (waterToRestore % 400 > 0 && waterToRestore + (400 - waterToRestore % 400) <= getScaledWaterRestore(is) && (volume/50) == maxSips)
					{
						//waterToRestore += (400 - waterToRestore % 400);
					}
					if (!is.hasTagCompound())
					{
						is.stackTagCompound = new NBTTagCompound();
					}
					is.stackTagCompound.setInteger("WaterConsumed", (int)(waterToRestore / 8f));
				}
				float restoreRatio = (float) waterToRestore / (float) getScaledWaterRestore(is);
				TFC_Core.getPlayerFoodStats(player).restoreWater(player, (int) (restoreRatio * getScaledWaterRestore(is)));
				TFC_Core.getPlayerFoodStats(player).restoreHunger(player, restoreRatio * getScaledFoodRestore());
				fs = TFC_Core.getPlayerFoodStats(player);
				if (foodGroup != null)
				{
					fs.addNutrition(foodGroup, restoreRatio * 5);
				}
				//fs.restoreWater(player, waterRestore);
				BodyTempStats bodyTemp = TFC_Core.getBodyTempStats(player);
				if (bodyTemp.temporaryHeatProtection < 1 && heatProtectionDuration > 0)
				{
					bodyTemp.temporaryHeatProtection++;
				}
				if (bodyTemp.temporaryColdProtection < 1 && coldProtectionDuration > 0)
				{
					bodyTemp.temporaryColdProtection++;
				}
				if (bodyTemp.tempHeatTimeRemaining < heatProtectionDuration)
				{
					bodyTemp.tempHeatTimeRemaining = heatProtectionDuration;
				}
				if (bodyTemp.tempColdTimeRemaining < coldProtectionDuration)
				{
					bodyTemp.tempColdTimeRemaining = coldProtectionDuration;
				}
				TFC_Core.setBodyTempStats(player, bodyTemp);
				TFC_Core.setPlayerFoodStats(player, fs);
			}
		}
		return is;
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack is, World world, EntityPlayer player, int itemUseCount)
	{
		//Assume itemUseCount was 32
		if(itemUseCount < 24)
		{
			//drink it anyway!
			int uses = 1 + ((24-itemUseCount))/6;
			is = drinking(is, world, player, uses);
			postDrink(is, world, player);
		}
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player)
	{
		is = drinking(is, world, player, -1);
		return postDrink(is, world, player);
	}

	protected ItemStack postDrink(ItemStack is, World world, EntityPlayer player)
	{
		// First try to add the empty bottle to an existing stack of bottles in
		// the inventory
		if(world.isRemote)
		{
			return is;
		}
		if (is == null || player == null || world == null)
		{
			return is;
		}
		ItemStack toGive = getContainerItem(is);
		if (canDrinkInParts && is != null && is.hasTagCompound() && is.stackTagCompound.hasKey("WaterConsumed") && is.stackTagCompound.getInteger("WaterConsumed") < getScaledWaterRestore(is))
		{
			int deltaDrink = (int) Math.max(1, (getMaxDamage(is) * ((float) is.stackTagCompound.getInteger("WaterConsumed") / (volume))));
			int newDamage = is.getItemDamage() + deltaDrink;
			if (newDamage < getMaxDamage(is))
			{
				toGive = new ItemStack(this, 1, newDamage);
			}
		}
		FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
		int drunkeness = (int) (fs.soberTime/100000);
		boolean dropChance = 0 == world.rand.nextInt(Math.max(1,50 - drunkeness)) + (!pottery?1:0)+ (player.capabilities.isCreativeMode?1:0);
		if (is.stackSize <= 0 || (is.stackSize == 1 && player.capabilities.isCreativeMode))
		{
			if (dropChance)
			{
				EntityItem droppedItem = new EntityItem(world, player.posX, player.posY + 1.25, player.posZ, toGive);
				droppedItem.delayBeforeCanPickup = 15;
				world.spawnEntityInWorld(droppedItem);
				return null;
			}

			player.inventory.setInventorySlotContents(player.inventory.currentItem, toGive);
			return toGive;
		}
		else if ((is.stackSize >= 1 && !player.capabilities.isCreativeMode) || (is.stackSize > 1 ))
		{
			ItemStack newStack = is.copy();
			if(player.capabilities.isCreativeMode)
			{
				newStack.stackSize--;
			}
			if(newStack.hasTagCompound() && newStack.stackTagCompound.hasKey("WaterConsumed"))
			{
				newStack.stackTagCompound.removeTag("WaterConsumed");
				if(newStack.stackTagCompound.hasNoTags())
				{
					newStack.stackTagCompound = null;
				}
			}
			if(toGive != null && toGive.getItem() == this.getContainerItem())
			{
				TFC_Core.giveItemToPlayer(toGive, player);
				if (dropChance)
				{
					EntityItem droppedItem = new EntityItem(world, player.posX, player.posY + 1.25, player.posZ, newStack);
					droppedItem.delayBeforeCanPickup = 15;
					world.spawnEntityInWorld(droppedItem);
					return null;
				}
				player.inventory.setInventorySlotContents(player.inventory.currentItem, newStack);
				return newStack;
			}
			else
			{
				TFC_Core.giveItemToPlayer(newStack, player);
				if (dropChance)
				{
					EntityItem droppedItem = new EntityItem(world, player.posX, player.posY + 1.25, player.posZ, toGive);
					droppedItem.delayBeforeCanPickup = 15;
					world.spawnEntityInWorld(droppedItem);
					return null;
				}
				player.inventory.setInventorySlotContents(player.inventory.currentItem, toGive);
			}

			return toGive;
		}
		else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(toGive))
		{
			// If we couldn't fit the empty bottle in the inventory, and we
			// drank the last alcohol bottle, put the empty bottle in the empty
			// held slot
			if (is.stackSize == 0)
				return toGive;
				// If we couldn't fit the empty bottle in the inventory, and there
				// is more alcohol left in the stack, drop the bottle on the ground
			else
				player.dropPlayerItemWithRandomChoice(toGive, false);
		}

		return is;
	}
}
