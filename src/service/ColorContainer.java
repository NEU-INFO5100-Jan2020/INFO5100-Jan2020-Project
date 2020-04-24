package service;

import java.util.List;
import java.util.Map;

public class ColorContainer {
  Map<String,String> colors;

  public void populateContainer(){
    if (ColorJsonPopulator.populateColorContainer() != null) {
      this.colors = ColorJsonPopulator.populateColorContainer();
    }
  }
}

