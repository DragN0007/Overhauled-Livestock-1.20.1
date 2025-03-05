package com.dragn0007.dragnlivestock.entities.villager;

//import com.dragn0007.dragnlivestock.entities.ai.FollowTraderGoal;
//import com.dragn0007.dragnlivestock.entities.mule.OMule;
//import com.dragn0007.dragnlivestock.entities.mule.OMuleMarkingLayer;
//import com.dragn0007.dragnlivestock.entities.mule.OMuleModel;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.minecraft.world.entity.ai.goal.PanicGoal;
//import net.minecraft.world.entity.ai.goal.target.TargetGoal;
//import net.minecraft.world.entity.ai.targeting.TargetingConditions;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.ServerLevelAccessor;
//
//import javax.annotation.Nullable;
//import java.util.EnumSet;
//import java.util.Random;
//
//public class TraderMule extends OMule {
//    private int despawnDelay = 47999;
//
//    public TraderMule(EntityType<? extends OMule> p_30939_, Level p_30940_) {
//        super(p_30939_, p_30940_);
//    }
//
//    public void addAdditionalSaveData(CompoundTag p_30950_) {
//        super.addAdditionalSaveData(p_30950_);
//        p_30950_.putInt("DespawnDelay", this.despawnDelay);
//    }
//
//    public void readAdditionalSaveData(CompoundTag p_30948_) {
//        super.readAdditionalSaveData(p_30948_);
//        if (p_30948_.contains("DespawnDelay", 99)) {
//            this.despawnDelay = p_30948_.getInt("DespawnDelay");
//        }
//
//    }
//
//    public void registerGoals() {
//        super.registerGoals();
//        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
//        this.targetSelector.addGoal(1, new TraderMule.TraderMuleDefendLivestockTraderGoal(this));
//        this.targetSelector.addGoal(0, new FollowTraderGoal(this, 1.1D, 4.0F, 10.0F));
//    }
//
//    protected void doPlayerRide(Player p_30958_) {
//        Entity entity = this.getLeashHolder();
//        if (!(entity instanceof LivestockTrader)) {
//            super.doPlayerRide(p_30958_);
//        }
//    }
//
//    public void aiStep() {
//        super.aiStep();
//        if (!this.level().isClientSide) {
//            this.maybeDespawn();
//        }
//
//    }
//
//    private void maybeDespawn() {
//        if (this.canDespawn()) {
//            this.despawnDelay = this.isLeashedToLivestockTrader() ? ((LivestockTrader)this.getLeashHolder()).getDespawnDelay() - 1 : this.despawnDelay - 1;
//            if (this.despawnDelay <= 0) {
//                this.dropLeash(true, false);
//                this.discard();
//            }
//
//        }
//    }
//
//    private boolean canDespawn() {
//        return !this.isTamed() && !this.isLeashedToSomethingOtherThanTheLivestockTrader() && !this.hasExactlyOnePlayerPassenger();
//    }
//
//    private boolean isLeashedToLivestockTrader() {
//        return this.getLeashHolder() instanceof LivestockTrader;
//    }
//
//    private boolean isLeashedToSomethingOtherThanTheLivestockTrader() {
//        return this.isLeashed() && !this.isLeashedToLivestockTrader();
//    }
//
//    @Nullable
//    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
//        if (type == MobSpawnType.EVENT) {
//            this.setAge(0);
//        }
//
//        if (data == null) {
//            data = new AgeableMob.AgeableMobGroupData(false);
//        }
//
//        Random random = new Random();
//        this.setBreed(2);
//        this.setChest(true);
//        this.setTamed(true);
//        this.setItemSlot(EquipmentSlot.CHEST, Items.CYAN_CARPET.getDefaultInstance());
//
//        this.setVariant(random.nextInt(OMuleModel.Variant.values().length));
//        this.setOverlayVariant(random.nextInt(OMuleMarkingLayer.Overlay.values().length));
//        this.setGender(random.nextInt(Gender.values().length));
//
//        return super.finalizeSpawn(levelAccessor, difficultyInstance, type, data, tag);
//    }
//
//    protected static class TraderMuleDefendLivestockTraderGoal extends TargetGoal {
//        private final OMule oMule;
//        private LivingEntity ownerLastHurtBy;
//        private int timestamp;
//
//        public TraderMuleDefendLivestockTraderGoal(OMule oMule) {
//            super(oMule, false);
//            this.oMule = oMule;
//            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
//        }
//
//        public boolean canUse() {
//            if (!this.oMule.isLeashed()) {
//                return false;
//            } else {
//                Entity entity = this.oMule.getLeashHolder();
//                if (!(entity instanceof LivestockTrader)) {
//                    return false;
//                } else {
//                    LivestockTrader wanderingtrader = (LivestockTrader)entity;
//                    this.ownerLastHurtBy = wanderingtrader.getLastHurtByMob();
//                    int i = wanderingtrader.getLastHurtByMobTimestamp();
//                    return i != this.timestamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT);
//                }
//            }
//        }
//
//        public void start() {
//            this.mob.setTarget(this.ownerLastHurtBy);
//            Entity entity = this.oMule.getLeashHolder();
//            if (entity instanceof LivestockTrader) {
//                this.timestamp = ((LivestockTrader)entity).getLastHurtByMobTimestamp();
//            }
//
//            super.start();
//        }
//    }
//}