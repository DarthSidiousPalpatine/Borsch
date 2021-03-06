package com.muurr.tfcplusborscht.compatibility;

import com.dunk.tfc.Core.FluidBaseTFC;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Crafting.BarrelManager;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.muurr.tfcplusborscht.ItemSetup;
import com.muurr.tfcplusborscht.ProperBarrelFireRecipe;
import com.muurr.tfcplusborscht.ProperItemDrink;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CompatItemSetup extends TFCItems{
	
	//Soups
	public static Item kvassokroshka;
	public static Item kvassokroshkabowl;
	public static final Fluid KVASSOKROSHKA = new FluidBaseTFC("kvassokroshka").setBaseColor(0x4c3838);
	
	public static Item zhur;
	public static Item zhurbowl;
	public static final Fluid ZHUR = new FluidBaseTFC("zhur").setBaseColor(0x9f8b6a);
	
	public static void setup()
	{
		//Soups
		kvassokroshka = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Grain).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(4800).setColdProtection(0).setUnlocalizedName("Kvass Okroshka").setCreativeTab(TFCTabs.TFC_FOODS);
		kvassokroshkabowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Grain).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(4800).setColdProtection(0).setUnlocalizedName("Bowl of Kvass Okroshka").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) kvassokroshka);
		drinks.add((ProperItemDrink) kvassokroshkabowl);
		
		zhur = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Grain).setCalories(2f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(3600).setUnlocalizedName("Zhur").setCreativeTab(TFCTabs.TFC_FOODS);
		zhurbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Grain).setCalories(2f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(3600).setUnlocalizedName("Bowl of Zhur").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) zhur);
		drinks.add((ProperItemDrink) zhurbowl);
	}
	
	public static void setuprecipes()
	{
		//Soups
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.eggCooked), 30), new FluidStack(com.muurr.tfcpluskvass.ItemSetup.KVASS, 500), null, new FluidStack(KVASSOKROSHKA, 750), 0, 100));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 30), new FluidStack(com.muurr.tfcpluskvass.ItemSetup.KISSEL, 500), null, new FluidStack(ZHUR, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 30), new FluidStack(com.muurr.tfcpluskvass.ItemSetup.FRUITKISSEL, 500), null, new FluidStack(ZHUR, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 30), new FluidStack(com.muurr.tfcpluskvass.ItemSetup.BERRYKISSEL, 500), null, new FluidStack(ZHUR, 750), 0, 250));
	}
}