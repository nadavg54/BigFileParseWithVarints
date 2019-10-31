package controlbufferreader.usememmarker;

import blocks.ByteRange;
import blocks.MessageBlock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UseMemMarkSet implements IUsedMemMarker
{
  private Set<Integer> usedBytes = new HashSet<>();

  @Override
  public void markMemAsUsed(int low, int high) {
    for(int i = low; i <= high ; i++){
      usedBytes.add(i);
    }
  }

  @Override
  public boolean contains(int num) {
    return usedBytes.contains(num);
  }

  @Override
  public List<ByteRange> getUnusedBlocks() {
    return null;
  }

  @Override
  public int getNumOfUnusedBytes() {
    return 0;
  }
}
