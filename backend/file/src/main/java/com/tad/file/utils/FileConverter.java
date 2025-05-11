package com.tad.file.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileConverter {

    public static MultiPartFileImpl convertBytesToMultipartFile(byte[] content,
                                                            String name,
                                                            String originalFilename,
                                                            String contentType) {
        return new MultiPartFileImpl(name, originalFilename, contentType, content);
    }
}
