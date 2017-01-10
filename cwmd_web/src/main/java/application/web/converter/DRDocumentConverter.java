package application.web.converter;

import application.core.model.dr.DRDocument;
import application.core.service.DRDocumentService;
import application.core.service.IUserService;
import application.web.dto.DRDocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DRDocumentConverter extends Converter<DRDocument, DRDocumentDto> {

    @Autowired
    private DRDocumentService drDocumentService;

    @Autowired
    private IUserService userService;


    @Override
    public DRDocumentDto toDTO(DRDocument drDocument) {
        return DRDocumentDto.builder()
                .id(drDocument.getId())
                .name(drDocument.getName())
                .username(drDocument.getUser().getUsername())
                .dateAdded(drDocument.getDateAdded())
                .drPersonInfoId(drDocument.getDrPersonInfo().getDrPersonInfoId())
                .drTravelInfoId(drDocument.getDrTravelInfo().getDrTravelInfoId())
                .drTransportationCostsId(drDocument.getDrTransportationCosts().getDrTransportationCostsId())
                .drDailyCostsId(drDocument.getDrDailyCosts().getDrDailyCostsId())
                .drHousingCostsId(drDocument.getDrHousingCosts().getDrHousingCostsId())
                .drOtherCostsId(drDocument.getDrOtherCosts().getDrOtherCostsId())
                .drTotalCostsId(drDocument.getDrTotalCosts().getDrTotalCostsId())
                .drBankInfoId(drDocument.getDrBankInfo().getDrBankInfoId())
                .build();
    }

    @Override
    public DRDocument fromDTO(DRDocumentDto drDocumentDto) {
        DRDocument drDocument = (DRDocument) DRDocument.builder()
                .id(drDocumentDto.getId())
                .name(drDocumentDto.getName())
                .dateAdded(drDocumentDto.getDateAdded())
                .user(userService.readOne(drDocumentDto.getUsername())).build();

        drDocument.setDrPersonInfo(drDocumentService.getDrPersonInfo(drDocumentDto.getDrPersonInfoId()));
        drDocument.setDrTravelInfo(drDocumentService.getDrTravelInfo(drDocumentDto.getDrTravelInfoId()));
        drDocument.setDrTransportationCosts(drDocumentService.getDrTransportationCosts(drDocumentDto.getDrTransportationCostsId()));
        drDocument.setDrDailyCosts(drDocumentService.getDrDailyCosts(drDocumentDto.getDrDailyCostsId()));
        drDocument.setDrHousingCosts(drDocumentService.getDrHousingCosts(drDocumentDto.getDrHousingCostsId()));
        drDocument.setDrOtherCosts(drDocumentService.getDrOtherCosts(drDocumentDto.getDrOtherCostsId()));
        drDocument.setDrTotalCosts(drDocumentService.getDrTotalCosts(drDocumentDto.getDrTotalCostsId()));
        drDocument.setDrBankInfo(drDocumentService.getDrBankInfo(drDocumentDto.getDrBankInfoId()));

        return drDocument;
    }
}
