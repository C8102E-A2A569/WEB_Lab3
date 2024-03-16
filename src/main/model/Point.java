package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean(name = "point")
@ApplicationScoped
@NoArgsConstructor
public class Point implements Serializable {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private double r;
    @Getter
    @Setter
    private boolean hit;
    @Getter
    @Setter
    private long attemptTime;
    @Getter
    @Setter
    private double executionTime;
    // внедряем бин ResultTable с названием table в текущий бин
    @ManagedProperty(value = "#{table}")
    @Getter
    @Setter
    private transient ResultTable table;

    public Point(double x, double y, double r, boolean hit, long attemptTime, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.attemptTime = attemptTime;
        this.executionTime = executionTime;
    }

    public void check() {
        long start = System.nanoTime();
        long attemptTime = System.currentTimeMillis();
        boolean hit = ((x >= -r) &&(y <= r/2) && (x <= 0) && (y >= 0) && (1.3*y - x/2<= r/2))
                || ((x >= 0) && (x <= r/2) && (y <= 0) && (y >= -r))
                || ((x >= 0) && (y >= 0) && (x * x + y * y <= r * r / 4));
        long executionTime = System.nanoTime() - start;
        table.addPoint(new Point(x, y, r, hit, attemptTime, executionTime));
    }
}
