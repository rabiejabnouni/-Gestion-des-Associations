package print.print.Association.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.print.Auth.Model.AppUser;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationRequestDTO {
    private String nom;
    private String description;
    private List<Long> presidentsIds;
    private String president;
    private List<Long> evenementsIds;
}
