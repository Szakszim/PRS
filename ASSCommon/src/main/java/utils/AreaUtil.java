package utils;

import java.math.BigDecimal;
import java.util.List;

public class AreaUtil {

    private static BigDecimal WSPOLCZYNNIK=new BigDecimal("7625944903.6763747731462320494002");

    public static BigDecimal calculateArea(List<Coordinates> coordinate_list) {

        BigDecimal result = BigDecimal.valueOf(0L);

        int leftIndexX = 0;
        int rightIndexX = 0;

        for (int i = 0; i < coordinate_list.size(); i++) {
            leftIndexX = i;
            rightIndexX = i;

            if (i == 0) {
                leftIndexX = coordinate_list.size();
            } else if ( i == coordinate_list.size() - 1){
                rightIndexX = -1;
            }
            result = result.add(coordinate_list.get(i).getY().multiply(coordinate_list.get(leftIndexX - 1).getX().subtract(coordinate_list.get(rightIndexX + 1).getX())));

        }

        System.out.println("in meters: "+result.divide(BigDecimal.valueOf(2L)).multiply(WSPOLCZYNNIK));
        return result.divide(BigDecimal.valueOf(2L)).multiply(WSPOLCZYNNIK).abs();
    }

    public static BigDecimal fromMetersToHectares(BigDecimal meterValue){
        System.out.println("in hectars: "+meterValue.divide(BigDecimal.valueOf(1000L)));
        return meterValue.divide(BigDecimal.valueOf(10000L));
    }
}
