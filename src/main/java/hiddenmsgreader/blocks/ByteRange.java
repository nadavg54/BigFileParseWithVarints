package hiddenmsgreader.blocks;

public class ByteRange implements Comparable {

  private int begin;

  private int end;

  public ByteRange(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  @Override
  public int compareTo(Object o) {
    ByteRange other = (ByteRange) o;
    return getBegin() - other.getBegin();
  }

  @Override
  public boolean equals(Object obj) {
    return ((ByteRange) obj).getBegin() == getBegin();
  }
}
