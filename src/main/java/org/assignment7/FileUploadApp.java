package org.assignment7;

public class FileUploadApp {

    public static void main(String[] args) {
        FileUploader uploader = new FileUploader();

        System.out.println("Uploading report.pdf (12 MB)...");
        try {
            uploader.uploadFile("report.pdf", 12.0);
            System.out.println("File uploaded successfully.");
        } catch (FileTooLargeException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nUploading movie.mp4 (30 MB)...");
        try {
            uploader.uploadFile("movie.mp4", 30.0);
            System.out.println("File uploaded successfully.");
        } catch (FileTooLargeException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nUploading image.jpg (25 MB)...");
        try {
            uploader.uploadFile("image.jpg", 25.0);
            System.out.println("File uploaded successfully.");
        } catch (FileTooLargeException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nUploading empty.txt (0 MB)...");
        try {
            uploader.uploadFile("empty.txt", 0.0);
            System.out.println("File uploaded successfully.");
        } catch (FileTooLargeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class FileTooLargeException extends Exception {
    public FileTooLargeException(String message) {
        super(message);
    }
}

class FileUploader {
    private static final double MAX_FILE_SIZE_MB = 25.0;

    public void uploadFile(String fileName, double fileSizeMB) throws FileTooLargeException {
        if (fileSizeMB > MAX_FILE_SIZE_MB) {
            throw new FileTooLargeException("File size exceeds the " + MAX_FILE_SIZE_MB + " MB limit.");
        }
    }
}

