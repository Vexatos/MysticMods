package MysticWorld.Client.FX;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LightCubeFX extends EntityFX {
	
	  public LightCubeFX(World world, double x, double y, double z)
	  {
	    super(world, x, y, z);
	  }

	  public LightCubeFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale){
		  super(world, x, y, z);
	      this.motionX *= 0.10000000149011612D;
	      this.motionY *= 0.10000000149011612D;
	      this.motionZ *= 0.10000000149011612D;
	      this.motionX += motionX;
	      this.motionY += motionY;
	      this.motionZ += motionZ;
		  this.particleRed = 1.0F;
		  this.particleGreen = 1.0F;
	      this.particleBlue = 1.0F;
	      this.particleScale =  scale;
	      this.noClip = true;
	      this.particleMaxAge = rand.nextInt(25);
	  }

	  public void onUpdate()
	  {
		  this.prevPosX = this.posX;
	      this.prevPosY = this.posY;
	      this.prevPosZ = this.posZ;

	      if (this.particleAge++ >= this.particleMaxAge)
	      {
	          this.setDead();
	      }

	      this.motionY += 0.004D;
	      this.moveEntity(this.motionX, this.motionY, this.motionZ);

	      if (this.posY == this.prevPosY)
	      {
	          this.motionX *= 1.1D;
	          this.motionZ *= 1.1D;
	      }

	      this.motionX *= 0.9599999785423279D;
	      this.motionY *= 0.9599999785423279D;
	      this.motionZ *= 0.9599999785423279D;

	      if (this.onGround)
	      {
	          this.motionX *= 0.699999988079071D;
	          this.motionZ *= 0.699999988079071D;
	      }
	  }

	  public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	  {
		  tessellator.draw();
		 
		  GL11.glPushMatrix();
		  GL11.glDepthMask(false);
		  GL11.glEnable(3042);
		  GL11.glBlendFunc(770, 1);
		  
		  FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation("MysticMods:textures" + "/misc/lightCubeFX.png"));

		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
		  
		  float scale = 0.1F * this.particleScale;
		  float xPos = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) par2 - interpPosX);
		  float yPos = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) par2 - interpPosY);
		  float zPos = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) par2 - interpPosZ);
		  
		  tessellator.startDrawingQuads();
		  tessellator.setBrightness(240);
		  
		  tessellator.setColorOpaque_F(this.particleRed, this.particleGreen, this.particleBlue);

		  tessellator.addVertexWithUV((double) (xPos - par3 * scale - par6 * scale), (double) (yPos - par4 * scale), (double) (zPos - par5 * scale - par7 * scale), 0D, 1D);
		  tessellator.addVertexWithUV((double) (xPos - par3 * scale + par6 * scale), (double) (yPos + par4 * scale), (double) (zPos - par5 * scale + par7 * scale), 1D, 1D);
		  tessellator.addVertexWithUV((double) (xPos + par3 * scale + par6 * scale), (double) (yPos + par4 * scale), (double) (zPos + par5 * scale + par7 * scale), 1D, 0D);
		  tessellator.addVertexWithUV((double) (xPos + par3 * scale - par6 * scale), (double) (yPos - par4 * scale), (double) (zPos + par5 * scale - par7 * scale), 0D, 0D);
		    
		  tessellator.draw();

		  GL11.glDisable(3042);
		  GL11.glDepthMask(true);
		  GL11.glPopMatrix();

		  FMLClientHandler.instance().getClient().renderEngine.func_110577_a(new ResourceLocation("minecraft:textures" + "/particle/particles.png"));
		  tessellator.startDrawingQuads();
	  }
}
