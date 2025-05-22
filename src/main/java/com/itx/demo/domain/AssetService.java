package com.itx.demo.domain;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for managing operations related to assets.
 * Provides methods to upload assets and retrieve filtered assets.
 */
public interface AssetService {

    /**
     * Uploads an asset to the system.
     *
     * @param request Object containing the data of the file to be uploaded.
     * @return A response containing the ID of the uploaded asset.
     * @throws IllegalArgumentException If the request is not properly formed.
     */
    String uploadAsset(Asset request);

    /**
     * Retrieves a list of assets filtered by the provided parameters.
     *
     * @param uploadDateStart Start date of the upload range (optional).
     * @param uploadDateEnd End date of the upload range (optional).
     * @param filename Name of the file (optional).
     * @param filetype Type of the file (optional).
     * @param sortDirection Sorting direction (ascending or descending) (optional).
     * @return A list of assets matching the specified filters.
     */
    List<Asset> getAssetsByFilter(LocalDateTime uploadDateStart, LocalDateTime uploadDateEnd,
                                  String filename, String filetype, String sortDirection);
}