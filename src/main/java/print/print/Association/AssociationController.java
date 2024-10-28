package print.print.Association;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.print.Association.DTO.AssociationRequestDTO;
import print.print.Association.DTO.AssociationResponseDTO;



@RestController
@RequestMapping("/api/associations")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    // Créer une nouvelle association
    @PostMapping
    public ResponseEntity<AssociationResponseDTO> createAssociation(@RequestBody AssociationRequestDTO requestDTO) {
        AssociationResponseDTO responseDTO = associationService.createAssociation(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Obtenir une association par ID
    @GetMapping("/getById")
    public ResponseEntity<AssociationResponseDTO> getAssociationById(@RequestParam Long id) {
        AssociationResponseDTO responseDTO = associationService.getAssociationById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Mettre à jour une association
    @PutMapping("/update")
    public ResponseEntity<AssociationResponseDTO> updateAssociation(@RequestParam Long id, @RequestBody AssociationRequestDTO requestDTO) {
        AssociationResponseDTO responseDTO = associationService.updateAssociation(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Supprimer une association
    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteAssociation(@RequestParam Long id) {
        associationService.deleteAssociation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Ajouter un membre à une association
    @PostMapping("/addMembre")
    public ResponseEntity<Void> addMemberToAssociation(@RequestParam Long associationId, @RequestParam Long userId) {
        associationService.addMemberToAssociation(associationId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Changer le président de l'association
    @PutMapping("/changePresident")
    public ResponseEntity<Void> changePresident(@RequestParam Long associationId, @RequestParam String newPresidentUsername) {
        associationService.changePresident(associationId, newPresidentUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
