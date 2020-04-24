package service;

import java.util.List;
import java.util.Map;

public class ColorContainer {
  public Map<String,String> colors;

  public void populateContainer(){
    if (ColorJsonPopulator.populateColorContainer() != null) {
      this.colors = ColorJsonPopulator.populateColorContainer();
    }
  }
}

