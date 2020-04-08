package service;
import java.util.Collection;


public interface DataGetter {
  Collection<? extends BigDataType> get(SearchCriterion sc);
}

class IncentiveGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get(SearchCriterion sc) {
    return null;
  }
}

class VehicleGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get(SearchCriterion sc) {
    return null;
  }
}

class DealerGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get(SearchCriterion sc) {
    return null;
  }
}