package print.print.Association.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.print.Association.AssociationEntity;
import print.print.Auth.Model.AppUser;
import print.print.Auth.Rpository.AppUserRepository;
import print.print.Auth.Service.AppUserService;
import print.print.Event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociationMapper {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AppUserService appUserService;

    public AssociationResponseDTO convertToEventResponse(AssociationEntity association) {
        List<String> participantNames = association.getMembres().stream()
                .map(AppUser::getFirstName) // ou un autre attribut de l'utilisateur
                .collect(Collectors.toList());

        List<EventResponceDTO> evenements = association.getEvenements().stream()
                .map(eventMapper::convertToEventResponse)
                .collect(Collectors.toList());

        return new AssociationResponseDTO(
                association.getId(),
                association.getNom(),
                association.getDescription(),
                participantNames,
                association.getPresident().getEmail(),
                evenements
        );
    }

    public AssociationEntity convertToEventEntity(AssociationRequestDTO requestDTO) {
        List<AppUser> membres = requestDTO.getPresidentsIds().stream()
                .map(appUserRepository::findById)
                .filter(Optional::isPresent) // Filtrer uniquement les valeurs présentes
                .map(Optional::get) // Extraire la valeur de l'Optional
                .collect(Collectors.toList());

        List<EventEntity> evenements = requestDTO.getEvenementsIds().stream()
                .map(eventRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
         AppUser president= appUserService.UserByUsername(requestDTO.getPresident());
        return new AssociationEntity(
                requestDTO.getNom(),
                requestDTO.getDescription(),
                membres,
                president,
                evenements
        );
    }
}
