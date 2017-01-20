package application.web.converter;

import application.core.model.Document;
import application.core.service.IUserService;
import application.web.dto.LightDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LightDocumentConverter extends Converter<Document, LightDocumentDTO> {
    @Autowired
    private IUserService userService;

    @Override
    public LightDocumentDTO toDTO(Document document) {
        return LightDocumentDTO.builder()
                .id(document.getId())
                .name(document.getName())
                .dateAdded(document.getDateAdded())
                .username(document.getUser().getUsername())
                .type(document.getType())
                .path(document.getPath())
                .build();
    }

    @Override
    public Document fromDTO(LightDocumentDTO lightDocumentDTO) {
        return Document.builder()
                .id(lightDocumentDTO.getId())
                .name(lightDocumentDTO.getName())
                .dateAdded(lightDocumentDTO.getDateAdded())
                .user(userService.readOne(lightDocumentDTO.getUsername()))
                .build();
    }
}
