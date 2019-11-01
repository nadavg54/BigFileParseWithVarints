package hiddenmsgreader.blocks;

import java.util.List;

public class MessageBlock {

  private int length;

  private List<Integer> pointers;

  private int startBlock;


  public int getStartBlock() {
    return startBlock;
  }

  public void setStartBlock(int startBlock) {
    this.startBlock = startBlock;
  }

  public int getBlockEndIndex() {
    return getStartBlock() + getLength() - 1;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public List<Integer> getPointers() {
    return pointers;
  }

  public void setPointers(List<Integer> pointers) {
    this.pointers = pointers;
  }

}
