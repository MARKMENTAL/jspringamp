package com.app.JSpringAmp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final Path uploadDir = Paths.get("uploads");
    private final Path metadataFile = uploadDir.resolve("tracklist.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<TrackMeta> tracks = new CopyOnWriteArrayList<>();

    public UploadController() throws IOException {
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void loadTrackMetadata() {
        if (Files.exists(metadataFile)) {
            try {
                List<TrackMeta> saved = mapper.readValue(metadataFile.toFile(), new TypeReference<>() {});
                tracks.addAll(saved);
            } catch (IOException e) {
                System.err.println("Failed to load track metadata: " + e.getMessage());
            }
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist) {

        try {
            String filename = file.getOriginalFilename();
            if (filename == null || filename.isBlank()) {
                return ResponseEntity.badRequest().body("Invalid filename");
            }

            Path filePath = uploadDir.resolve(filename);
            file.transferTo(filePath);

            TrackMeta track = new TrackMeta(filename, title, artist);
            tracks.add(track);
            saveTrackMetadata();

            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/tracks")
    public List<Map<String, String>> listTracks() {
        List<Map<String, String>> list = new ArrayList<>();
        for (TrackMeta track : tracks) {
            Map<String, String> map = new HashMap<>();
            map.put("url", "/uploads/" + track.filename);
            map.put("title", track.title);
            map.put("artist", track.artist);
            list.add(map);
        }
        return list;
    }

    private void saveTrackMetadata() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(metadataFile.toFile(), tracks);
        } catch (IOException e) {
            System.err.println("Failed to save track metadata: " + e.getMessage());
        }
    }

    static class TrackMeta {
        public String filename;
        public String title;
        public String artist;

        public TrackMeta() {} // Required by Jackson

        public TrackMeta(String filename, String title, String artist) {
            this.filename = filename;
            this.title = title;
            this.artist = artist;
        }
    }
}

