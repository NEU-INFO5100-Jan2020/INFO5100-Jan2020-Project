package service;

import java.util.List;

public class MakeModelContainerV2 {
  List<MakeModelVer2> makeModelVer2s;

  public List<MakeModelVer2> getMakeModelVer2s() {
    return makeModelVer2s;
  }

  public void populateContainer(){
    if (MakeModelJsonPopulator.populateMakeModel() != null) {
      this.makeModelVer2s = MakeModelJsonPopulator.populateMakeModel();
    }
  }
}
