package print.print.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponceDTO {
    private String name;
    private String address;
    private LocalDateTime date;
    private Integer duration;
    private Integer capacity;
    private List<String> participants;


}
