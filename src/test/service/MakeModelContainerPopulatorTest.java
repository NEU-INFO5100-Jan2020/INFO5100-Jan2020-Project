package service;

import org.junit.jupiter.api.Test;

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
    int counter = 0;
    mmc =  mmcp.getMakeModels();
    for (MakeModel mm : mmc.getMakeModels()){

      System.out.println("--------------------------------------");
      System.out.println(mm.make);
      for (String model: mm.models){
        System.out.println(model);
        counter ++;
      }
    }
    System.out.println(counter);
  }
}