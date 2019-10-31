package controlbufferreader.usememmarker;

import blocks.ByteRange;
import blocks.MessageBlock;

import java.util.List;

public interface IUsedMemMarker {

  void markMemAsUsed(int low,int high);

  boolean contains(int num);

  List<ByteRange> getUnusedBlocks();

  int getNumOfUnusedBytes();

}
