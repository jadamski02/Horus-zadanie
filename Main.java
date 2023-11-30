import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Block b1 = new SmallBlock("red", "clay");
        Block b2 = new SmallBlock("red", "clay");
        Block b3 = new SmallBlock("white", "clay");
        Block b4 = new SmallBlock("white", "stone");
        Block b5 = new SmallBlock("gray", "stone");

        Block b6 = new SmallBlock("yellow", "sandstone");
        Block b7 = new SmallBlock("yellow", "sandstone");
        Block b8 = new SmallBlock("yellow", "sandstone");

        CompositeBlock cb1 = new CompositeBlockImpl(Arrays.asList(b6, b7, b8));

        List<Block> blocks = new ArrayList<>();
        blocks.add(b1);
        blocks.add(b2);
        blocks.add(b3);
        blocks.add(b4);
        blocks.add(b5);

        Wall w1 = new Wall(blocks);
        List<Block> blocks2 = new ArrayList<>(blocks);
        blocks2.addAll(cb1.getBlocks());
        Wall w2 = new Wall(blocks2);

        Optional<Block> redBlock = w1.findBlockByColor("red");
        System.out.println(redBlock.toString());

        List<Block> clayBlocks = w1.findBlocksByMaterial("clay");
        System.out.println(clayBlocks.toString());
        List<Block> stoneBlocks = w1.findBlocksByMaterial("stone");
        System.out.println(stoneBlocks.toString());

        System.out.println(w1.count());

        Optional<Block> redBlock2 = w2.findBlockByColor("yellow");
        System.out.println(redBlock.toString());

        List<Block> stoneBlocks2 = w2.findBlocksByMaterial("sandstone");
        System.out.println(stoneBlocks2.toString());
        System.out.println(w2.count());
    }

}

class SmallBlock implements Block {
    private String color;
    private String material;

    public SmallBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "SmallBlock{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}

class CompositeBlockImpl implements CompositeBlock {
    private List<Block> blocks;

    public CompositeBlockImpl(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String getColor() {
        if (!blocks.isEmpty()) {
            return blocks.get(0).getColor();
        }
        return "";
    }

    @Override
    public String getMaterial() {
        if (!blocks.isEmpty()) {
            return blocks.get(0).getMaterial();
        }
        return "";
    }
}
