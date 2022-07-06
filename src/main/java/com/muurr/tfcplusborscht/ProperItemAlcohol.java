package com.muurr.tfcplusborscht;

import java.util.Random;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Core.Player.FoodStatsTFC;
import com.dunk.tfc.Items.ItemAlcohol;
import com.dunk.tfc.Items.ItemDrink;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ProperItemAlcohol extends ProperItemDrink
{
	float alcoholContent = 0;
	public ProperItemAlcohol(float v)
	{
		super(v);
	}
	
	public ProperItemAlcohol(float v, int t)
	{
		super(v,t);
	}
	
	public float getAlcoholContent()
	{
		return alcoholContent;
	}
	
	protected ItemStack drinking(ItemStack is, World world, EntityPlayer player, int maxSips)
	{
		is = super.drinking(is, world, player, maxSips);
		if(maxSips == -1)
		{
			maxSips = (int) Math.min(2f / Math.max(0.000001f, alcoholContent), (volume/50));
		}
		if (!world.isRemote)
		{
			int deltaDrink = (int) volume;
			if (canDrinkInParts && is != null && is.hasTagCompound() && is.stackTagCompound.hasKey("WaterConsumed") && is.stackTagCompound.getInteger("WaterConsumed") < getScaledWaterRestore(is))
			{
				deltaDrink = Math.min(maxSips,(int) Math.max(1, (getMaxDamage(is) * ((float) is.stackTagCompound.getInteger("WaterConsumed") / (volume * 8f))))) * 50;
			}
			int sips = deltaDrink/50;
			if(sips == 0)
			{
				return is;
			}
			//Give experience
			if (tier > 0 && !world.isRemote)
			{
				float xpCount = (float)(tier * tier) * (0.5f * (float)sips);
				int i = (int)xpCount;

				while (i > 0)
				{
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					world.spawnEntityInWorld(new EntityXPOrb(world, player.posX, player.posY, player.posZ, j));
				}
			}
			Random rand = new Random();
			FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
			//fs.restoreWater(player, 800);
			int time = 400+rand.nextInt(1000);
			
			fs.consumeAlcohol(alcoholContent * ((float)deltaDrink/250f));
			if(rand.nextInt(500/sips)==0){
				player.addPotionEffect(new PotionEffect(8,time,4));
			}
			if(rand.nextInt(500/sips)==0){
				player.addPotionEffect(new PotionEffect(5,time,4));
			}
			if(rand.nextInt(500/sips)==0){
				player.addPotionEffect(new PotionEffect(8,time,4));
			}
			if(rand.nextInt(500/sips)==0){
				player.addPotionEffect(new PotionEffect(10,time,4));
			}
			if(rand.nextInt(500/sips)==0){
				player.addPotionEffect(new PotionEffect(12,time,4));
			}
			if(rand.nextInt(900/sips)==0){
				player.addPotionEffect(new PotionEffect(13,time,4));
			}
			int levelMod = 250*player.experienceLevel;
			if(fs.soberTime >TFC_Time.getTotalTicks()+6000+levelMod){
				/*if(rand.nextInt(4)==0){
					//player.addPotionEffect(new PotionEffect(9,time,4));
				}*/
				if(fs.soberTime >TFC_Time.getTotalTicks()+10000+levelMod){
					if(rand.nextInt(4)==0){
						player.addPotionEffect(new PotionEffect(18,time,4));
					}
					if(fs.soberTime >TFC_Time.getTotalTicks()+14000+levelMod){
						if(rand.nextInt(2)==0){
							player.addPotionEffect(new PotionEffect(15,time,4));
						}
						if(fs.soberTime >TFC_Time.getTotalTicks()+16000+levelMod){
							if(rand.nextInt(10)==0){
								player.addPotionEffect(new PotionEffect(20,time,4));
							}
						}
						if(fs.soberTime > TFC_Time.getTotalTicks()+24000+levelMod && !player.capabilities.isCreativeMode){
							fs.soberTime = 0;

							player.attackEntityFrom(new DamageSource("alcohol").setDamageBypassesArmor().setDamageIsAbsolute(), player.getMaxHealth() *1.1f);
						}
					}

				}
			}
			TFC_Core.setPlayerFoodStats(player, fs);
		}
		return is;
	}


	public ProperItemAlcohol setAlcoholContent(float f)
	{
		this.alcoholContent = f;
		return this;
	}
}
