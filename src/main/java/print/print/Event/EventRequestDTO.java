package print.print.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    private String name;
    private String address;
    private Integer duration;
    private Integer capacity;
    private List<String> participants;
}
