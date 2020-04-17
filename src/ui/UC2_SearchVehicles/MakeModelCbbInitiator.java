package ui.UC2_SearchVehicles;

import persist.ConnectionToSql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MakeModelCbbInitiator {

    private List<MakeDTO> makeDTOS;

    Collection<MakeDTO> getMakeModel() {
        if (makeDTOS == null)
            makeDTOS = sqlConnectionMakeModel();
        return makeDTOS;
    }

    private List<MakeDTO> sqlConnectionMakeModel() {
        ConnectionToSql connect = new ConnectionToSql();
        String query = "SELECT * FROM VehicleTable;";
        ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "SELECT");
        MakeModelContainerCbb mmc = new MakeModelContainerCbb();

        for (ArrayList<String> stringArrayList : result) {
            MakeDTO d = mmc.getMakeModel(stringArrayList.get(3));
            if (d == null) {
                d = new MakeDTO(stringArrayList.get(3));
                d.addModelToModels(stringArrayList.get(4));
                mmc.addMakeModel(d);
            }
            if (!d.models.contains(stringArrayList.get(4))) {
                d.addModelToModels(stringArrayList.get(4));
            }
        }

        // Convert from mcc to Collection
        ArrayList<MakeDTO> makeDTOList = new ArrayList<>();
        for (MakeDTO makeDTO : mmc.getMakeDTOContainer()) {
            makeDTOList.add(makeDTO);
        }
        return makeDTOList;
    }

    public static void main(String[] args) {
        new MakeModelCbbInitiator().getMakeModel();
    }
}

class MakeModelContainerCbb {
    Collection<MakeDTO> makeDTOContainer;

    MakeDTO getMakeModel(String make) {
        for (MakeDTO mm : makeDTOContainer) {
            if (mm.make.equals(make)) {
                return mm;
            }
        }
        return null;
    }

    public Collection<MakeDTO> getMakeDTOContainer() {
        return makeDTOContainer;
    }
    public MakeModelContainerCbb(){
        makeDTOContainer = new ArrayList<>();
    }
    public void addMakeModel(MakeDTO mm) {
        makeDTOContainer.add(mm);
    }
}

class MakeDTO {
    String make;
    Collection<String> models;

    public MakeDTO(String make) {
        this.make = make;
        models = new ArrayList<>();
    }

    public void addModelToModels(String model) {
        models.add(model);
    }
}

