package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.DocumentDetailDto;
import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;

public class DocumentDetailDtoMapper {
    public static DocumentDetailDto toDocumentDetailDto(Document document) {
        DocumentVersion lastVersion = Util.getLatestVersion(document);

        DocumentDetailDto dto = (
                (DocumentDetailDto)DocumentInfoDtoMapper.toDocumentInfoDto(document)
        )
        .setComments(document.getComments())
        .setFlag(lastVersion.getFlag());

        return dto;
    }
}
