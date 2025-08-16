package com.juandavyc.products.dto.response;

import lombok.*;

import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PagedResponseDto {

    private List<ProductResponseDto> content;
    private Integer totalPages;
    private Integer number;
    private Integer size;
    private Integer numberOfElements;
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private Boolean isEmpty;

}
