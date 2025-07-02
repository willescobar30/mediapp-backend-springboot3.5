package com.mitocode.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    //inyectando appcontext(se recupera su valor en tiempo de ejecucion) para que se pueda acceder al bean de Qualifier de defaultMapper o MedicsMapper
    private final ApplicationContext applicationContext;
    //usando Java Reflection
    //source: recibe una lista de lo que viene(origen)
    //tagetClass hacia donde se ira esa info transformada(destino)
    //S, T-> definiendo genericos en el metodo
    //mandando el modelmapper como parametro en mapperQualifier
    //los 3 puntos en String... significa que es un arreglo y es opcional que se le mande info
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, String... mapperQualifier){
        ModelMapper modelMapper = getModelMapper(mapperQualifier);
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    //de origen a destino
    //los 3 puntos en String... significa que es un arreglo
    public <S, T> T map(S source, Class<T> targetClass, String... mapperQualifier){
        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        return modelMapper.map(source, targetClass);
    }

    //metodo que devuelve el Qualifier basado en el modelMapper
    private ModelMapper getModelMapper(String... mapperQualifier){
        //validando si el mapperQualifier trae algo en el arreglo agarre el de ahi, sino asigna default por defecto
        if(mapperQualifier.length == 0 || mapperQualifier[0] == null || mapperQualifier[0].isEmpty()){
            return applicationContext.getBean("defaultMapper", ModelMapper.class);
        }else {
            return applicationContext.getBean(mapperQualifier[0], ModelMapper.class);
        }
    }

}
