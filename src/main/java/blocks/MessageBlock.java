package blocks;

import java.util.List;

public class MessageBlock {

  private int length;

  private List<Integer> pointers;

  private int payloadStart;

  private int payloadEnd;

  private int startBlock ;


  public int getStartBlock() {
    return startBlock;
  }

  public void setStartBlock(int startBlock) {
    this.startBlock = startBlock;
  }

  public int getBlockEndIndex(){
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

  public int getPayloadStart() {
    return payloadStart;
  }

  public void setPayloadStart(int payloadStart) {
    this.payloadStart = payloadStart;
  }

  public int getPayloadEnd() {
    return payloadEnd;
  }

  public void setPayloadEnd(int payloadEnd) {
    this.payloadEnd = payloadEnd;
  }
}

/*
A block contains the following, in order:
1. Length of the block as varint, including the size of this very field.
2. Zero or more pointers as varints.
3. A zero byte (may be omitted if there is no payload).
4. Zero or more arbitrary bytes (payload).
* */
