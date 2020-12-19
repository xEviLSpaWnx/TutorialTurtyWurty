package com.evilspawn.tutorialmod.init;

import com.evilspawn.tutorialmod.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TutorialMod.MOD_ID)

public class BlockInit {
    public static final Block example_block = null;
    public static final Block test_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0f, 4.0f).sound(SoundType.METAL)).setRegistryName("example_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0f, 4.0f).sound(SoundType.METAL)).setRegistryName("test_block"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_block"));
        event.getRegistry().register(new BlockItem(test_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("test_block"));
    }
}