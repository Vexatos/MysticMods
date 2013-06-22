package MysticWorld.Items;

import MysticWorld.MysticWorld;
import MysticWorld.Entity.EntityChargeAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemOrbAir extends Item {

	public ItemOrbAir(int par1) 
	{
		super(par1);
        this.setMaxStackSize(1);
        this.setMaxDamage(300);
		this.setCreativeTab(MysticWorld.MysticWorldTab);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		
		if (entityPlayer.onGround)
			entityPlayer.addVelocity(0, 2.5, 0);
		else
			entityPlayer.addVelocity(0, 0.5, 0);
		
        if (!world.isRemote)
        {    	
            itemStack.damageItem(1, entityPlayer);
        }
        
        return itemStack;
	}
	
}