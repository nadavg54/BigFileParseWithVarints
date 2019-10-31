package controlbufferreader;

import blocks.BlockFactory;
import blocks.ByteRange;
import blocks.MessageBlock;
import controlbufferreader.usememmarker.IUsedMemMarker;
import controlbufferreader.usememmarker.UseMemMarkSet;
import controlbufferreader.usememmarker.UsedMemMarkerOrderedTree;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BufferProtocolReader {

  public static void main(String[] args) {



    Path path = Paths.get("/home/nad/Downloads/mysqlioperation.com/bufferprotoc/memorydump.bin");
    try {

      byte[] fileContents =  Files.readAllBytes(path);
      IUsedMemMarker memMarker = new UsedMemMarkerOrderedTree(fileContents.length);
      BlockFactory blockFactory = new BlockFactory();

      dfs(fileContents,0,blockFactory,memMarker);

      List<ByteRange> unusedBlocks = memMarker.getUnusedBlocks();
      byte[] hiddenMsg = new byte[14];
      int hiddemMsgIndex = 0;

      for(ByteRange block: unusedBlocks){
        int startBlock = block.getBegin();
        int end = block.getEnd();
        for (int i = startBlock; i <= end; i++){
          hiddenMsg[hiddemMsgIndex] = fileContents[i];
          hiddemMsgIndex++;
        }
      }
      //System.out.println(new String(hiddenMsg,"UTF-8"));
      String output = new String(hiddenMsg, StandardCharsets.UTF_8);

      System.out.println(output);
    }
    catch (IOException e) {

      System.exit(1);

    }

  }

  private static void dfs(byte[] data,int index,BlockFactory bf,IUsedMemMarker marker){

    if (marker.contains(index)){
      return;
    }

    MessageBlock block = bf.createBlock(data, index);
    marker.markMemAsUsed(block.getStartBlock(),block.getBlockEndIndex());
    List<Integer> pointers = block.getPointers();
    if(pointers == null){
      return;
    }
    for(Integer pointer: pointers){
        dfs(data,pointer,bf,marker);
    }

  }

}
