package application.web.converter;

import application.core.model.rn.RNDocument;
import application.core.model.rn.RNProduct;
import application.core.service.IUserService;
import application.core.service.RNDocumentService;
import application.web.dto.RNDocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RNDocumentConverter extends Converter<RNDocument, RNDocumentDto> {

    @Autowired
    private RNDocumentService rnDocumentService;

    @Autowired
    private IUserService userService;

    @Override
    public RNDocumentDto toDTO(RNDocument rnDocument) {
        return RNDocumentDto.builder()
                .id(rnDocument.getId())
                .name(rnDocument.getName())
                .dateAdded(rnDocument.getDateAdded())
                .username(rnDocument.getUser().getUsername())
                .budget(rnDocument.getBudget())
                .personalFunds(rnDocument.getPersonalFunds())
                .rnOthersId(rnDocument.getRnOthers().getRnOthersId())
                .rnResearchId(rnDocument.getRnResearch().getRnResearchId())
                .rnSponsorsId(rnDocument.getRnSponsors().getRnSponsorsId())
                .rnTotalId(rnDocument.getRnTotal().getRnTotalId())
                .rnProductIds(rnDocument.getRnProducts().stream().map(RNProduct::getRnProductId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public RNDocument fromDTO(RNDocumentDto rnDocumentDto) {
        RNDocument rnDocument = (RNDocument) RNDocument.builder()
                .id(rnDocumentDto.getId())
                .name(rnDocumentDto.getName())
                .dateAdded(rnDocumentDto.getDateAdded())
                .user(userService.readOne(rnDocumentDto.getUsername())).build();

        rnDocument.setBudget(rnDocumentDto.getBudget());
        rnDocument.setPersonalFunds(rnDocumentDto.getPersonalFunds());
        rnDocument.setRnResearch(rnDocumentService.getRnResearch(rnDocumentDto.getRnResearchId()));
        rnDocument.setRnSponsors(rnDocumentService.getRnSponsors(rnDocumentDto.getRnSponsorsId()));
        rnDocument.setRnOthers(rnDocumentService.getRnOthers(rnDocumentDto.getRnOthersId()));
        rnDocument.setRnTotal(rnDocumentService.getRnTotal(rnDocumentDto.getRnTotalId()));
        rnDocument.setRnProducts(rnDocumentDto.getRnProductIds().stream().map(integer -> rnDocumentService.getRnProduct(integer)).collect(Collectors.toList()));

        return rnDocument;
    }
}
