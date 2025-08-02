package com.ridelog.app.domain.services;

import com.ridelog.app.dao.model.ImagePark;
import com.ridelog.app.dao.model.Park;
import com.ridelog.app.dao.repositories.ParkRepository;
import com.ridelog.app.ui.models.ParkDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParkService {

    @Autowired
    private ParkRepository parqueRepository;

    public ParkDTO createParque(String name, String location, String description) {
        Park parque = new Park(name, location, description);
        Park savedParque = parqueRepository.save(parque);
        return convertToDTO(savedParque);
    }

    @Transactional()
    public Optional<ParkDTO> getParqueById(Long id) {
        return parqueRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional()
    public Page<ParkDTO> getAllParques(Pageable pageable) {
        return parqueRepository.findAllParquesDTO(pageable);
    }

    @Transactional()
    public Page<ParkDTO> searchParques(String query, Pageable pageable) {
        return parqueRepository.findByNameOrLocationContainingIgnoreCase(query, query, pageable)
                .map(this::convertToDTO);
    }

    @Transactional()
    public List<ParkDTO> getVisitedParques(List<Long> parkIds) {
        return parqueRepository.findAllByIdIn(parkIds)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ParkDTO updateParque(Long id, String name, String location, String description) {
        Park parque = parqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Park not found"));

        parque.setName(name);
        parque.setLocation(location);
        parque.setDescription(description);

        Park savedParque = parqueRepository.save(parque);
        return convertToDTO(savedParque);
    }

    public void deleteParque(Long id) {
        if (!parqueRepository.existsById(id)) {
            throw new RuntimeException("Park not found");
        }
        parqueRepository.deleteById(id);
    }

    private ParkDTO convertToDTO(Park parque) {
        ParkDTO dto = new ParkDTO(parque.getId(), parque.getName(),
                parque.getLocation(), parque.getDescription());

        // Set image URLs
        List<String> imageUrls = parque.getImagenes().stream()
                .map(ImagePark::getUrl)
                .toList();
        dto.setImageUrls(imageUrls);

        // Set attractions count
        dto.setAtraccionesCount(parque.getAtracciones().size());

        // Set average rating
        Double averageRating = parqueRepository.getAverageRatingByParqueId(parque.getId());
        dto.setAverageRating(averageRating);

        return dto;
    }
}
