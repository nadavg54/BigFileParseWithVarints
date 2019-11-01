package blocks;

import hiddenmsgreader.blocks.BlockFactory;
import hiddenmsgreader.blocks.MessageBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlockFactoryTest {

  @Test
  public void test(){
    BlockFactory blockFactory = new BlockFactory();

    byte[] bytes = new byte[8];
    bytes[0] = 8;
    //1010 1100 0000 0010
    bytes[1] = (byte)0b10101100;
    bytes[2] = (byte)0b00000010;
    bytes[3] = 1;
    bytes[4] = 0;
    bytes[5] = 1;
    bytes[6] = 1;
    bytes[7] = 1;


    MessageBlock block = blockFactory.createBlock(bytes, 0);
    assertEquals(block.getLength(),8);
    List<Integer> expectedPointers = new ArrayList<>();
    expectedPointers.add(300);
    expectedPointers.add(1);
    assertArrayEquals(block.getPointers().toArray(),expectedPointers.toArray());
    //assertEquals(block.getPayloadEnd(),7);
  }
}
