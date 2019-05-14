package utils;

import java.math.BigDecimal;

public class Coordinates {

    private BigDecimal x;
    private BigDecimal y;

    public Coordinates(Long x, Long y) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    public Coordinates(Double x, Double y) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    public Coordinates(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX(){
        return x;
    }

    public BigDecimal getY(){
        return y;
    }
}
