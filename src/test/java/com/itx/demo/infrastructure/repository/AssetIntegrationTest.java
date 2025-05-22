package com.itx.demo.infrastructure.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itx.demo.boot.DemoApplication;
import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import com.itx.demo.infrastructure.restController.AssetRestController;
import com.itx.demo.infrastructure.restController.mapper.AssetDtoMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RequiredArgsConstructor
@ContextConfiguration(classes = DemoApplication.class)
public class AssetIntegrationTest {

    @Autowired
    private AssetRestController assetRestController;


    private AssetFileUploadRequestDTO request;

    @BeforeEach
    void setup() {
        request = new AssetFileUploadRequestDTO("file.txt", "image/png", "asfasfsafasfsafasfas");
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "admin",
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                )
        );

    }

    @Test
    void givenAsset_whenSave_thenAssetIsPersisted() throws IOException {

        assetRestController.uploadAsset(request);

        ResponseEntity<Object> response= assetRestController.getAssetsByFilter(null,null,null,null,"asc");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<Asset> assets = objectMapper.convertValue(response.getBody(), new TypeReference<List<Asset>>() {});

        assertNotNull(response.getBody());
        assertEquals(1,assets.size());
    }

}
