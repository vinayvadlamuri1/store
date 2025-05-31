package com.example.store.mapper;

import com.example.store.dto.ProductDTO;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Named("orderSetToIdList")
    static List<Long> orderSetToIdList(Set<Order> orders) {
        if (orders == null) return List.of();
        return orders.stream().map(com.example.store.entity.Order::getId).collect(Collectors.toList());
    }

    @Mapping(target = "orders", source = "orders", qualifiedByName = "orderSetToIdList")
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductDTOs(List<Product> products);
}