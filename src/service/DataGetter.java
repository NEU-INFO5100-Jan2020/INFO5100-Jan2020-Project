package service;
import java.util.Collection;


public interface DataGetter {
  /*
  Interface DataGetter,
   */
  Collection<? extends BigDataType> get(SearchFilter sf);
}

class IncentiveGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get(SearchFilter sf) {
    return null;
  }
}

class VehicleGetter implements DataGetter{

  @Override
  public Collection<? extends BigDataType> get(SearchFilter sf) {
    return null;
  }
}

class DealerGetter implements DataGetter{


  @Override
  public Collection<? extends BigDataType> get(SearchFilter sf) {
    return null;
  }
}