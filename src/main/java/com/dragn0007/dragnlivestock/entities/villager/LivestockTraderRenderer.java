package com.dragn0007.dragnlivestock.entities.villager;
//
//import com.dragn0007.dragnlivestock.LivestockOverhaul;
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.model.VillagerModel;
//import net.minecraft.client.model.geom.ModelLayers;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
//import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.npc.WanderingTrader;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class LivestockTraderRenderer extends MobRenderer<WanderingTrader, VillagerModel<WanderingTrader>> {
//   private static final ResourceLocation VILLAGER_BASE_SKIN = new ResourceLocation(LivestockOverhaul.MODID, "textures/entity/villager/profession/livestock_trader.png");
//
//   //this shit so hacky even god turns his gaze from it
//
//   public LivestockTraderRenderer(EntityRendererProvider.Context p_174441_) {
//      super(p_174441_, new VillagerModel<>(p_174441_.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
//      this.addLayer(new CustomHeadLayer<>(this, p_174441_.getModelSet(), p_174441_.getItemInHandRenderer()));
//      this.addLayer(new CrossedArmsItemLayer<>(this, p_174441_.getItemInHandRenderer()));
//   }
//
//   public ResourceLocation getTextureLocation(WanderingTrader p_116373_) {
//      return VILLAGER_BASE_SKIN;
//   }
//
//   protected void scale(WanderingTrader p_116375_, PoseStack p_116376_, float p_116377_) {
//      float f = 0.9375F;
//      p_116376_.scale(0.9375F, 0.9375F, 0.9375F);
//   }
//}