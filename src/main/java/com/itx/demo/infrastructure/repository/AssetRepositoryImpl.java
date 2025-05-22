package com.itx.demo.infrastructure.repository;

import com.itx.demo.domain.entity.Asset;
import com.itx.demo.domain.repository.AssetRepository;
import com.itx.demo.infrastructure.AssetRepositoryJpa;
import com.itx.demo.infrastructure.entity.AssetEntity;
import com.itx.demo.infrastructure.mapper.AssetMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the AssetRepository interface.
 */
@Repository
@RequiredArgsConstructor
public class AssetRepositoryImpl  implements AssetRepository {
    private final AssetRepositoryJpa assetRepositoryJpa;
    private final AssetMapper assetMapper;

    /**
     * Saves the given asset to the database.
     *
     * @param asset the asset to save
     * @return the saved asset
     */
    @Override
    public Asset save(Asset asset) {
        return  assetMapper.assetEntityToAsset(assetRepositoryJpa.save(assetMapper.assetToAssetEntity(asset)));
    }

    /**
     * Retrieves a list of assets filtered by the provided parameters.
     *
     * @param uploadDateStart Start date of the upload range (optional).
     * @param uploadDateEnd End date of the upload range (optional).
     * @param filename Name of the file to filter by (optional).
     * @param filetype Type of the file to filter by (optional).
     * @param sortDirection Direction to sort the results by (optional).
     * @return a list of assets that match the provided filters
     */
    @Override
    public List<Asset> getAssetsByFilter(LocalDateTime uploadDateStart, LocalDateTime uploadDateEnd, String filename, String filetype, String sortDirection) {

        Specification<AssetEntity> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (uploadDateStart != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("uploadDate"), uploadDateStart));
            }
            if (uploadDateEnd != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("uploadDate"), uploadDateEnd));
            }
            if (filename != null) {
                predicates.add(criteriaBuilder.like(root.get("filename"), "%" + filename + "%"));
            }
            if (filetype != null) {
                predicates.add(criteriaBuilder.equal(root.get("contentType"), filetype));
            }
            if ("asc".equalsIgnoreCase(sortDirection)) {
                query.orderBy(criteriaBuilder.asc(root.get("uploadDate")));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("uploadDate")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return assetRepositoryJpa.findAll(spec).stream().map(assetMapper::assetEntityToAsset).toList();
    }

}
