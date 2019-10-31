package controlbufferreader.usememmarker;

import blocks.ByteRange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UsedMemMarkerOrderedTreeTest {

  @Test
  public void testBasic()
  {
    //[0-3][4-7][10-12]
    UsedMemMarkerOrderedTree usedMemMarkerOrderedTree = new UsedMemMarkerOrderedTree(15);


    usedMemMarkerOrderedTree.markMemAsUsed(0,3);
    usedMemMarkerOrderedTree.markMemAsUsed(10,12);
    usedMemMarkerOrderedTree.markMemAsUsed(4,7);

    ByteRange eightToNine = new ByteRange(8, 9);
    ByteRange thirteenToFourteen = new ByteRange(13, 14);
    List<ByteRange> expectedResult = new ArrayList<>();
    expectedResult.add(eightToNine);
    expectedResult.add(thirteenToFourteen);

    List<ByteRange> unusedBlocks = usedMemMarkerOrderedTree.getUnusedBlocks();
    assertEquals(expectedResult,unusedBlocks);

  }

  @Test
  public void allUsedMem() {
    UsedMemMarkerOrderedTree usedMemMarkerOrderedTree = new UsedMemMarkerOrderedTree(7);

    usedMemMarkerOrderedTree.markMemAsUsed(0,3);
    usedMemMarkerOrderedTree.markMemAsUsed(4,6);
    List<ByteRange> unusedBlocks = usedMemMarkerOrderedTree.getUnusedBlocks();
    assertEquals(unusedBlocks.size(),0);
  }
}
