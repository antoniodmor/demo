package com.itx.demo.domain.repository;

import com.itx.demo.domain.entity.Asset;

import java.time.LocalDateTime;
import java.util.List;

public interface AssetRepository {
    public Asset save(Asset asset);
    public List<Asset> getAssetsByFilter(LocalDateTime uploadDateStart, LocalDateTime uploadDateEnd,
    String filename, String filetype, String sortDirection);
}
