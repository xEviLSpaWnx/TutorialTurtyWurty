package com.evilspawn.tutorialmod.init;

import com.evilspawn.tutorialmod.TutorialMod;
import com.evilspawn.tutorialmod.objects.blocks.SpecalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TutorialMod.MOD_ID)

public class BlockInit {
    public static final Block example_block = null;
    public static final Block test_block = null;
    public static final Block specal_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(
                new Block(Block.Properties.create(Material.IRON)
                        .hardnessAndResistance(3.0f, 4.0f)
                        .sound(SoundType.METAL)
                        .harvestLevel(2)
                        .harvestTool(ToolType.PICKAXE))
                        .setRegistryName("example_block"));

        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0f, 4.0f).sound(SoundType.METAL)).setRegistryName("test_block"));

        event.getRegistry().register(new SpecalBlock(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(2.0f, 10.0f)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.GLASS)
                .lightValue(4)
                .slipperiness(1.2f)
                .speedFactor(0.7f)
                .noDrops())
                .setRegistryName("specal_block"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_block"));
        event.getRegistry().register(new BlockItem(test_block, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("test_block"));
        event.getRegistry().register(new BlockItem(specal_block, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("specal_block"));
    }
}