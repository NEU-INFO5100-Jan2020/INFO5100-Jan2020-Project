package service;
import java.util.Collection;

public interface Parser {
  Collection<String> parse(String[] k);

}

class VehicleParser implements Parser {
  @Override
  public Collection<String> parse(String[] k) {
    return null;
  }
}

class DealerParser implements Parser {
  @Override
  public Collection<String> parse(String[] k) {
    return null;
  }
}

class IncentiveParser implements Parser {
  @Override
  public Collection<String> parse(String[] k) {
    return null;
  }
}
