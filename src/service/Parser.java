/*
Obsolete
 */

package service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Parser {
  Collection<String> parse(String[] k);

}

class VehicleParser implements Parser {
  @Override
  public Collection<String> parse(String[] k) {
    List<String> out  = new ArrayList<>();
    return out;
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
