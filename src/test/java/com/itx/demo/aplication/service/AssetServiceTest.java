package com.itx.demo.aplication.service;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadResponseDTO;
import com.itx.demo.domain.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    private Asset  asset2;

    private AssetFileUploadRequestDTO request;


    @BeforeEach
    void setup() {
        asset2 = new Asset(2L,"image.png", "image/png", 512L, LocalDateTime.of(2024, 5, 3, 12, 0),"fsdfsdfsdfsdfsdfds");
        request = new AssetFileUploadRequestDTO("file.txt", "image/png", "asfasfsafasfsafasfas");
    }

    @Test
    void filteredAssets() {
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 5, 0, 0);
        String filename = "image.png";
        String filetype = "image/png";
        String sortDirection = "ASC";

        when(assetRepository.getAssetsByFilter(startDate,endDate,filename,filetype,sortDirection)).thenReturn(Collections.singletonList(asset2));
        List<Asset> result = assetService.getAssetsByFilter(startDate, endDate, filename, filetype, sortDirection);

        assertThat(result).isNotEmpty();
        assertThat(result).containsExactly(asset2);
        verify(assetRepository, times(1)).getAssetsByFilter(startDate,endDate,filename,filetype,sortDirection);
    }

    @Test
    void testUploadAsset()  {

        when(assetRepository.save(any(Asset.class))).thenReturn(asset2);

        String response = assetService.uploadAsset(asset2);

        assertNotNull(response);
        verify(assetRepository, times(1)).save(any(Asset.class));
    }

}
