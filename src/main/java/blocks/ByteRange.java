package blocks;

public class ByteRange implements Comparable{

  private int begin;

  private int end;

  public ByteRange(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public void setBegin(int begin) {
    this.begin = begin;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  @Override
  public int compareTo(Object o) {
    ByteRange other = (ByteRange)o;
    return getBegin() - other.getBegin();
  }

  @Override
  public boolean equals(Object obj) {
    return ((ByteRange)obj).getBegin() == getBegin();
  }
}
