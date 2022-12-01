package dto;

import lombok.Builder;
import lombok.Data;
import model.Event;

@Data
@Builder
public class EventDto {

    private Long id;
    private Long created;
    private Long updated;
    private FileDto fileDto;
    private UserDto userDto;

    public static EventDto getEntity(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .created(Long.valueOf(event.getCreated()))
                .updated(Long.valueOf(event.getUpdated()))
                .build();
    }

}
