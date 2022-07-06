package com.muurr.tfcplusborscht;

import com.dunk.tfc.CommonProxy;
import com.dunk.tfc.Core.FluidBaseTFC;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Proxy {
	public void registerItems()
	{
		GameRegistry.registerItem(ItemSetup.chickenbouillon, ItemSetup.chickenbouillon.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.meatbouillon, ItemSetup.meatbouillon.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.fishbouillon, ItemSetup.fishbouillon.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.mushroombouillon, ItemSetup.mushroombouillon.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.bonebouillon, ItemSetup.bonebouillon.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.chickensoup, ItemSetup.chickensoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.potatosoup, ItemSetup.potatosoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.cabbagesoup, ItemSetup.cabbagesoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.borscht, ItemSetup.borscht.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.fishsoup, ItemSetup.fishsoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.mushroomsoup, ItemSetup.mushroomsoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.peasantsoup, ItemSetup.peasantsoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.okroshka, ItemSetup.okroshka.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.cheesesoup, ItemSetup.cheesesoup.getUnlocalizedName());
		GameRegistry.registerItem(ItemSetup.milksoup, ItemSetup.milksoup.getUnlocalizedName());
	}
	public void registerFluids()
	{
		//Bouillons
		FluidRegistry.registerFluid(ItemSetup.CHICKENBOUILLON);
		setupFluidDrinks(ItemSetup.CHICKENBOUILLON, 1000, ItemSetup.chickenbouillon, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.MEATBOUILLON);
		setupFluidDrinks(ItemSetup.MEATBOUILLON, 1000, ItemSetup.meatbouillon, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.FISHBOUILLON);
		setupFluidDrinks(ItemSetup.FISHBOUILLON, 1000, ItemSetup.fishbouillon, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.MUSHROOMBOUILLON);
		setupFluidDrinks(ItemSetup.MUSHROOMBOUILLON, 1000, ItemSetup.mushroombouillon, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.BONEBOUILLON);
		setupFluidDrinks(ItemSetup.BONEBOUILLON, 1000, ItemSetup.bonebouillon, TFCItems.glassBottle);

		//Soups
		FluidRegistry.registerFluid(ItemSetup.CHICKENSOUP);
		setupFluidDrinks(ItemSetup.CHICKENSOUP, 1000, ItemSetup.chickenbouillon, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.POTATOSOUP);
		setupFluidDrinks(ItemSetup.POTATOSOUP, 1000, ItemSetup.potatosoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.CABBAGESOUP);
		setupFluidDrinks(ItemSetup.CABBAGESOUP, 1000, ItemSetup.cabbagesoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.BORSCHT);
		setupFluidDrinks(ItemSetup.BORSCHT, 1000, ItemSetup.borscht, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.FISHSOUP);
		setupFluidDrinks(ItemSetup.FISHSOUP, 1000, ItemSetup.fishsoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.MUSHROOMSOUP);
		setupFluidDrinks(ItemSetup.MUSHROOMSOUP, 1000, ItemSetup.mushroomsoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.PEASANTSOUP);
		setupFluidDrinks(ItemSetup.PEASANTSOUP, 1000, ItemSetup.peasantsoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.OKROSHKA);
		setupFluidDrinks(ItemSetup.OKROSHKA, 1000, ItemSetup.okroshka, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.CHEESESOUP);
		setupFluidDrinks(ItemSetup.CHEESESOUP, 1000, ItemSetup.cheesesoup, TFCItems.glassBottle);
		FluidRegistry.registerFluid(ItemSetup.MILKSOUP);
		setupFluidDrinks(ItemSetup.MILKSOUP, 1000, ItemSetup.milksoup, TFCItems.glassBottle);
	}
	
	private void registerFluidContainerHelper(Fluid fluid, int baseVolume, ItemStack baseItem, ItemStack baseContainer)
	{
		FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume),baseItem, baseContainer);
		if(TFCItems.potteryDrinks != null && TFCItems.potteryDrinks.containsKey(baseItem.getUnlocalizedName()))
		{
			if(baseContainer != null && baseItem != null && baseContainer.getItem() == baseItem.getItem())
			{
				Item potteryVersion = TFCItems.potteryDrinks.get(baseItem.getUnlocalizedName());
				FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), new ItemStack(potteryVersion, 1, baseItem.getItemDamage()), new ItemStack(potteryVersion,1, baseContainer.getItemDamage()));
			}
			else
			{
				FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), new ItemStack(TFCItems.potteryDrinks.get(baseItem.getUnlocalizedName()), 1, baseItem.getItemDamage()), new ItemStack(TFCItems.potteryJug,1, 1));
			}
			
		}
	}

	protected void setupFluidDrinks(Fluid fluid, int mB, Item drink, Item container)
	{
		int num = mB/50;
		registerFluidContainerHelper(fluid, /*mB - (i*50)*/50, new ItemStack(drink,1,num-1), new ItemStack(container));
		for(int i = 0; i < num-1; i++)
		{
			registerFluidContainerHelper(fluid, /*mB - (i*50)*/50, new ItemStack(drink,1,i),  new ItemStack(drink,1,i+1));
		}
	}
	
	protected void setupFluidDrinks(Fluid fluid, int mB, Item drink, int minDrinkDamage, Item container, int containerDamage)
	{
		int num = mB/50;
		registerFluidContainerHelper(fluid, /*mB - (i*50)*/50, new ItemStack(drink,1,minDrinkDamage + num-1), new ItemStack(container, 1, containerDamage));
		for(int i = 0; i < num-1; i++)
		{
			registerFluidContainerHelper(fluid, /*mB - (i*50)*/50, new ItemStack(drink,1,minDrinkDamage + i),  new ItemStack(drink,1,minDrinkDamage + i+1));
		}
	}
}
