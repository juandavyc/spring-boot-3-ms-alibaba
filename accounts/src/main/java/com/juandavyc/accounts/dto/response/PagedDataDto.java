package com.juandavyc.accounts.dto.response;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PagedDataDto<T> {

    private List<T> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean isEmpty;
    private Boolean hasPrevious;
    private Boolean hasNext;

}
