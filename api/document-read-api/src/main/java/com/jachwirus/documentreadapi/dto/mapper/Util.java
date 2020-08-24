package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentHashTag;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.model.HashTag;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static List<String> getHashTags(List<DocumentHashTag> tags) {
        return tags.stream()
                .map(DocumentHashTag::getHashTag)
                .map(HashTag::getTagName)
                .collect(Collectors.toList());
    }

    public static DocumentVersion getLatestVersion(Document document) {
        List<DocumentVersion> versions = document.getVersions();
        DocumentVersion lastVersion = versions.get(versions.size() - 1);
        return lastVersion;
    }
}
