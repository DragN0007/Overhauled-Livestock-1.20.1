package com.dragn0007.dragnlivestock.entities.villager;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.blocks.LOBlocks;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.EnumSet;

import static com.dragn0007.dragnlivestock.LivestockOverhaul.MODID;

public class LivestockTrader extends WanderingTrader {
   @Nullable
   private BlockPos wanderTarget;
   private int despawnDelay;

   public LivestockTrader(EntityType<? extends LivestockTrader> p_35843_, Level p_35844_) {
      super(p_35843_, p_35844_);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY), SoundEvents.WANDERING_TRADER_DISAPPEARED, (p_289486_) -> {
         return this.level().isNight() && !p_289486_.isInvisible();
      }));
      this.goalSelector.addGoal(0, new UseItemGoal<>(this, new ItemStack(Items.MILK_BUCKET), SoundEvents.WANDERING_TRADER_REAPPEARED, (p_289487_) -> {
         return this.level().isDay() && p_289487_.isInvisible();
      }));
      this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5D, 0.5D));
      this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
      this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
      this.goalSelector.addGoal(2, new LivestockTrader.WanderToPositionGoal(this, 2.0D, 0.35D));
      this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35D));
      this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
   }

   @Nullable
   public AgeableMob getBreedOffspring(ServerLevel p_150046_, AgeableMob p_150047_) {
      return null;
   }

   public boolean showProgressBar() {
      return false;
   }

   public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) {
      ItemStack itemstack = p_35856_.getItemInHand(p_35857_);
      if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
         if (p_35857_ == InteractionHand.MAIN_HAND) {
            p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
         }

         if (this.getOffers().isEmpty()) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         } else {
            if (!this.level().isClientSide) {
               this.setTradingPlayer(p_35856_);
               this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }
      } else {
         return super.mobInteract(p_35856_, p_35857_);
      }
   }

   public static final DeferredRegister<PoiType> POI_TYPES
           = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);
   public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
           = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MODID);

   public static final RegistryObject<PoiType> LIVESTOCK_TRADER_POI = POI_TYPES.register("livestock_trader_poi",
           () -> new PoiType(ImmutableSet.copyOf(LOBlocks.BEEF_JERKY_HANGING.get().getStateDefinition().getPossibleStates()),
                   1, 1));

   public static final RegistryObject<VillagerProfession> LIVESTOCK_TRADER =
           VILLAGER_PROFESSIONS.register("livestock_trader", () -> new VillagerProfession("livestock_trader",
                   holder -> holder.get() == LIVESTOCK_TRADER_POI.get(), holder -> holder.get() == LIVESTOCK_TRADER_POI.get(),
                   ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));

   public static void register(IEventBus eventBus) {
      POI_TYPES.register(eventBus);
      VILLAGER_PROFESSIONS.register(eventBus);
   }

   static class ItemsForEmeralds implements VillagerTrades.ItemListing {
      private final ItemStack itemStack;
      private final int emeraldCost;
      private final int numberOfItems;
      private final int maxUses;
      private final int villagerXp;
      private final float priceMultiplier;

      public ItemsForEmeralds(Item item, int cost, int amount, int max, int xp) {
         this(new ItemStack(item), cost, amount, max, xp);
      }

      public ItemsForEmeralds(ItemStack itemStack, int cost, int amount, int max, int xp) {
         this(itemStack, cost, amount, max, xp, 0.05F);
      }

      public ItemsForEmeralds(ItemStack stack, int cost, int numOfItems, int maxUses, int villagerXP, float priceMultiplier) {
         this.itemStack = stack;

         this.emeraldCost = cost;
         this.numberOfItems = numOfItems;
         this.maxUses = maxUses;
         this.villagerXp = villagerXP;

         this.priceMultiplier = priceMultiplier;
      }

      public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
         return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
      }
   }

   public static final Int2ObjectMap<VillagerTrades.ItemListing[]> LIVESTOCK_TRADER_TRADES = toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
           new ItemsForEmeralds(LOItems.FERTILIZED_EGG.get(), 2, 3, 5, 1),
