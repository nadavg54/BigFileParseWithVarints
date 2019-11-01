package hiddenmsgreader.usememmarker;

import hiddenmsgreader.blocks.ByteRange;

import java.util.List;

public interface IUsedMemMarker {

  void markMemAsUsed(int low, int high);

  boolean contains(int num);

  List<ByteRange> getUnusedBlocks();


}
