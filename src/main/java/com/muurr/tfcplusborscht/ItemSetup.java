package com.muurr.tfcplusborscht;

import com.dunk.tfc.Core.FluidBaseTFC;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.Items.Tools.ItemCustomBucketMilk;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Crafting.BarrelFireRecipe;
import com.dunk.tfc.api.Crafting.BarrelManager;
import com.dunk.tfc.api.Enums.EnumFoodGroup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSetup extends TFCItems{
	
	//Bouillons
	public static Item chickenbouillon;
	public static Item chickenbouillonbowl;
	public static final Fluid CHICKENBOUILLON = new FluidBaseTFC("chickenbouillon").setBaseColor(0xfffb92);
	
	public static Item meatbouillon;
	public static Item meatbouillonbowl;
	public static final Fluid MEATBOUILLON = new FluidBaseTFC("meatbouillon").setBaseColor(0xffd391);
	
	public static Item fishbouillon;
	public static Item fishbouillonbowl;
	public static final Fluid FISHBOUILLON = new FluidBaseTFC("fishbouillon").setBaseColor(0xfcffe8);
	
	public static Item mushroombouillon;
	public static Item mushroombouillonbowl;
	public static final Fluid MUSHROOMBOUILLON = new FluidBaseTFC("mushroombouillon").setBaseColor(0x87825f);
	
	public static Item bonebouillon;
	public static Item bonebouillonbowl;
	public static final Fluid BONEBOUILLON = new FluidBaseTFC("bonebouillon").setBaseColor(0xfffbe2);
		
	//Soups
	public static Item chickensoup;
	public static Item chickensoupbowl;
	public static final Fluid CHICKENSOUP = new FluidBaseTFC("chickensoup").setBaseColor(0xfef02c);
	
	public static Item potatosoup;
	public static Item potatosoupbowl;
	public static final Fluid POTATOSOUP = new FluidBaseTFC("potatosoup").setBaseColor(0xded988);
	
	public static Item cabbagesoup;
	public static Item cabbagesoupbowl;
	public static final Fluid CABBAGESOUP = new FluidBaseTFC("cabbagesoup").setBaseColor(0xe79863);

	public static Item borscht;
	public static Item borschtbowl;
	public static final Fluid BORSCHT = new FluidBaseTFC("borscht").setBaseColor(0x8c2c24);
	
	public static Item fishsoup;
	public static Item fishsoupbowl;
	public static final Fluid FISHSOUP = new FluidBaseTFC("fishsoup").setBaseColor(0xeeec82);
	
	public static Item mushroomsoup;
	public static Item mushroomsoupbowl;
	public static final Fluid MUSHROOMSOUP = new FluidBaseTFC("mushroomsoup").setBaseColor(0x8f7341);
	
	public static Item peasantsoup;
	public static Item peasantsoupbowl;
	public static final Fluid PEASANTSOUP = new FluidBaseTFC("peasantsoup").setBaseColor(0xf6d8a6);
	
	public static Item okroshka;
	public static Item okroshkabowl;
	public static final Fluid OKROSHKA = new FluidBaseTFC("okroshka").setBaseColor(0xeffadc);
	
	public static Item cheesesoup;
	public static Item cheesesoupbowl;
	public static final Fluid CHEESESOUP = new FluidBaseTFC("cheesesoup").setBaseColor(0xeeed72);
	
	public static Item milksoup;
	public static Item milksoupbowl;
	public static final Fluid MILKSOUP = new FluidBaseTFC("milksoup").setBaseColor(0xefefe8);

	public static void setup()
	{
		//Bouillons
		chickenbouillon = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Chicken Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		chickenbouillonbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Chicken Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) chickenbouillon);
		drinks.add((ProperItemDrink) chickenbouillonbowl);

		meatbouillon = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Meat Boillon").setCreativeTab(TFCTabs.TFC_FOODS);
		meatbouillonbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Meat Boillon").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) meatbouillon);
		drinks.add((ProperItemDrink) meatbouillonbowl);

		fishbouillon  = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Fish Boillon").setCreativeTab(TFCTabs.TFC_FOODS);
		fishbouillonbowl  = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Fish Boillon").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) fishbouillon);
		drinks.add((ProperItemDrink) fishbouillonbowl);

		mushroombouillon = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Mushroom Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		mushroombouillonbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Mushroom Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) mushroombouillon);
		drinks.add((ProperItemDrink) mushroombouillonbowl);

		bonebouillon = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(0.750f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bone Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		bonebouillonbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(0.750f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Bone Bouillon").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) bonebouillon);
		drinks.add((ProperItemDrink) bonebouillonbowl);
		
		//Soups
		chickensoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(2400).setUnlocalizedName("Chicken Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		chickensoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(2400).setUnlocalizedName("Bowl of Chicken Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) chickensoup);
		drinks.add((ProperItemDrink) chickensoupbowl);

		potatosoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Potato Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		potatosoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Potato Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) potatosoup);
		drinks.add((ProperItemDrink) potatosoupbowl);

		cabbagesoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Cabbage Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		cabbagesoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Cabbage Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) cabbagesoup);
		drinks.add((ProperItemDrink) cabbagesoupbowl);

		borscht = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(2f).setWaterRestoreRatio(0.500f).setHeatProtection(0).setColdProtection(3600).setUnlocalizedName("Borscht").setCreativeTab(TFCTabs.TFC_FOODS);
		borschtbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(2f).setWaterRestoreRatio(0.500f).setHeatProtection(0).setColdProtection(3600).setUnlocalizedName("Bowl of Borscht").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) borscht);
		drinks.add((ProperItemDrink) borschtbowl);

		fishsoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Fish Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		fishsoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Fish Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) fishsoup);
		drinks.add((ProperItemDrink) fishsoupbowl);

		mushroomsoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Mushroom Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		mushroomsoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Protein).setCalories(1.5f).setWaterRestoreRatio(0.750f).setHeatProtection(0).setColdProtection(1800).setUnlocalizedName("Bowl of Mushroom Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) mushroomsoup);
		drinks.add((ProperItemDrink) mushroomsoupbowl);

		peasantsoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(900).setUnlocalizedName("Peasant Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		peasantsoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Vegetable).setCalories(1.250f).setWaterRestoreRatio(1f).setHeatProtection(0).setColdProtection(900).setUnlocalizedName("Bowl of Peasant Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) peasantsoup);
		drinks.add((ProperItemDrink) peasantsoupbowl);

		okroshka = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(3600).setColdProtection(0).setUnlocalizedName("Okroshka").setCreativeTab(TFCTabs.TFC_FOODS);
		okroshkabowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(3600).setColdProtection(0).setUnlocalizedName("Bowl of Okroshka").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) okroshka);
		drinks.add((ProperItemDrink) okroshkabowl);

		cheesesoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(900).setColdProtection(900).setUnlocalizedName("Cheese Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		cheesesoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(900).setColdProtection(900).setUnlocalizedName("Bowl of Cheese Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) cheesesoup);
		drinks.add((ProperItemDrink) cheesesoupbowl);

		milksoup = new ProperItemDrink(1000).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(1800).setColdProtection(0).setUnlocalizedName("Milk Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		milksoupbowl = new ProperItemDrink(500, 3).setFoodGroup(EnumFoodGroup.Dairy).setCalories(1.5f).setWaterRestoreRatio(0.500f).setHeatProtection(1800).setColdProtection(0).setUnlocalizedName("Bowl of Milk Soup").setCreativeTab(TFCTabs.TFC_FOODS);
		drinks.add((ProperItemDrink) milksoup);
		drinks.add((ProperItemDrink) milksoupbowl);
	}
	public static void setuprecipes()
	{
		//Bouillons
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.chickenRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(CHICKENBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.beefRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MEATBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.porkchopRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MEATBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.muttonRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MEATBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.venisonRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MEATBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.horseMeatRaw), 60), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MEATBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.fishRaw), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(FISHBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.calamariRaw), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(FISHBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.scallopRaw), 15), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(FISHBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.seastarRaw), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(FISHBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.mushroomFoodB), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MUSHROOMBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.mushroomFoodR), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(MUSHROOMBOUILLON, 750), 0, 250));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(new ItemStack(TFCItems.boneFragment, 2, 0), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(BONEBOUILLON, 500), 0, 250));

		//Soups
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 15), new FluidStack(CHICKENBOUILLON, 500), null, new FluidStack(CHICKENSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 15), new FluidStack(MEATBOUILLON, 500), null, new FluidStack(POTATOSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cabbage), 15), new FluidStack(MEATBOUILLON, 500), null, new FluidStack(CABBAGESOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(new ItemStack(TFCItems.madderRoot, 5, 0), new FluidStack(CABBAGESOUP, 500), null, new FluidStack(BORSCHT, 1500), 0, 750));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 15), new FluidStack(FISHBOUILLON, 500), null, new FluidStack(FISHSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 15), new FluidStack(MUSHROOMBOUILLON, 500), null, new FluidStack(MUSHROOMSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cabbage), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.carrot), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.onion), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.maizeEar), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.blackEyedPeas), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.garlic), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.greenbeans), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.greenBellPepper), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.redBellPepper), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.yellowBellPepper), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.squash), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.pumpkinGuts), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.tomato), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.weldRoot), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.madderRoot), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.woadLeaves), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.soybean), 15), new FluidStack(BONEBOUILLON, 500), null, new FluidStack(PEASANTSOUP, 750), 0, 500));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.eggCooked), 30), new FluidStack(TFCFluids.MILKCURDLED, 500), null, new FluidStack(OKROSHKA, 750), 0, 100));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese), 30), new FluidStack(TFCFluids.SALTWATER, 500), null, new FluidStack(CHEESESOUP, 750), 0, 150));
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.riceGrain), 30), new FluidStack(TFCFluids.MILK, 500), null, new FluidStack(MILKSOUP, 750), 0, 50));
		
		//Salt
		BarrelManager.getInstance().addRecipe(new ProperBarrelFireRecipe(new ItemStack(TFCItems.powder, 5, 9), new FluidStack(TFCFluids.FRESHWATER, 500), null, new FluidStack(TFCFluids.SALTWATER, 500), 0, 50));
	}
}
