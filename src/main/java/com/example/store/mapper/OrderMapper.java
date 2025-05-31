package com.example.store.mapper;

import com.example.store.dto.OrderCustomerDTO;
import com.example.store.dto.OrderDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Named("mapProducts")
    static List<com.example.store.dto.ProductDTO> mapProducts(Set<Product> products) {
        if (products == null) return List.of();
        return products.stream().map(product -> {
            com.example.store.dto.ProductDTO dto = new com.example.store.dto.ProductDTO();
            dto.setId(product.getId());
            dto.setDescription(product.getDescription());
            dto.setOrders(product.getOrders().stream().map(Order::getId).collect(Collectors.toList()));
            return dto;
        }).toList();
    }

    @Mapping(target = "products", source = "products", qualifiedByName = "mapProducts")
    OrderDTO orderToOrderDTO(Order order);

    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);

    OrderCustomerDTO orderToOrderCustomerDTO(Customer customer);
}
