package com.splunk.sharedmc.utilities;

public class Point3d {
    public final double x;
    public final double y;
    public final double z;

    /**
     * Constructs a new point.
     */
    public Point3d(double x, double y, double z) {


        this.x = Math.round(x * 100.00) / 100.00;
        this.y = Math.round(y * 100.00) / 100.00;
        this.z = Math.round(z * 100.00) / 100.00;

    }


}
