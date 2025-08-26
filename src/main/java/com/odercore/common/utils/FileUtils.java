package com.odercore.common.utils;

import com.odercore.minio.MinioService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUtils {

    private FileUtils() {
    }

    public static <T extends HasFileURLs> void uploadFilesToLicense(
            T license,
            UUID licenseId,
            List<MultipartFile> files,
            MinioService minioService,
            String bucket,
            String prefix
    ) throws Exception {
        int startIndex = getNextFileIndex(license.getFileURLs());

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String fileName = buildFileName(licenseId, file, startIndex, i, prefix);
            minioService.uploadFile(bucket, fileName, file);
            license.getFileURLs().add(fileName);
        }
    }

    public static int getNextFileIndex(List<String> fileURLs) {
        if (fileURLs == null || fileURLs.isEmpty()) return 0;
        TreeSet<Integer> usedIndexes = fileURLs.stream()
                .map(FileUtils::extractFileIndex)
                .collect(Collectors.toCollection(TreeSet::new));

        int index = 0;
        while (usedIndexes.contains(index)) {
            index++;
        }
        return index;
    }

    public static int extractFileIndex(String fileName) {
        int openIdx = fileName.lastIndexOf('(');
        int closeIdx = fileName.lastIndexOf(')');
        if (openIdx != -1 && closeIdx != -1 && closeIdx > openIdx) {
            try {
                return Integer.parseInt(fileName.substring(openIdx + 1, closeIdx));
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    public static String buildFileName(UUID licenseId, MultipartFile file, int startIndex, int currentIndex, String prefix) {
        String extension = getFileExtension(file);
        if (startIndex == 0 && currentIndex == 0) {
            return prefix + "-" + licenseId + extension;
        }
        return prefix + "-" + licenseId + "(" + (startIndex + currentIndex) + ")" + extension;
    }

    public static String getFileExtension(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName != null && originalName.contains(".")) {
            return originalName.substring(originalName.lastIndexOf('.'));
        }
        return "";
    }

}
