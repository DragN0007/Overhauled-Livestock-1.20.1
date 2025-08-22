package com.dragn0007.dragnlivestock.common.entities.util;

import com.dragn0007.dragnlivestock.common.entities.ai.OFollowFlockLeaderGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractSchoolingOFish extends AbstractOFish {
    @Nullable
    private AbstractSchoolingOFish leader;
    private int schoolSize = 1;

    public AbstractSchoolingOFish(EntityType<? extends AbstractSchoolingOFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new OFollowFlockLeaderGoal(this));
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    public boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public AbstractSchoolingOFish startFollowing(AbstractSchoolingOFish p_27526_) {
        this.leader = p_27526_;
        p_27526_.addFollower();
        return p_27526_;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends AbstractOFish> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }

    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }

    }

    public void addFollowers(Stream<? extends AbstractSchoolingOFish> p_27534_) {
        p_27534_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((p_27536_) -> {
            p_27536_.startFollowing(this);
        });
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_27528_, DifficultyInstance p_27529_, MobSpawnType p_27530_, @Nullable SpawnGroupData groupData, @Nullable CompoundTag p_27532_) {
        super.finalizeSpawn(p_27528_, p_27529_, p_27530_, groupData, p_27532_);

        if (groupData == null) {
            groupData = new AgeableMob.AgeableMobGroupData(0.2F);
        }

        if (groupData instanceof AbstractSchoolingOFish.SchoolSpawnGroupData) {
            this.startFollowing(((AbstractSchoolingOFish.SchoolSpawnGroupData) groupData).leader);
        } else {
            groupData = new AbstractSchoolingOFish.SchoolSpawnGroupData(this);
        }

        return groupData;
    }

    public static class SchoolSpawnGroupData implements SpawnGroupData {
        public final AbstractSchoolingOFish leader;

        public SchoolSpawnGroupData(AbstractSchoolingOFish p_27553_) {
            this.leader = p_27553_;
        }
    }
}