//           new ItemsForEmeralds(LOItems.FERTILIZED_AMERAUCANA_EGG.get(), 2, 3, 5, 1),
//           new ItemsForEmeralds(LOItems.FERTILIZED_CREAM_LEGBAR_EGG.get(), 2, 3, 5, 1),
//           new ItemsForEmeralds(LOItems.FERTILIZED_MARANS_EGG.get(), 2, 3, 5, 1),
//           new ItemsForEmeralds(LOItems.FERTILIZED_SUSSEX_SILKIE_EGG.get(), 2, 3, 5, 1),
//           new ItemsForEmeralds(LOItems.AYAM_CEMANI_EGG.get(), 4, 1, 2, 4),
//
//           new ItemsForEmeralds(LOItems.MANE_SCISSORS.get(), 1, 1, 5, 2),
//           new ItemsForEmeralds(LOItems.TAIL_SCISSORS.get(), 1, 1, 5, 2),
//
//           new ItemsForEmeralds(Items.SADDLE, 3, 1, 5, 2),
//           new ItemsForEmeralds(Items.LEATHER_HORSE_ARMOR, 2, 1, 5, 2),
//           new ItemsForEmeralds(LOItems.CHAINMAIL_HORSE_ARMOR.get(), 4, 1, 5, 2),
//           new ItemsForEmeralds(Items.IRON_HORSE_ARMOR, 5, 1, 5, 2),
//           new ItemsForEmeralds(Items.GOLDEN_HORSE_ARMOR, 7, 1, 5, 2),
//           new ItemsForEmeralds(Items.DIAMOND_HORSE_ARMOR, 9, 1, 5, 2),
//           new ItemsForEmeralds(LOItems.NETHERITE_HORSE_ARMOR.get(), 11, 1, 5, 2),
//
//           new ItemsForEmeralds(LOItems.BEEF_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.CHICKEN_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.PORK_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.MUTTON_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.FISH_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.GAME_JERKY.get(), 2, 16, 4, 1),
//           new ItemsForEmeralds(LOItems.GENERIC_JERKY.get(), 2, 16, 4, 1),
//
//           new ItemsForEmeralds(LOItems.CHEESE.get(), 2, 6, 5, 2),
//           new ItemsForEmeralds(LOItems.SHEEP_CHEESE.get(), 2, 6, 5, 2),
//           new ItemsForEmeralds(LOItems.LLAMA_CHEESE.get(), 2, 6, 5, 2),
//           new ItemsForEmeralds(LOItems.GOAT_CHEESE.get(), 2, 6, 5, 2),
//
//           new ItemsForEmeralds(LOItems.OVERWORLD_UNICORN_SPAWN_EGG.get(), 20, 1, 1, 4),
//           new ItemsForEmeralds(LOItems.NETHER_UNICORN_SPAWN_EGG.get(), 25, 1, 1, 4),
//           new ItemsForEmeralds(LOItems.END_UNICORN_SPAWN_EGG.get(), 30, 1, 1, 4),
   }));

   private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
      return new Int2ObjectOpenHashMap<>(p_35631_);
   }
   protected void updateTrades() {
      VillagerTrades.ItemListing[] avillagertrades$itemlisting = LIVESTOCK_TRADER_TRADES.get(1);
      VillagerTrades.ItemListing[] avillagertrades$itemlisting1 = LIVESTOCK_TRADER_TRADES.get(2);
      if (avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null) {
         MerchantOffers merchantoffers = this.getOffers();
         this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 5);
         int i = this.random.nextInt(avillagertrades$itemlisting1.length);
         VillagerTrades.ItemListing villagertrades$itemlisting = avillagertrades$itemlisting1[i];
         MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
         if (merchantoffer != null) {
            merchantoffers.add(merchantoffer);
         }

      }
   }

   public void addAdditionalSaveData(CompoundTag p_35861_) {
      super.addAdditionalSaveData(p_35861_);
      p_35861_.putInt("DespawnDelay", this.despawnDelay);
      if (this.wanderTarget != null) {
         p_35861_.put("WanderTarget", NbtUtils.writeBlockPos(this.wanderTarget));
      }

   }

   public void readAdditionalSaveData(CompoundTag p_35852_) {
      super.readAdditionalSaveData(p_35852_);
      if (p_35852_.contains("DespawnDelay", 99)) {
         this.despawnDelay = p_35852_.getInt("DespawnDelay");
      }

      if (p_35852_.contains("WanderTarget")) {
         this.wanderTarget = NbtUtils.readBlockPos(p_35852_.getCompound("WanderTarget"));
      }

      this.setAge(Math.max(0, this.getAge()));
   }

   public boolean removeWhenFarAway(double p_35886_) {
      return false;
   }

   protected void rewardTradeXp(MerchantOffer p_35859_) {
      if (p_35859_.shouldRewardExp()) {
         int i = 3 + this.random.nextInt(4);
         this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
      }

   }

   protected SoundEvent getAmbientSound() {
      return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource p_35870_) {
      return SoundEvents.WANDERING_TRADER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.WANDERING_TRADER_DEATH;
   }

   protected SoundEvent getDrinkingSound(ItemStack p_35865_) {
      return p_35865_.is(Items.MILK_BUCKET) ? SoundEvents.WANDERING_TRADER_DRINK_MILK : SoundEvents.WANDERING_TRADER_DRINK_POTION;
   }

   protected SoundEvent getTradeUpdatedSound(boolean p_35890_) {
      return p_35890_ ? SoundEvents.WANDERING_TRADER_YES : SoundEvents.WANDERING_TRADER_NO;
   }

   public SoundEvent getNotifyTradeSound() {
      return SoundEvents.WANDERING_TRADER_YES;
   }

   public void setDespawnDelay(int p_35892_) {
      this.despawnDelay = p_35892_;
   }

   public int getDespawnDelay() {
      return this.despawnDelay;
   }

   public void aiStep() {
      super.aiStep();
      if (!this.level().isClientSide) {
         this.maybeDespawn();
      }

   }

   private void maybeDespawn() {
      if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
         this.discard();
      }

   }

   public void setWanderTarget(@Nullable BlockPos p_35884_) {
      this.wanderTarget = p_35884_;
   }

   @Nullable
   BlockPos getWanderTarget() {
      return this.wanderTarget;
   }

   class WanderToPositionGoal extends Goal {
      final LivestockTrader trader;
      final double stopDistance;
      final double speedModifier;

      WanderToPositionGoal(LivestockTrader p_35899_, double p_35900_, double p_35901_) {
         this.trader = p_35899_;
         this.stopDistance = p_35900_;
         this.speedModifier = p_35901_;
         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
      }

      public void stop() {
         this.trader.setWanderTarget((BlockPos)null);
         LivestockTrader.this.navigation.stop();
      }

      public boolean canUse() {
         BlockPos blockpos = this.trader.getWanderTarget();
         return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
      }

      public void tick() {
         BlockPos blockpos = this.trader.getWanderTarget();
         if (blockpos != null && LivestockTrader.this.navigation.isDone()) {
            if (this.isTooFarAway(blockpos, 10.0D)) {
               Vec3 vec3 = (new Vec3((double)blockpos.getX() - this.trader.getX(), (double)blockpos.getY() - this.trader.getY(), (double)blockpos.getZ() - this.trader.getZ())).normalize();
               Vec3 vec31 = vec3.scale(10.0D).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
               LivestockTrader.this.navigation.moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            } else {
               LivestockTrader.this.navigation.moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speedModifier);
            }
         }

      }

      private boolean isTooFarAway(BlockPos p_35904_, double p_35905_) {
         return !p_35904_.closerToCenterThan(this.trader.position(), p_35905_);
      }
   }
}