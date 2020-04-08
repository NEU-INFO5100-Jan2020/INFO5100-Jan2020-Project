package service;
import java.util.Collection;

public interface DataGetter {
  Collection<? extends BigDataType> get();
}

class IncentiveGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get() {
    return null;
  }
}

class VehicleGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get() {
    return null;
  }
}

class DealerGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get() {

    return null;
  }
}