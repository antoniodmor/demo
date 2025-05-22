package com.itx.demo.infrastructure.restController.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response to an asset file upload request.
 */
@Getter
@Setter
@AllArgsConstructor
public class AssetFileUploadResponseDTO {
    private String id;
}