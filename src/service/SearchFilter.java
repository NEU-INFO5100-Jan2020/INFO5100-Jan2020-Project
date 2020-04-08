package service;

public interface SearchFilter {
  String getName();
  Object getValue();
}

class DealerSearch implements SearchFilter{

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Object getValue() {
    return null;
  }
}