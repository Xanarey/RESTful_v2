package dto;

import lombok.Builder;
import lombok.Data;
import model.File;

@Data
@Builder
public class FileDto {

    private Long id;
    private String name;
    private String url;

    public static FileDto getEntity(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .url(file.getUrl())
                .build();
    }
}
