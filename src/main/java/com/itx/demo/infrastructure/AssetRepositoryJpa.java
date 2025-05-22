package com.itx.demo.infrastructure;

import com.itx.demo.infrastructure.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the AssetEntity class.
 */
@Repository
public interface AssetRepositoryJpa extends JpaRepository<AssetEntity, Long>, JpaSpecificationExecutor<AssetEntity> {
}