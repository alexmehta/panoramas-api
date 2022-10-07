package com.server.panorama;

import org.springframework.content.commons.repository.ContentStore;

import java.util.UUID;

public interface PhotoStore extends ContentStore<PanoramaFrame, UUID> {
}
