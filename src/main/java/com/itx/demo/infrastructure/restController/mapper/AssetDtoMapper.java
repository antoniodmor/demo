package com.itx.demo.infrastructure.restController.mapper;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.restController.dto.AssetDTO;
import com.itx.demo.infrastructure.restController.dto.AssetFileUploadRequestDTO;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper (componentModel = "spring")
public interface AssetDtoMapper {
    Asset toAsset(AssetFileUploadRequestDTO asset, LocalDateTime uploadDate);
    AssetDTO toAssetDTO(Asset asset);

}
