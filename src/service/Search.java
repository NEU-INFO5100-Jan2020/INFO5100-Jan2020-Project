package service;

import dto.BigDataType;
import dto.Vehicle;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

interface SearchFactory {
  /*
  Prototype of constructors
   */

  DataGetter produceDataGetter(SearchFilter serf);

  Sorter produceSorter(SortFilter sorf);
}

class SearchDealer implements SearchFactory {
  /*
  SearchDealer Factory, creates services for Dealer GUI
   */

  @Override
  public DataGetter produceDataGetter(SearchFilter serf) {
    return new DealerGetter((DealerSearchFilter) serf);
  }

  @Override
  public Sorter produceSorter(SortFilter sorf) {
    return new DealerSorter();
  }

}

class SearchVehicle implements SearchFactory {
  /*
  SearchVehicle Factory, creates services for Vehicle GUI
   */


  @Override
  public DataGetter produceDataGetter(SearchFilter serf) {
    return new VehicleGetter((VehicleSearchFilter) serf);
  }

  @Override
  public Sorter produceSorter(SortFilter sorf) {
    return new VehicleSorter();
  }
}

class SearchIncentive implements SearchFactory {
  /*
  SearchIncentive Factory, creates services for Incentive GUI
   */

  @Override
  public DataGetter produceDataGetter(SearchFilter serf) {
    return new IncentiveGetter(serf);
  }

  @Override
  public Sorter produceSorter(SortFilter sorf) {
    return new IncentiveSorter();
  }
}

public class Search {
  /*
  This class is the API we provide to all the GUIs. The GUI should instantiate a Search instance with their input of
  GUI and their identifier(Vehicle, Dealer or Incentive). Afterwards the GUI only need to call the doSearch() method
  then our algorithms will return sorted data for GUI to display. The instantiation of Search also creates the corresponding
  Parser, DataGetter and Sorter by the SearchFactory using Factory method pattern.
   */
  SearchFactory factory;
  SearchFilter serf;
  SortFilter sorf;
  List<? extends BigDataType> results;

  public Search(SearchFilter serf, SortFilter sorf) {
    /*
    One option of constructor of Search class, the input should be a SearchCriterion object including the information
    needed by our services
     */
    this.serf = serf;
    this.sorf = sorf;
    if (serf == null) {
      throw new NullPointerException("serf is null");
    }
    if (serf.getClass() == VehicleSearchFilter.class) {
      factory = new SearchVehicle();
    } else if (serf.getClass() == DealerSearchFilter.class) {
      factory = new SearchDealer();
    } else if (serf.getClass() == IncentiveSearchFilter.class) {
      factory = new SearchIncentive();
    } else {
      throw new NoSuchElementException("invalid type");
    }
  }

  public void doSearch() {
    /*
    Functions as the main method for our service, it creates Getter, Parser and Sorter instances to
     */
    DataGetter curGetter = this.factory.produceDataGetter(serf);
    Sorter curSorter = this.factory.produceSorter(sorf);
    results = curSorter.sort(curGetter.get());
  }

  public SearchFilter getSerf() {
    return serf;
  }

  public SearchFactory getFactory() {
    return factory;
  }

  public SortFilter getSorf() {
    return sorf;
  }

  public Collection<? extends BigDataType> getResults() {
    return results;
  }

  public int[] getArrayOfVehicleID(){
    int[] vidArray = new int[results.size()];
    for (int i = 0; i < results.size(); i++) {
      vidArray[i] = ((Vehicle)results.get(i)).getVehicleId();
    }
    return vidArray;
  }
}
