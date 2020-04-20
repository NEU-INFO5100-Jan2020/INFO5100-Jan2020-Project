/*
Going to be obsolete, replaced by MakeModelVer2
 */


package service;

import persist.ConnectionToSql;

import java.util.ArrayList;

public class MakeModelContainerPopulator {
    ConnectionToSql connect = new ConnectionToSql();

    public MakeModelContainer getMakeModels() {


        String query = "SELECT * FROM VehicleTable;";

        System.out.println(query);

        ArrayList<ArrayList> result = connect.executeVehicleQuery(query, "SELECT");

        return convertToMakeModelContainer(result);
    }


    private MakeModelContainer convertToMakeModelContainer(ArrayList<ArrayList> sqlQueryOutput) {
        MakeModelContainer mmc = new MakeModelContainer();
        for (int i = 0; i < sqlQueryOutput.size(); i++) {
            ArrayList temp = sqlQueryOutput.get(i);


            MakeModel d = mmc.getMakeModel(temp.get(3).toString());
            if (d == null) {
                d = new MakeModel(temp.get(3).toString());
                d.addModelToModels(temp.get(4).toString());
                mmc.addMakeModel(d);
            }
            if (!d.getModels().contains(temp.get(4))) {
                d.addModelToModels(temp.get(4).toString());
            }
        }
        return mmc;
    }
}