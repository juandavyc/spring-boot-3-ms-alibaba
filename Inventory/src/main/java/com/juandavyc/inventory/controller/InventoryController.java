package com.juandavyc.inventory.controller;

import com.juandavyc.inventory.constants.InventoryResponseCode;
import com.juandavyc.inventory.dto.request.InventoryRequestDto;
import com.juandavyc.inventory.dto.response.InventoryResponseDto;
import com.juandavyc.inventory.dto.response.PagedDataDto;
import com.juandavyc.inventory.dto.response.ResponseDto;
import com.juandavyc.inventory.helper.ResponseBuilder;
import com.juandavyc.inventory.service.IInventoryService;
import com.juandavyc.inventory.validation.group.OnCreate;
import com.juandavyc.inventory.validation.group.OnUpdate;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@Validated
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @GetMapping
    public ResponseEntity<ResponseDto<PagedDataDto<InventoryResponseDto>>> fetchAllInventory(
            @PageableDefault(size = 2, sort = "id") Pageable pageable
    ) {
        PagedDataDto<InventoryResponseDto>
                inventoryListDto = inventoryService.fetchAllInventory(pageable);

        InventoryResponseCode inventoryResponseCode = InventoryResponseCode.SUCCESS;

        return ResponseEntity
                .status(inventoryResponseCode.getStatus())
                .body(ResponseBuilder.response(inventoryResponseCode, inventoryListDto));

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<InventoryResponseDto>> fetchInventoryById(
            @PathVariable UUID id
    ) {
        InventoryResponseDto
                inventoryResponseDto = inventoryService.findById(id);
        InventoryResponseCode inventoryResponseCode = InventoryResponseCode.SUCCESS;

        return ResponseEntity
                .status(inventoryResponseCode.getStatus())
                .body(ResponseBuilder.response(inventoryResponseCode, inventoryResponseDto));
    }

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<ResponseDto<InventoryResponseDto>> fetchInventoryByProductId(
            @PathVariable UUID id
    ) {
        InventoryResponseDto
                inventoryResponseDto = inventoryService.findByProductId(id);
        InventoryResponseCode inventoryResponseCode = InventoryResponseCode.SUCCESS;

        return ResponseEntity
                .status(inventoryResponseCode.getStatus())
                .body(ResponseBuilder.response(inventoryResponseCode, inventoryResponseDto));
    }


    @PostMapping
    public ResponseEntity<ResponseDto<InventoryResponseDto>> createInventory(
            @Validated({OnCreate.class, Default.class}) @RequestBody InventoryRequestDto requestDto
    ) {
        InventoryResponseDto
                inventoryResponseDto = inventoryService.createInventory(requestDto);
        InventoryResponseCode inventoryResponseCode = InventoryResponseCode.SUCCESS;

        return ResponseEntity
                .status(inventoryResponseCode.getStatus())
                .body(ResponseBuilder.response(inventoryResponseCode, inventoryResponseDto));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<ResponseDto<InventoryResponseDto>> updateInventory(
            @PathVariable UUID id,
            @Validated({OnUpdate.class, Default.class}) @RequestBody InventoryRequestDto requestDto
    ) {
        InventoryResponseDto
                inventoryResponseDto = inventoryService.updateInventory(id, requestDto);
        InventoryResponseCode inventoryResponseCode = InventoryResponseCode.SUCCESS;

        return ResponseEntity
                .status(inventoryResponseCode.getStatus())
                .body(ResponseBuilder.response(inventoryResponseCode, inventoryResponseDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto<Map<String, Boolean>>> deleteProduct(
            @PathVariable UUID id
    ) {
        boolean isDeleted = inventoryService.deleteProduct(id);
        InventoryResponseCode responseCode =
                (isDeleted) ? InventoryResponseCode.SUCCESS : InventoryResponseCode.DELETE_FAILED;

        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseBuilder.response(
                                responseCode,
                                Map.of("isDeleted", isDeleted)
                        )
                );


    }


}
