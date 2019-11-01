package hiddenmsgreader;

import hiddenmsgreader.blocks.BlockFactory;
import hiddenmsgreader.blocks.ByteRange;
import hiddenmsgreader.blocks.MessageBlock;
import hiddenmsgreader.usememmarker.IUsedMemMarker;
import hiddenmsgreader.usememmarker.UsedMemMarkerOrderedTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class HiddenMsgReader {

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("file path argument is missing");
      System.exit(1);
    }
    String filePath = args[0];

    Path path = Paths.get(filePath);
    try {

      byte[] fileContent = Files.readAllBytes(path);
      IUsedMemMarker memMarker = new UsedMemMarkerOrderedTree(fileContent.length);
      BlockFactory blockFactory = new BlockFactory();

      dfsItirative(fileContent, blockFactory, memMarker);

      List<ByteRange> unusedBlocks = memMarker.getUnusedBlocks();
      int hiddenMsgLength = unusedBlocks.stream().map(e -> (e.getEnd() - e.getBegin() + 1)).reduce(0, Integer::sum);
      byte[] hiddenMsg = copyHiddenMsgBytes(fileContent, unusedBlocks, hiddenMsgLength);
      String output = new String(hiddenMsg, StandardCharsets.UTF_8);
      System.out.println(output);
    }

    catch (IOException e) {
      System.exit(1);
    }

  }

  private static byte[] copyHiddenMsgBytes(byte[] fileContent, List<ByteRange> unusedBlocks, int length) {

    byte[] hiddenMsgArr = new byte[length];

    int hiddemMsgIndex = 0;
    for (ByteRange block : unusedBlocks) {
      int startBlock = block.getBegin();
      int end = block.getEnd();
      for (int i = startBlock; i <= end; i++) {
        hiddenMsgArr[hiddemMsgIndex] = fileContent[i];
        hiddemMsgIndex++;
      }
    }
    return hiddenMsgArr;
  }


  private static void dfsItirative(byte[] data, BlockFactory bf, IUsedMemMarker marker) {

    Stack<Integer> integers = new Stack<>();
    integers.push(0);

    while (!integers.empty()) {

      Integer pointer = integers.pop();

      MessageBlock block = bf.createBlock(data, pointer);
      marker.markMemAsUsed(block.getStartBlock(), block.getBlockEndIndex());
      for (Integer currPointer : block.getPointers()) {
        if (marker.contains(currPointer)) {
          continue;
        }
        integers.push(currPointer);
      }

    }

  }

}
