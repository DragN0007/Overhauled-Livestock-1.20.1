package com.dragn0007.dragnlivestock.util;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class LOUtils {
    public static Random random = new Random();
    public static final Map<String, ResourceLocation> TEXTURE_CACHE = new HashMap<>();

    public static ResourceLocation getRandomClientCoat(String folderName) {
        PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
        List<ResourceLocation> allTextures = new ArrayList<>();
        for(Pack pack: packRepository.getSelectedPacks()) {
            if(pack.getId().equals("vanilla") || pack.getId().equals("mod_resources")) continue;

            try(PackResources packResources = pack.open()) {
                Set<String> namespaces = packResources.getNamespaces(PackType.CLIENT_RESOURCES);
                for(String namespace : namespaces) {
                    packResources.listResources(PackType.CLIENT_RESOURCES, namespace, folderName, (resourceLocation, inputStreamIoSupplier) -> {
                        if(resourceLocation.toString().endsWith(".png")) allTextures.add(resourceLocation);
                    });
                }
            } catch (Exception e) {
                LivestockOverhaul.LOGGER.error("Failed to randomly get a client coat from: " + folderName);
                return MissingTextureAtlasSprite.getLocation();
            }
        } if(allTextures.isEmpty()) {
            LivestockOverhaul.LOGGER.error("Failed to randomly get a client coat from: " + folderName + ", nothing found in folder.");
            return MissingTextureAtlasSprite.getLocation();
        }
        return allTextures.get(random.nextInt(allTextures.size()));
    }

    public static ResourceLocation getRandomClientPattern(String folderName) {
        PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
        List<ResourceLocation> allTextures = new ArrayList<>();
        for(Pack pack: packRepository.getSelectedPacks()) {
            if(pack.getId().equals("vanilla") || pack.getId().equals("mod_resources")) continue;

            try(PackResources packResources = pack.open()) {
                Set<String> namespaces = packResources.getNamespaces(PackType.CLIENT_RESOURCES);
                for(String namespace : namespaces) {
                    packResources.listResources(PackType.CLIENT_RESOURCES, namespace, folderName, (resourceLocation, inputStreamIoSupplier) -> {
                        if(resourceLocation.toString().endsWith(".png")) allTextures.add(resourceLocation);
                    });
                }
            } catch (Exception e) {
                LivestockOverhaul.LOGGER.error("Failed to randomly get a client coat from: " + folderName);
                return MissingTextureAtlasSprite.getLocation();
            }
        } if(allTextures.isEmpty()) {
            LivestockOverhaul.LOGGER.error("Failed to randomly get a client coat from: " + folderName + ", nothing found in folder.");
            return MissingTextureAtlasSprite.getLocation();
        }
        return allTextures.get(random.nextInt(allTextures.size()));
    }

    public static ResourceLocation getRandomServerCoat(String folderName) {
        PackRepository packRepository = Minecraft.getInstance().getResourcePackRepository();
        List<ResourceLocation> allTextures = new ArrayList<>();
        for(Pack pack : packRepository.getSelectedPacks()) {
            if(pack.getPackSource() != PackSource.SERVER) continue;

            try(PackResources packResources = pack.open()) {
                Set<String> namespaces = packResources.getNamespaces(PackType.CLIENT_RESOURCES);
                for(String namespace : namespaces) {
                    packResources.listResources(PackType.CLIENT_RESOURCES, namespace, folderName, (resourceLocation, inputStreamIoSupplier) -> {
                        if(resourceLocation.toString().endsWith(".png")) allTextures.add(resourceLocation);
                    });
                }
            } catch (Exception e) {return MissingTextureAtlasSprite.getLocation();}
        } if(allTextures.isEmpty()) return MissingTextureAtlasSprite.getLocation();
        return allTextures.get(random.nextInt(allTextures.size()));
    }

    public static boolean entityIsHidden(Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
        Vec3 start = mc.player.getEyePosition(1.0F);
        Vec3 end = entity.getEyePosition(1.0F);

        BlockHitResult result = entity.level().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                entity
        ));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = result.getBlockPos();
            BlockState hitState = entity.level().getBlockState(hitPos);
            if (hitState.getBlock().defaultBlockState().isSolid() ||
                    hitState.getBlock() instanceof FenceBlock ||
                    hitState.getBlock() instanceof FenceGateBlock ||
                    hitState.getBlock() instanceof IronBarsBlock ||
                    hitState.getBlock() instanceof HalfTransparentBlock ||
                    hitState.getBlock() instanceof LeavesBlock ||
                    hitState.getBlock() instanceof StainedGlassBlock) {
                return false;
            }
            return true;
        }
        return false;
    }

}
