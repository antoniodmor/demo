package com.itx.demo.infrastructure.repository;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.AssetRepositoryJpa;
import com.itx.demo.infrastructure.entity.AssetEntity;
import com.itx.demo.infrastructure.mapper.AssetMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AssetRepositoryImplTest {

    @Mock
    private AssetRepositoryJpa assetRepositoryJpa;

    @Spy
    private AssetMapperImpl assetMapper;

    @InjectMocks
    private AssetRepositoryImpl assetRepository;

    @BeforeEach
    void setup() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "admin",
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                )
        );

    }

    @Test
    void testSave() {
        Asset asset = new Asset("image.png", "image/png", 100L, LocalDateTime.now(), "fafjkasbnfkasbfkasbfas");
        AssetEntity assetEntity = new AssetEntity("image.png","image/png", 100L, LocalDateTime.now(), "fafjkasbnfkasbfkasbfas");
        assetEntity.setId(1L);


        when(assetRepositoryJpa.save(any())).thenReturn(assetEntity);

        Asset savedAsset = assetRepository.save(asset);

        assertNotNull(savedAsset);
        assertEquals(1L,savedAsset.getId());

        verify(assetRepositoryJpa, times(1)).save(any());
    }

    @Test
    void testGetAssetsByFilter() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        String filename = "image.png";
        String filetype = "image/png";
        String sortDirection = "ASC";

        AssetEntity assetEntity = new AssetEntity();
        assetEntity.setId(1L);
        List<AssetEntity> assetEntities = Collections.singletonList(assetEntity);
        Asset asset = new Asset(filename, filetype, 100L, LocalDateTime.now(), "fafjkasbnfkasbfkasbfas");

        when(assetRepositoryJpa.findAll(any(Specification.class))).thenReturn(assetEntities);

        List<Asset> result = assetRepository.getAssetsByFilter(startDate, endDate, filename, filetype, sortDirection);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(assetRepositoryJpa, times(1)).findAll(any(Specification.class));
    }

}