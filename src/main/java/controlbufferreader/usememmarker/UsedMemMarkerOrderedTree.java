package controlbufferreader.usememmarker;

import blocks.ByteRange;

import java.util.ArrayList;

import java.util.List;
import java.util.TreeSet;

public class UsedMemMarkerOrderedTree implements IUsedMemMarker {

  private TreeSet<ByteRange> usedMem = new TreeSet<>();

  private int numberOfBytes;

  public UsedMemMarkerOrderedTree(int numberOfBytes) {
    this.numberOfBytes = numberOfBytes;
  }

  @Override
  public void markMemAsUsed(int low, int high) {
    ByteRange byteRange = new ByteRange(low, high);
    usedMem.add(byteRange);
  }

  @Override
  public boolean contains(int num) {
    return usedMem.contains(new ByteRange(num,num));
  }

  @Override
  public List<ByteRange> getUnusedBlocks() {

    List<ByteRange> result = new ArrayList<>();
    ByteRange previousRange = null;

    for(ByteRange range : usedMem){
      if (previousRange != null && ( (range.getBegin() - previousRange.getEnd()) > 1)){
        int begin = previousRange.getEnd() + 1;
        int end = range.getBegin() - 1;
        ByteRange unusedMem = new ByteRange(begin,end);
        result.add(unusedMem);
      }
      previousRange = range;
    }
    if (numberOfBytes - previousRange.getEnd() > 1){
      result.add(new ByteRange(previousRange.getEnd() + 1,numberOfBytes -1));
    }
    return result;
  }

  @Override
  public int getNumOfUnusedBytes() {
    return 10000;
  }



}
