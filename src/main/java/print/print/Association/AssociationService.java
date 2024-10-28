package print.print.Association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import print.print.Association.AssociationEntity;
import print.print.Association.DTO.AssociationMapper;
import print.print.Association.DTO.AssociationRequestDTO;
import print.print.Association.DTO.AssociationResponseDTO;
import print.print.Auth.Model.AppUser;
import print.print.Auth.Rpository.AppUserRepository;
import print.print.Auth.Service.AppUserService;


@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associationRepository;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    // Création d'une nouvelle association
    public AssociationResponseDTO createAssociation(AssociationRequestDTO requestDTO) {
        AssociationEntity association = associationMapper.convertToEventEntity(requestDTO);
        AssociationEntity savedAssociation = associationRepository.save(association);
        return associationMapper.convertToEventResponse(savedAssociation);
    }

    // Lecture d'une association par son ID
    public AssociationResponseDTO getAssociationById(Long id) {
        AssociationEntity association = associationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association not found"));
        return associationMapper.convertToEventResponse(association);
    }

    // Mise à jour d'une association
    public AssociationResponseDTO updateAssociation(Long id, AssociationRequestDTO requestDTO) {
        AssociationEntity existingAssociation = associationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association not found"));

        existingAssociation.setNom(requestDTO.getNom());
        existingAssociation.setDescription(requestDTO.getDescription());

        AssociationEntity updatedAssociation = associationRepository.save(existingAssociation);
        return associationMapper.convertToEventResponse(updatedAssociation);
    }

    // Suppression d'une association
    public void deleteAssociation(Long id) {
        associationRepository.deleteById(id);
    }

    // Ajouter un utilisateur comme membre de l'association
    @Transactional
    public void addMemberToAssociation(Long associationId, Long userId) {
        AssociationEntity association = associationRepository.findById(associationId)
                .orElseThrow(() -> new RuntimeException("Association not found"));

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        association.getMembres().add(user);
        associationRepository.save(association);
    }

    // Changer le président de l'association
    @Transactional
    public void changePresident(Long associationId, String newPresidentUsername) {
        AssociationEntity association = associationRepository.findById(associationId)
                .orElseThrow(() -> new RuntimeException("Association not found"));

        AppUser newPresident = appUserService.UserByUsername(newPresidentUsername);
        association.setPresident(newPresident);
        associationRepository.save(association);
    }
}

