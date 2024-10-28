package print.print.Association.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.print.Event.EventResponceDTO;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationResponseDTO  {
    private Long id;
    private String nom;
    private String description;
    private List<String> membres;
    private String president;
    private List<EventResponceDTO> evenements;
}
