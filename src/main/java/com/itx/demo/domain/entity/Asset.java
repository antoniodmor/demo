package com.itx.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Represents an asset with its metadata and encoded file content.
 * This class is used to transfer asset data within the application.
 */
public class Asset {

    private Long id;
    private String filename;
    private String contentType;
    private Long size;
    private LocalDateTime uploadDate;

    private String encodedFile;

    /**
     * Creates a new Asset object with the given parameters.
     * @param filename the name of the file
     * @param contentType the content type of the file
     * @param size the size of the file
     * @param uploadDate the date when the file was uploaded
     * @param encodedFile the encoded file content
     */
    public Asset(String filename, String contentType, Long size, LocalDateTime uploadDate, String encodedFile) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.uploadDate = uploadDate;
        this.encodedFile = encodedFile;
    }
}