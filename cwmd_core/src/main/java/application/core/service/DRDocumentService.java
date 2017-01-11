package application.core.service;


import application.core.model.User;
import application.core.model.dr.*;
import application.core.repository.UserRepository;
import application.core.repository.dr.*;
import application.core.utils.UserUtil;
import com.aspose.words.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class DRDocumentService {

    @Autowired
    private DRDocumentRepository drDocumentRepository;

    @Autowired
    private DRBankInfoRepository drBankInfoRepository;

    @Autowired
    private DRDailyCostsRepository drDailyCostsRepository;

    @Autowired
    private DRHousingCostsRepository drHousingCostsRepository;

    @Autowired
    private DROtherCostsRepository drOtherCostsRepository;

    @Autowired
    private DRPersonInforRepository drPersonInforRepository;

    @Autowired
    private DRTotalCostsRepository drTotalCostsRepository;

    @Autowired
    private DRTransportationCostsRepository drTransportationCostsRepository;

    @Autowired
    private DRTravelInfoRepository drTravelInfoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<DRDocument> getDocumentsByUsername(String username) {
        return drDocumentRepository.findByUser_Username(username);
    }

    public List<DRDocument> getAllDocuments() {
        return drDocumentRepository.findAll();
    }

    public List<DRDocument> getPartOfAFlowDocumentsByUsername(String username) {
        return drDocumentRepository.findByUser_UsernameAndIsPartOfFlow(username, true);
    }

    public List<DRDocument> getAllPartOfAFlowDocuments() {
        return drDocumentRepository.findByIsPartOfFlow(true);
    }

    public void saveDocumentOnServer(Document document, HttpServletRequest request) {
        URL urlToResourses = DRDocumentService.class.getClassLoader().getResource("");
        try {
            LocalDate date = LocalDate.now();
            String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
            String username = UserUtil.getCurrentUsername(request);
            String path = urlToResourses.getPath();
            document.save(path + "files/" + username + "/dr/DR - " + dateString, SaveFormat.DOCX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer saveDocumentInDBFirstPart(Document document, HttpServletRequest request) throws ParseException {
        NodeCollection shapes = document.getChildNodes(NodeType.SHAPE, true);
        System.out.println("shapes.getCount() = " + shapes.getCount());

        DRDocument drDocument = new DRDocument();

        Iterator<Shape> it = ((Iterable<Shape>) shapes).iterator();

        DRPersonInfo drPersonInfo = new DRPersonInfo();
        drPersonInfo.setFullName(it.next().getText());
        drPersonInfo.setTitle(it.next().getText());
        drPersonInfo.setDepartment(it.next().getText());
        drPersonInfo.setPhoneNumber(it.next().getText());
        drPersonInfo.setEmail(it.next().getText().replaceAll("\\s+", ""));
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

        LocalDate date = LocalDate.now();
        String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
        Date dateAdded = Date.valueOf(date.plusDays(1));
        drDocument.setDateAdded(dateAdded);
        drDocument.setName("DR - " + dateString);
        String username = UserUtil.getCurrentUsername(request);
        User user = userRepository.findOne(username);
        drDocument.setUser(user);
        //// FIXME: 20.12.2016 set the correct status as soon as it known how the integer representation of the status is mapped
        drDocument.setStatus(1);
        drDocument.setVersion(0.1f);
        drDocument.setPartOfFlow(false);

        URL urlToResourses = DRDocumentService.class.getClassLoader().getResource("");
        drDocument.setPath(urlToResourses.getPath() + "files/" + username + "/dr/" + "DR - " + dateString);

        DRDocument savedDocument = drDocumentRepository.save(drDocument);

        return savedDocument.getId();
    }

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

    public DRDocument getDocument(Integer id) {
        return drDocumentRepository.findOne(id);
    }

    public DRBankInfo getDrBankInfo(Integer id) {
        return drBankInfoRepository.findOne(id);
    }

    public DRDailyCosts getDrDailyCosts(Integer id) {
        return drDailyCostsRepository.findOne(id);
    }

    public DRHousingCosts getDrHousingCosts(Integer id) {
        return drHousingCostsRepository.findOne(id);
    }

    public DROtherCosts getDrOtherCosts(Integer id) {
        return drOtherCostsRepository.findOne(id);
    }

    public DRPersonInfo getDrPersonInfo(Integer id) {
        return drPersonInforRepository.findOne(id);
    }

    public DRTotalCosts getDrTotalCosts(Integer id) {
        return drTotalCostsRepository.findOne(id);
    }

    public DRTransportationCosts getDrTransportationCosts(Integer id) {
        return drTransportationCostsRepository.findOne(id);
    }

    public DRTravelInfo getDrTravelInfo(Integer id) {
        return drTravelInfoRepository.findOne(id);
    }
}
