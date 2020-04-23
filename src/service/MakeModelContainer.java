/*
Going to be obsolete, replaced by MakeModelVer2
 */


package service;

import java.util.ArrayList;
import java.util.Collection;

public class MakeModelContainer {
    Collection<MakeModel> makeModels;

    MakeModel getMakeModel(String make) {
        for (MakeModel mm : makeModels) {
            if (mm.make.equals(make)) {
                return mm;
            }
        }
        return null;
    }

    public Collection<MakeModel> getMakeModels() {
        return makeModels;
    }
    public MakeModelContainer(){
        makeModels = new ArrayList<>();
    }
    public void addMakeModel(MakeModel mm) {
        makeModels.add(mm);
    }
}