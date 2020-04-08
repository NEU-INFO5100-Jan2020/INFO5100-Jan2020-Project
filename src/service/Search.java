package service;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

interface SearchFactory {
  /*
  Prototype of constructors
   */
  Parser produceParser();

  DataGetter produceDataGetter();

  Sorter produceSorter();
}

class SearchDealer implements SearchFactory {
  /*
  SearchDealer Factory, creates services for Dealer GUI
   */
  @Override
  public Parser produceParser() {
    return new DealerParser();
  }

  @Override
  public DataGetter produceDataGetter() {
    return new DealerGetter();
  }

  @Override
  public Sorter produceSorter() {
    return new DealerSorter();
  }

}

class SearchVehicle implements SearchFactory {
  /*
  SearchVehicle Factory, creates services for Vehicle GUI
   */

  @Override
  public Parser produceParser() {
    return new VehicleParser();
  }

  @Override
  public DataGetter produceDataGetter() {
    return new VehicleGetter();
  }

  @Override
  public Sorter produceSorter() {
    return new VehicleSorter();
  }
}

class SearchIncentive implements SearchFactory {
  /*
  SearchIncentive Factory, creates services for Incentive GUI
   */
  @Override
  public Parser produceParser() {
    return new IncentiveParser();
  }

  @Override
  public DataGetter produceDataGetter() {
    return new IncentiveGetter();
  }

  @Override
  public Sorter produceSorter() {
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
  String[] input;

  public Search(SearchCriterion sc, SortFilter sf){
    /*
    One option of constructor of Search class, the input should be a SearchCriterion object including the information
    needed by our services
     */
    input = sc.optionalSearchFilters;
    String signature = sc.mandatoryInput[0];
    if (signature.equals("Vehicle")) {
      factory = new SearchVehicle();
    } else if (signature.equals("Dealer")) {
      factory = new SearchDealer();
    } else if (signature.equals("Incentive")) {
      factory = new SearchIncentive();
    } else {
      throw new NoSuchElementException();
    }
  }

  public ArrayList<? extends BigDataType> doSearch() {
    /*
    Functions as the main method for our service, it creates Getter, Parser and Sorter instances to
     */
    DataGetter curGetter = this.factory.produceDataGetter();
    Parser curParser = this.factory.produceParser();
    Sorter curSorter = this.factory.produceSorter();
    SearchFilter sf = curParser.parse(this.input);
    Collection<?extends BigDataType> data = curGetter.get();
    return curSorter.sort(data);
  }
}
