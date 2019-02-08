package com.gayko.bookstore.converters;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface Converter<D, E> {

    E toEntity(D dto);

    default List<E> toEntityList(List<D> list){
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    default Set<E> toEntitySet(Set<D> set){
        return set.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
