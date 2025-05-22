package com.itx.demo.aplication.service;

import com.itx.demo.domain.AssetService;
import com.itx.demo.domain.entity.Asset;
import com.itx.demo.domain.repository.AssetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AssetServiceImpl  implements AssetService {

    private final AssetRepository assetRepository;

    @Transactional
    public String uploadAsset(Asset request) {
    log.info("Starting asset processing... {}", request);

    if (request.getEncodedFile() == null || request.getFilename() == null || request.getContentType() == null) {
        log.error("the request is not formed correctly {}", request);
        throw new IllegalArgumentException("the request is not formed correctly");
    }
    request.setSize((long) request.getEncodedFile().length());


        return assetRepository.save(request).getId().toString();
    }


    public List<Asset> getAssetsByFilter(LocalDateTime uploadDateStart, LocalDateTime uploadDateEnd,
                                         String filename, String filetype, String sortDirection) {

        return assetRepository.getAssetsByFilter(uploadDateStart, uploadDateEnd, filename, filetype, sortDirection);
    }
}
