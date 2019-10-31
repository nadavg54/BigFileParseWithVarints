package blocks;

import controlbufferreader.varints.VarintsReaderWriter;

import java.util.ArrayList;
import java.util.List;

public class BlockFactory {

  public MessageBlock createBlock(byte[] bytes,int blockStartIndex){

    MessageBlock messageBlock = new MessageBlock();
    messageBlock.setStartBlock(blockStartIndex);
    VarintsReaderWriter.VarintToIntResult result = VarintsReaderWriter.varint2int(bytes,blockStartIndex);
    messageBlock.setLength(result.getValue());

    if (messageBlock.getLength() == result.getNumOfBytes()){
        return messageBlock;
    }

    int totalBytesRead = result.getNumOfBytes();
    List<Integer> pointers = new ArrayList<>();

    //read pointers
    while(bytes[blockStartIndex + totalBytesRead] != 0 && totalBytesRead < messageBlock.getLength()) {

      result = VarintsReaderWriter.varint2int(bytes,blockStartIndex + totalBytesRead);
      totalBytesRead += result.getNumOfBytes();
      int value = result.getValue();
      pointers.add(value);
    }

    messageBlock.setPointers(pointers);
    if (bytes[blockStartIndex + totalBytesRead] == 0 && totalBytesRead < messageBlock.getLength() ){
      messageBlock.setPayloadStart(totalBytesRead+1);
      messageBlock.setPayloadEnd(messageBlock.getLength() - 1);
    }

    return messageBlock;
  }

}
