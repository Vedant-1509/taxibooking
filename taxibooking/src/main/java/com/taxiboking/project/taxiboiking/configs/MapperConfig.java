package com.taxiboking.project.taxiboiking.configs;

import com.taxiboking.project.taxiboiking.dto.PointDto;
import com.taxiboking.project.taxiboiking.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        // Mapping from PointDto to org.locationtech.jts.geom.Point
        mapper.typeMap(PointDto.class, Point.class).setConverter(converter -> {
            PointDto pointDto = converter.getSource();
            return GeometryUtil.createPoint(pointDto);  // This returns a locationtech.jts.geom.Point
        });
        mapper.typeMap(Point.class, PointDto.class).setConverter(converter->{
            Point point=converter.getSource();
            double coordinates[]={
                    point.getX(),
                    point.getY()
            };
            return new PointDto(coordinates);
        });

        return mapper;
    }
}
