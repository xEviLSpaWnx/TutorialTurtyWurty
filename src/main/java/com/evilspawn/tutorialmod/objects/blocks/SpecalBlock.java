package com.evilspawn.tutorialmod.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class SpecalBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6, 12.9, 7.200000000000001, 10, 15.9, 15.200000000000001),
            Block.makeCuboidShape(6, 7.9, 10.200000000000001, 10, 12.9, 15.200000000000001),
            Block.makeCuboidShape(1, 1, 2, 15, 2, 15),
            Block.makeCuboidShape(0, 0, 3, 1, 3, 4),
            Block.makeCuboidShape(0, 0, 13, 1, 3, 14),
            Block.makeCuboidShape(15, 0, 13, 16, 3, 14),
            Block.makeCuboidShape(15, 0, 3, 16, 3, 4),
            Block.makeCuboidShape(2, 2, 3, 14, 8, 15),
            Block.makeCuboidShape(1, 8, 2, 15, 9, 15),
            Block.makeCuboidShape(2, 9, 3, 14, 10, 15)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0.7999999999999989, 12.9, 6, 8.799999999999999, 15.9, 10),
            Block.makeCuboidShape(0.7999999999999989, 7.9, 6, 5.799999999999999, 12.9, 10),
            Block.makeCuboidShape(1, 1, 1, 14, 2, 15),
            Block.makeCuboidShape(12, 0, 0, 13, 3, 1),
            Block.makeCuboidShape(2, 0, 0, 3, 3, 1),
            Block.makeCuboidShape(2, 0, 15, 3, 3, 16),
            Block.makeCuboidShape(12, 0, 15, 13, 3, 16),
            Block.makeCuboidShape(1, 2, 2, 13, 8, 14),
            Block.makeCuboidShape(1, 8, 1, 14, 9, 15),
            Block.makeCuboidShape(1, 9, 2, 13, 10, 14)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6, 12.9, 0.7999999999999989, 10, 15.9, 8.799999999999999),
            Block.makeCuboidShape(6, 7.9, 0.7999999999999989, 10, 12.9, 5.799999999999999),
            Block.makeCuboidShape(1, 1, 1, 15, 2, 14),
            Block.makeCuboidShape(15, 0, 12, 16, 3, 13),
            Block.makeCuboidShape(15, 0, 2, 16, 3, 3),
            Block.makeCuboidShape(0, 0, 2, 1, 3, 3),
            Block.makeCuboidShape(0, 0, 12, 1, 3, 13),
            Block.makeCuboidShape(2, 2, 1, 14, 8, 13),
            Block.makeCuboidShape(1, 8, 1, 15, 9, 14),
            Block.makeCuboidShape(2, 9, 1, 14, 10, 13)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(7.200000000000001, 12.9, 6, 15.200000000000001, 15.9, 10),
            Block.makeCuboidShape(10.200000000000001, 7.9, 6, 15.200000000000001, 12.9, 10),
            Block.makeCuboidShape(2, 1, 1, 15, 2, 15),
            Block.makeCuboidShape(3, 0, 15, 4, 3, 16),
            Block.makeCuboidShape(13, 0, 15, 14, 3, 16),
            Block.makeCuboidShape(13, 0, 0, 14, 3, 1),
            Block.makeCuboidShape(3, 0, 0, 4, 3, 1),
            Block.makeCuboidShape(3, 2, 2, 15, 8, 14),
            Block.makeCuboidShape(2, 8, 1, 15, 9, 15),
            Block.makeCuboidShape(3, 9, 2, 15, 10, 14)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public SpecalBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult result) {
        if (!worldIn.isRemote()) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
            serverWorld.addLightningBolt(entity);
        }
        return ActionResultType.SUCCESS;
    }
}
