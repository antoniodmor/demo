package com.itx.demo.infrastructure.restController.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a request to upload an asset file.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetFileUploadRequestDTO {
    private String filename;
    private String contentType;
    private String encodedFile;

}

