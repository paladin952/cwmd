package application.web.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for all converters
 * @param <T> From
 * @param <DTO> To
 */
public abstract class Converter<T, DTO> {
    public abstract DTO toDTO(T t);
    public abstract T fromDTO(DTO dto);

    /**
     * Convert to DTO
     * @param objs The object
     * @return The new DTO
     */
    public List<DTO> toDTOs(List<T> objs)
    {
        return objs.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert from DTO to object
     * @param dtos The dto
     * @return The new object
     */
    public List<T> fromDTOs(List<DTO> dtos)
    {
        return dtos.stream().map(this::fromDTO)
                .collect(Collectors.toList());
    }
}