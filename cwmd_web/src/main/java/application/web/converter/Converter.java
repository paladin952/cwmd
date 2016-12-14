package application.web.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<T, DTO> {
    public abstract DTO toDTO(T obj);
    public abstract T fromDTO(DTO dto);
    public List<DTO> toDTOs(List<T> objs)
    {
        return objs.stream().map(this::toDTO)
        .collect(Collectors.toList());
    }

    public List<T> fromDTOs(List<DTO> dtos)
    {
        return dtos.stream().map(this::fromDTO)
                .collect(Collectors.toList());
    }
}
