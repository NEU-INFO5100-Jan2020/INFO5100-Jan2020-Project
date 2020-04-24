/*
obsolete, going to remove soon
 */

package service;

import org.junit.jupiter.api.Test;
import service.*;


import static org.junit.jupiter.api.Assertions.*;

class MakeModelContainerPopulatorTest {
  MakeModelContainerPopulator mmcp;
  void initialize(){
    mmcp = new MakeModelContainerPopulator();
  }
  @Test
  void getMakeModels() {
    initialize();
    MakeModelContainer mmc;
    mmc =  mmcp.getMakeModels(1);
    for (MakeModel mm : mmc.getMakeModels()){

      System.out.println("--------------------------------------");
      System.out.println(mm.getMake());
      for (String model: mm.getModels()){
        System.out.println(model);
      }
    }
  }
}