package com.itx.demo.infrastructure.mapper;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.infrastructure.entity.AssetEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface AssetMapper {

    AssetEntity assetToAssetEntity(Asset asset);

    Asset assetEntityToAsset(AssetEntity assetEntity);
}
