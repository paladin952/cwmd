package application.core.service;


import application.core.model.dr.*;
import application.core.repository.DRDocumentRepository;
import application.core.utils.UserUtil;
import com.aspose.words.Document;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class DRDocumentServiceImpl implements DRDocumentService {

    @Autowired
    private DRDocumentRepository drDocumentRepository;

    @Override
    public void saveDocumentOnServer(Document document, String filename) {
        URL urlToResourses = DRDocumentServiceImpl.class.getClassLoader().getResource("");
        try {
            //TODO: get the current user logged in order to save files under personal folder
            document.save(urlToResourses.getPath() + "files/dr/" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer saveDocumentInDBFirstPart(Document document, String filename) {
        NodeCollection shapes = document.getChildNodes(NodeType.SHAPE, true);
        System.out.println("shapes.getCount() = " + shapes.getCount());

        DRDocument drDocument = new DRDocument();

        Iterator<Shape> it = ((Iterable<Shape>) shapes).iterator();

        DRPersonInfo drPersonInfo = new DRPersonInfo();
        drPersonInfo.setFullName(it.next().getText());
        drPersonInfo.setTitle(it.next().getText());
        drPersonInfo.setDepartment(it.next().getText());
        drPersonInfo.setPhoneNumber(it.next().getText());
        drPersonInfo.setEmail(it.next().getText().replaceAll("\\s+",""));
        drDocument.setDrPersonInfo(drPersonInfo);

        DRTravelInfo drTravelInfo = new DRTravelInfo();
        drTravelInfo.setTravelLocation(it.next().getText());
        drTravelInfo.setRoute(it.next().getText());
        drTravelInfo.setEventTime(it.next().getText());
        drTravelInfo.setDisplacementPeriod(it.next().getText());
        drTravelInfo.setMeansOfTransportation(it.next().getText());
        drTravelInfo.setTravelScope(it.next().getText());
        drTravelInfo.setCostBearer(it.next().getText());
        drDocument.setDrTravelInfo(drTravelInfo);

        DRTransportationCosts drTransportationCosts = new DRTransportationCosts();
        drTransportationCosts.setTransport1_1Amount(it.next().getText());
        drTransportationCosts.setTransport1_1Currency(it.next().getText());
        drTransportationCosts.setTransport1_1Funding(it.next().getText());

        drTransportationCosts.setTransport1_2_1Amount(it.next().getText());
        drTransportationCosts.setTransport1_2_1Funding(it.next().getText());

        drTransportationCosts.setTransport1_2_2Amount(it.next().getText());
        drTransportationCosts.setTransport1_2_2Currency(it.next().getText());
        drTransportationCosts.setTransport1_2_2Funding(it.next().getText());

        drTransportationCosts.setTransport1_3Amount(it.next().getText());
        drTransportationCosts.setTransport1_3Funding(it.next().getText());

        drTransportationCosts.setTransport1_4Amount(it.next().getText());
        drTransportationCosts.setTransport1_4Currency(it.next().getText());
        drTransportationCosts.setTransport1_4Funding(it.next().getText());
        drDocument.setDrTransportationCosts(drTransportationCosts);

        DRDailyCosts drDailyCosts = new DRDailyCosts();
        drDailyCosts.setDailyPayment2_1Amount(it.next().getText());
        drDailyCosts.setDailyPayment2_1Days(it.next().getText());
        drDailyCosts.setDailyPayment2_1Total(it.next().getText());
        drDailyCosts.setDailyPayment2_1Currency(it.next().getText());
        drDailyCosts.setDailyPayment2_1Funding(it.next().getText());

        drDailyCosts.setDailyPayment2_2Amount(it.next().getText());
        drDailyCosts.setDailyPayment2_2Days(it.next().getText());
        drDailyCosts.setDailyPayment2_2Funding(it.next().getText());

        drDailyCosts.setDailyPayment2_3Amount(it.next().getText());
        drDailyCosts.setDailyPayment2_3Months(it.next().getText());
        drDailyCosts.setDailyPayment2_3Total(it.next().getText());
        drDailyCosts.setDailyPayment2_3Currency(it.next().getText());
        drDailyCosts.setDailyPayment2_3Funding(it.next().getText());
        drDocument.setDrDailyCosts(drDailyCosts);

        DRHousingCosts drHousingCosts = new DRHousingCosts();
        drHousingCosts.setHousing3_1Amount(it.next().getText());
        drHousingCosts.setHousing3_1Days(it.next().getText());
        drHousingCosts.setHousing3_1Total(it.next().getText());
        drHousingCosts.setHousing3_1Currency(it.next().getText());
        drHousingCosts.setHousing3_1Funding(it.next().getText());

        drHousingCosts.setHousing3_2Amount(it.next().getText());
        drHousingCosts.setHousing3_2Days(it.next().getText());
        drHousingCosts.setHousing3_2Total(it.next().getText());
        drHousingCosts.setHousing3_2Currency(it.next().getText());
        drHousingCosts.setHousing3_2Funding(it.next().getText());
        drDocument.setDrHousingCosts(drHousingCosts);

        Date dateAdded = new Date();
        drDocument.setDateAdded(dateAdded);
        drDocument.setName("DR - " + dateAdded.toString());
        drDocument.setUser(UserUtil.getCurrentUser());

        DRDocument savedDocument = drDocumentRepository.save(drDocument);

        return savedDocument.getId();
    }

    @Override
    public void saveDocumentInDBSecondPart(Document document, Integer documentId) {
        NodeCollection shapes = document.getChildNodes(NodeType.SHAPE, true);
        System.out.println("shapes.getCount() = " + shapes.getCount());

        DRDocument drDocument = drDocumentRepository.findOne(documentId);

        Iterator<Shape> it = ((Iterable<Shape>) shapes).iterator();

        DROtherCosts drOtherCosts = new DROtherCosts();
        drOtherCosts.setOtherCosts4_1Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_1Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_1Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_2Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_2Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_2Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_3Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_3Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_3Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_4Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_4Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_4Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_5Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_5Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_5Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_6Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_6Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_7Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_7Currency(it.next().getText());
        drOtherCosts.setOtherCosts4_7Funding(it.next().getText());

        drOtherCosts.setOtherCosts4_8Amount(it.next().getText());
        drOtherCosts.setOtherCosts4_8Funding(it.next().getText());
        drDocument.setDrOtherCosts(drOtherCosts);

        DRTotalCosts drTotalCosts = new DRTotalCosts();
        drTotalCosts.setTotalSpending(it.next().getText());
        drTotalCosts.setAdvancePayment(it.next().getText());
        drDocument.setDrTotalCosts(drTotalCosts);

        DRBankInfo drBankInfo = new DRBankInfo();
        drBankInfo.setAccountOwner(it.next().getText());
        drBankInfo.setCnp(it.next().getText());
        drBankInfo.setHomeAddress(it.next().getText());
        drBankInfo.setBankName(it.next().getText());
        drBankInfo.setIban(it.next().getText());
        drDocument.setDrBankInfo(drBankInfo);

        drDocumentRepository.save(drDocument);
    }

    @Override
    public List<DRDocument> getDocuments(String username) {
        return drDocumentRepository.findByUser_Username(username);
    }
}
