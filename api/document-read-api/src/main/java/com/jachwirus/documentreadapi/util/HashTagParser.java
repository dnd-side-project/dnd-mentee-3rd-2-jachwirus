package com.jachwirus.documentreadapi.util;

import com.jachwirus.documentreadapi.model.DocumentHashTag;
import com.jachwirus.documentreadapi.model.HashTag;

import java.util.List;
import java.util.stream.Collectors;

public class HashTagParser {
    public static List<String> extractHashTagsName(List<DocumentHashTag> tags) {
        return tags.stream()
                .map(DocumentHashTag::getHashTag)
                .map(HashTag::getTagName)
                .collect(Collectors.toList());
    }
}
