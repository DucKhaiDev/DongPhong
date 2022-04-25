package Tools;

import Util.Constant;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;

import java.io.File;
import java.util.Locale;

public class GraphHopper {
    private final static String osmFile = Constant.Path.DP_RESOURCES + File.separator + "vietnam-latest.osm.pbf";
    private final static String graphFolder = Constant.Path.DP_RESOURCES + File.separator + "map";
    private static com.graphhopper.GraphHopper hopper;

    private static void init() {
        hopper = new GraphHopperOSM().forServer();
        hopper.setDataReaderFile(osmFile);
        hopper.setGraphHopperLocation(graphFolder);
        hopper.setEncodingManager(EncodingManager.create("car"));
        hopper.setProfiles(new Profile("car").setVehicle("car").setWeighting("fastest"));
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        hopper.importOrLoad();
    }

    public static double calculateDistance(double lat1, double long1, double lat2, double long2) {
        init();
        GHRequest request = new GHRequest(lat1, long1, lat2, long2).setProfile("car").setLocale(Locale.US);
        GHResponse response = hopper.route(request);

        if (response.hasErrors()) {
            System.out.println("Error: " + response.getErrors());
            return 0;
        }

        ResponsePath path = response.getBest();
        return path.getDistance();
    }
}