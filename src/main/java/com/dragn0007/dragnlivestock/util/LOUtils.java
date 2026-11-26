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
                        allTextures.add(resourceLocation);
                    });
                }
            } catch (Exception e) {
                LivestockOverhaul.LOGGER.error("Failed to randomly get server coat from: " + folderName);
                return MissingTextureAtlasSprite.getLocation();
            }
        }
        if(allTextures.isEmpty()) {
            LivestockOverhaul.LOGGER.error("Failed to randomly get a server coat from: " + folderName);
            return MissingTextureAtlasSprite.getLocation();
        }
        return allTextures.get(random.nextInt(allTextures.size()));
    }

    public static boolean entityIsHidden(Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
        Vec3 start = mc.player.getEyePosition(1.0F);
        Vec3 end = entity.getEyePosition(1.0F);
        BlockHitResult result = entity.level().clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = result.getBlockPos();
            BlockState hitState = entity.level().getBlockState(hitPos);
            return !hitState.getBlock().defaultBlockState().isSolid() &&
                    !(hitState.getBlock() instanceof FenceBlock) &&
                    !(hitState.getBlock() instanceof FenceGateBlock) &&
                    !(hitState.getBlock() instanceof IronBarsBlock) &&
                    !(hitState.getBlock() instanceof HalfTransparentBlock) &&
                    !(hitState.getBlock() instanceof LeavesBlock) &&
                    !(hitState.getBlock() instanceof StainedGlassBlock);
        }
        return false;
    }

    public static String getHorseBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Mustang";
            case 1 -> "Ardennes";
            case 2 -> "Kladruber";
            case 3 -> "Fjord";
            case 4 -> "Thoroughbred";
            case 5 -> "Friesian";
            case 6 -> "Irish Cob";
            case 7 -> "American Quarter";
            case 8 -> "Percheron";
            case 9 -> "Selle Francais";
            case 10 -> "Marwari";
            case 11 -> "Mongolian";
            case 12 -> "Shire";
            case 13 -> "Akhal-Teke";
            case 14 -> "American Soldier";
            case 15 -> "Welsh Pony";
            case 16 -> "Connemara";
            case 17 -> "Haflinger";
            case 18 -> "Oldenburger";
            case 19 -> "Shetland";
            case 20 -> "Standardbred";
            case 21 -> "Trakehner";
            case 22 -> "Boulonnais";
            case 23 -> "Lusitano";
            case 24 -> "Nokota";
            case 25 -> "Dutch Warmblood";
            case 26 -> "Suffolk Punch";
            case 27 -> "Vanner";
            case 28 -> "American Paint";
            case 29 -> "Appaloosa";
            default -> "Unknown";
        };
    }

    public static String getMuleBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Stock";
            case 1 -> "Mini";
            case 2 -> "Draft";
            default -> "Unknown";
        };
    }

    public static String getCowBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Angus";
            case 1 -> "Longhorn";
            case 2 -> "Brahman";
            case 3 -> "Mini";
            case 4 -> "Watusi";
            case 5 -> "Corriente";
            case 6 -> "Holstein";
            case 7 -> "Jersey";
            case 8 -> "Hereford";
            case 9 -> "Highland";
            case 10 -> "Ox";
            case 11 -> "Spanish Fighting";
            default -> "Unknown";
        };
    }

    public static String getCowHorns(int horns) {
        return switch (horns) {
            case 0 -> "Polled";
            case 1, 8 -> "Medium";
            case 2, 4, 3 -> "Long";
            case 5, 6 -> "Thick";
            case 7, 10 -> "Small";
            case 9 -> "Zebu";
            default -> "Unknown";
        };
    }

    public static String getPigBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Yorkshire";
            case 1 -> "Pot-Belly";
            case 2 -> "Guinea Hog";
            case 3 -> "Kunekune";
            case 4 -> "Poland-China";
            case 5 -> "Berkshire";
            default -> "Unknown";
        };
    }

    public static String getSheepBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Gulf Coast";
            case 1 -> "Norfolk";
            case 2 -> "Dorset";
            case 3 -> "Jacob";
            case 4 -> "Racka";
            case 5 -> "California Red";
            case 6 -> "Hair";
            case 7 -> "Border Leicester";
            case 8 -> "Fat-Tailed";
            default -> "Unknown";
        };
    }

    public static String getLlamaBreeds(int breed) {
        return switch (breed) {
            case 0 -> "Flocculent";
            case 1 -> "Woolly";
            default -> "Unknown";
        };
    }

    public static String getGoatBreeds(int breed) {
        switch (breed) {
            case 0: return "Classic";
            case 1: return "Meat";
            case 2: return "Nubian";
            case 3: return "Warm";
            case 4: return "Fibrous";
            case 5: return "Dairy";
            default: return "Unknown";
        }
    }
}
