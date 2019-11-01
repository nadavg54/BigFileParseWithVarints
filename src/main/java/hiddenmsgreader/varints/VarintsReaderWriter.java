package hiddenmsgreader.varints;

public class VarintsReaderWriter {

  public static VarintToIntResult varint2int(byte[] bytes, int readFromIndex) {
    VarintToIntResult result = new VarintToIntResult();

    int value = bytes[readFromIndex];
    if ((value & 0x80) == 0) {
      result.setValue(value);
      result.setNumOfBytes(1);
      return result;
    }

    value &= 0x7F;
    int chunk = bytes[readFromIndex + 1];
    value |= (chunk & 0x7F) << 7;
    if ((chunk & 0x80) == 0) {
      result.setValue(value);
      result.setNumOfBytes(2);
      return result;
    }

    chunk = bytes[readFromIndex + 2];
    value |= (chunk & 0x7F) << 14;
    if ((chunk & 0x80) == 0) {
      result.setValue(value);
      result.setNumOfBytes(3);
      return result;
    }

    chunk = bytes[readFromIndex + 3];
    value |= (chunk & 0x7F) << 21;
    if ((chunk & 0x80) == 0) {
      result.setValue(value);
      result.setNumOfBytes(4);
      return result;
    }

    chunk = bytes[readFromIndex + 4];
    value |= chunk << 28;
    if ((chunk & 0xF0) == 0) {
      result.setValue(value);
      result.setNumOfBytes(5);
      return result;
    }

    throw new RuntimeException("varint2int error");
  }

  public static class VarintToIntResult {

    private int value;

    private int numOfBytes;

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getNumOfBytes() {
      return numOfBytes;
    }

    public void setNumOfBytes(int numOfBytes) {
      this.numOfBytes = numOfBytes;
    }
  }

}
