package com.taxiboking.project.taxiboiking.utils;

import com.taxiboking.project.taxiboiking.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;


//this utility class is used to get the exact location of the points mentioned by the user

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);  //this line makes the model of reference for the points to get located and SRID 4326 represents earth
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);

        return geometryFactory.createPoint(coordinate);
    }
}



