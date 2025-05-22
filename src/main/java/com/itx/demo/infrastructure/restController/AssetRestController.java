package com.itx.demo.infrastructure.restController;


import com.itx.demo.domain.AssetService;
import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadResponseDTO;
import com.itx.demo.infrastructure.restController.mapper.AssetDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller for managing assets.
 */
@RestController
@RequestMapping("/api/mgmt/1/assets")
@RequiredArgsConstructor
@Slf4j
public class AssetRestController {

    private final AssetService assetService;

    private final AssetDtoMapper assetDtoMapper;

    /**
     * Uploads an asset to the system.
     *
     * @param request Object containing the data of the file to be uploaded.
     * @return A response containing the ID of the uploaded asset.
     * @throws IllegalArgumentException If the request is not properly formed.
     */
    @PostMapping("/actions/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> uploadAsset(@RequestBody AssetFileUploadRequestDTO request) {
        log.info("Asset uploaded request: {}", request);

        String response = assetService.uploadAsset(this.assetDtoMapper.toAsset(request, LocalDateTime.now()));
        log.info("Asset uploaded successfully with id: {}", response);
        return ResponseEntity.accepted().body(new AssetFileUploadResponseDTO(response));
    }

    /**
     * Retrieves a list of assets filtered by the provided parameters.
     *
     * @param uploadDateStart Start date of the upload range (optional).
     * @param uploadDateEnd   End date of the upload range (optional).
     * @param filename        Name of the file to filter by (optional).
     * @param filetype        Type of the file to filter by (optional).
     * @param sortDirection   Direction to sort the results by (optional).
     * @return A list of assets filtered by the provided parameters.
     */
    @GetMapping({"", "/"})
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAssetsByFilter(
            @RequestParam(required = false) LocalDateTime uploadDateStart,
            @RequestParam(required = false) LocalDateTime uploadDateEnd,
            @RequestParam(required = false) String filename,
            @RequestParam(required = false) String filetype,
            @RequestParam(required = false) String sortDirection) {

        log.info("Asset filtered request: uploadDateStart: {}, uploadDateEnd: {}, filename: {}, filetype: {}, sortDirection: {}",
                uploadDateStart, uploadDateEnd, filename, filetype, sortDirection);;

        List<Asset> assets = assetService.getAssetsByFilter(uploadDateStart, uploadDateEnd, filename, filetype, sortDirection);
        log.info("Assets filtered successfully: {}", assets);
        return ResponseEntity.status(200).body(assets.stream().map(assetDtoMapper::toAssetDTO).toList());

    }


}