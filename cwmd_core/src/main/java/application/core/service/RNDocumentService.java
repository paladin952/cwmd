package application.core.service;


import application.core.model.rn.*;
import application.core.repository.rn.*;
import application.core.utils.UserUtil;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RNDocumentService {
    @Autowired
    private RNDocumentRepository rnDocumentRepository;

    @Autowired
    private RNOthersRepository rnOthersRepository;

    @Autowired
    private RNProductRepository rnProductRepository;

    @Autowired
    private RNResearchRepository rnResearchRepository;

    @Autowired
    private RNSponsorsRepository rnSponsorsRepository;

    @Autowired
    private RNTotalRepository rnTotalRepository;

    public List<RNDocument> getDocuments(String username) {
        return rnDocumentRepository.findByUser_Username(username);
    }

    public void saveDocumentOnDisk(Workbook workbook) {
        URL urlToResourses = DRDocumentService.class.getClassLoader().getResource("");
        try {
            LocalDate date = LocalDate.now();
            String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
            //TODO: get the current user logged in order to save files under personal folder
            workbook.save(urlToResourses.getPath() + "files/rn/" + "RN - " + dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer saveDocumentInDB(Workbook workbook, String filename) throws ParseException {
        Cells cells = workbook.getWorksheets().get(0).getCells();

        RNDocument rnDocument = new RNDocument();

        Float budget = cells.get("D5").getFloatValue();
        rnDocument.setBudget(budget);

        Float personalFunds = cells.get("D6").getFloatValue();
        rnDocument.setPersonalFunds(personalFunds);

        RNResearch rnResearch = new RNResearch();

        Cell b8 = cells.get("B8");
        if (b8.getValue() != null) {
            rnResearch.setNationalFunds1Identification(b8.getStringValue());
            rnResearch.setNationalFunds1(cells.get("D8").getFloatValue());
        }
        Cell b9 = cells.get("B9");
        if (b9.getValue() != null) {
            rnResearch.setNationalFunds2Identification(b9.getStringValue());
            rnResearch.setNationalFunds2(cells.get("D9").getFloatValue());
        }
        Cell b10 = cells.get("B10");
        if (b10.getValue() != null) {
            rnResearch.setTernaryContractsIdentification(b10.getStringValue());
            rnResearch.setTernaryContracts(cells.get("D10").getFloatValue());
        }
        rnDocument.setRnResearch(rnResearch);

        RNSponsors rnSponsors = new RNSponsors();
        Cell b11 = cells.get("B11");
        if (b11.getValue() != null) {
            rnSponsors.setSponsorName(b11.getStringValue());
            rnSponsors.setAmount(cells.get("D11").getFloatValue());
        }
        rnDocument.setRnSponsors(rnSponsors);

        RNOthers rnOthers = new RNOthers();
        Cell b13 = cells.get("B13");
        if (b13.getValue() != null) {
            rnOthers.setStructuralFundsIdentification(b13.getStringValue());
            rnOthers.setStructuralFunds(cells.get("D13").getFloatValue());
        }
        Cell b14 = cells.get("B14");
        if (b14.getValue() != null) {
            rnOthers.setExternalFundingIdentification(b14.getStringValue());
            rnOthers.setExternalFunding(cells.get("D14").getFloatValue());
        }
        Cell b15 = cells.get("B15");
        if (b15.getValue() != null) {
            rnOthers.setOtherIdentification(b15.getStringValue());
            rnOthers.setOther(cells.get("D15").getFloatValue());
        }
        rnOthers.setAdvancePayment(cells.get("D16").getFloatValue());
        rnDocument.setRnOthers(rnOthers);

        List<RNProduct> products = new ArrayList<>();

        int i = 6;
        while (i <= 14) {
            RNProduct rnProduct = new RNProduct();
            Cell nrCrt = cells.get("G" + i);
            if (nrCrt.getValue() == null) {
                break;
            }
            rnProduct.setNrCrt(nrCrt.getIntValue());
            rnProduct.setName(cells.get("H" + i).getStringValue());
            rnProduct.setCodCPV(cells.get("I" + i).getStringValue());
            rnProduct.setUm(cells.get("J" + i).getStringValue());
            rnProduct.setQuantity(cells.get("K" + i).getIntValue());
            rnProduct.setPricePerUnit(cells.get("L" + i).getFloatValue());
            rnProduct.setTotalPrice(cells.get("M" + i).getFloatValue());
            rnProduct.setRnDocument(rnDocument);
            products.add(rnProduct);

            i++;
        }

        rnDocument.setRnProducts(products);

        RNTotal rnTotal = new RNTotal();
        rnTotal.setTotalQuantity(cells.get("K15").getIntValue());
        rnTotal.setTotalPricePerUnit(cells.get("L15").getFloatValue());
        rnTotal.setTotalPrice(cells.get("M15").getFloatValue());
        rnDocument.setRnTotal(rnTotal);

        LocalDate date = LocalDate.now();
        rnDocument.setDateAdded(new SimpleDateFormat("yyyy-MM-dd").parse(date.toString()));
        String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
        rnDocument.setName("RN - " + dateString);
        rnDocument.setUser(UserUtil.getCurrentUser());

        //// FIXME: 20.12.2016 set the correct status as soon as it known how the integer representation of the status is mapped
        rnDocument.setStatus(1);
        rnDocument.setVersion(0.1f);

        URL urlToResourses = DRDocumentService.class.getClassLoader().getResource("");
        rnDocument.setPath(urlToResourses.getPath() + "files/rn/" + "RN - " + dateString);

        RNDocument savedDoc = rnDocumentRepository.save(rnDocument);

        return savedDoc.getId();
    }

    public RNDocument getDocument(Integer id) {
        return rnDocumentRepository.findOne(id);
    }

    public RNOthers getRnOthers(Integer id) {
        return rnOthersRepository.findOne(id);
    }

    public RNProduct getRnProduct(Integer id) {
        return rnProductRepository.findOne(id);
    }

    public RNResearch getRnResearch(Integer id) {
        return rnResearchRepository.findOne(id);
    }

    public RNSponsors getRnSponsors(Integer id) {
        return rnSponsorsRepository.findOne(id);
    }

    public RNTotal getRnTotal(Integer id) {
        return rnTotalRepository.findOne(id);
    }
}
