package com.server.panorama;

import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<PanoramaFrame, Long> {
}
