package com.gayko.bookstore.converters;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface DTOConverter<E, D> {

    D toDTO(E entity);

    default List<D> toDTOList(List<E> list){
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    default Set<D> toDTOSet(Set<E> set){
        return set.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}
