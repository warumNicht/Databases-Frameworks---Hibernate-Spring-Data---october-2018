package org.softuni.mostwanted.parser.interfaces;


import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelParserImpl implements ModelParser {
    private ModelMapper mapper;

    public ModelParserImpl() {
        this.mapper=new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        D destination = this.mapper.map(source, destinationClass);
        return destination;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.mapper.addMappings(propertyMap);
        D result = this.mapper.map(source, destinationClass);
        return result;
    }
}
