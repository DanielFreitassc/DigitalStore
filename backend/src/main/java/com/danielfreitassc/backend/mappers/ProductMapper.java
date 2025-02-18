package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.ProductRequestDto;
import com.danielfreitassc.backend.dtos.ProductResponseDto;
import com.danielfreitassc.backend.models.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toDto(ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void toUpdateDto(ProductRequestDto productRequestDto, @MappingTarget ProductEntity productEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ProductEntity toEntity(ProductRequestDto productRequestDto);
}
