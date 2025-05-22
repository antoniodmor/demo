package com.itx.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents an asset entity with its metadata and encoded file content.
 * This class is used to persist asset data in the database.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String contentType;
    private Long size;
    private LocalDateTime uploadDate;

    private String encodedFile;

    public AssetEntity(String filename, String contentType, Long size, LocalDateTime uploadDate, String encodedFile) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.uploadDate = uploadDate;
        this.encodedFile = encodedFile;
    }

}