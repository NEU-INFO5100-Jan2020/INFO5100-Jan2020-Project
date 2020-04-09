package service;

import java.util.ArrayList;
import java.util.Collection;
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
    return new DealerGetter();
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
    return new VehicleGetter();
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
    return new IncentiveGetter();
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

  public Search(SearchFilter serf, SortFilter sorf){
    /*
    One option of constructor of Search class, the input should be a SearchCriterion object including the information
    needed by our services
     */
    this.serf = serf;
    this.sorf = sorf;
    if (serf.getClass() == VehicleSearchFilter.class) {
      factory = new SearchVehicle();
    } else if (serf.getClass() == DealerSearchFilter.class) {
      factory = new SearchDealer();
    } else if (serf.getClass() == IncentiveSearchFilter.class) {
      factory = new SearchIncentive();
    } else {
      throw new NoSuchElementException();
    }
  }

  public ArrayList<? extends BigDataType> doSearch() { // TODO: 4/9/2020 Discuss with other teams on what data type is convenient for their GUI
    /*
    Functions as the main method for our service, it creates Getter, Parser and Sorter instances to
     */
    DataGetter curGetter = this.factory.produceDataGetter(serf);
    Sorter curSorter = this.factory.produceSorter(sorf);
    Collection<?extends BigDataType> data = curGetter.get();
    // TODO: 4/9/2020 Do modifications after change of IO with GUI 
    return curSorter.sort(data);
  }
}
