package hiddenmsgreader.blocks;

import hiddenmsgreader.varints.VarintsReaderWriter;

import java.util.ArrayList;
import java.util.List;

public class BlockFactory {

  public MessageBlock createBlock(byte[] bytes, int blockStartIndex) {

    MessageBlock messageBlock = new MessageBlock();
    messageBlock.setStartBlock(blockStartIndex);

    VarintsReaderWriter.VarintToIntResult result = VarintsReaderWriter.varint2int(bytes, blockStartIndex);
    messageBlock.setLength(result.getValue());

    if (messageBlock.getLength() == result.getNumOfBytes()) {
      return messageBlock;
    }

    int totalBytesRead = result.getNumOfBytes();
    List<Integer> pointers = readPointers(bytes, blockStartIndex, messageBlock, totalBytesRead);
    messageBlock.setPointers(pointers);
    return messageBlock;
  }


  private List<Integer> readPointers(byte[] bytes, int blockStartIndex, MessageBlock messageBlock, int totalBytesRead) {
    List<Integer> pointers = new ArrayList<>();
    VarintsReaderWriter.VarintToIntResult result;
    while (bytes[blockStartIndex + totalBytesRead] != 0 && totalBytesRead < messageBlock.getLength()) {

      result = VarintsReaderWriter.varint2int(bytes, blockStartIndex + totalBytesRead);
      totalBytesRead += result.getNumOfBytes();
      int value = result.getValue();
      pointers.add(value);
    }
    return pointers;
  }

}
