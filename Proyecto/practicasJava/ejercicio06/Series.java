import java.util.ArrayList;
import java.util.List;

public class Series {

    public static List<String> slices(String series, int sliceLength) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i <= series.length() - sliceLength; i++) {
            result.add(series.substring(i, i + sliceLength));
        }

        return result;
    }
}