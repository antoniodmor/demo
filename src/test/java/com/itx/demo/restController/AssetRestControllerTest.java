package com.itx.demo.restController;

import com.itx.demo.aplication.service.AssetServiceImpl;
import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadResponseDTO;
import com.itx.demo.infrastructure.restController.AssetRestController;
import com.itx.demo.infrastructure.restController.mapper.AssetDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetRestControllerTest {

    @Mock
    private AssetServiceImpl assetService;

    @InjectMocks
    private AssetRestController assetRestController;

    @Spy
    private AssetDtoMapperImpl assetDtoMapper;

    private AssetFileUploadRequestDTO request;
    private AssetFileUploadResponseDTO response;
    private Asset asset;

    @BeforeEach
    void setUp() {
        request = new AssetFileUploadRequestDTO("image.png", "image/png", "ffsdfdsfsdfsdfsd");
        asset = new Asset("image.png", "image/png", 100L, LocalDateTime.now(), "ffsdfdsfsdfsdfsd");
    }

    @Test
    void testUploadAsset_Success() {
        when(assetService.uploadAsset(any())).thenReturn("1");

        ResponseEntity<?> response = assetRestController.uploadAsset(request);

        assertEquals(202, response.getStatusCode().value());
    }


    @Test
    void testGetAssetsByFilter_Success() {
        List<Asset> assets = Collections.singletonList(asset);
        when(assetService.getAssetsByFilter(any(), any(), any(), any(), any())).thenReturn(assets);

        ResponseEntity<Object> response = assetRestController.getAssetsByFilter(
                LocalDateTime.now().minusDays(1), LocalDateTime.now(), "image.png", "image/png", "ASC"
        );

        assertEquals(200, response.getStatusCode().value());
        verify(assetService, times(1)).getAssetsByFilter(any(), any(), any(), any(), any());
    }


}