package application.core.service;


import application.core.model.rn.*;
import application.core.repository.RNDocumentRepository;
import application.core.utils.UserUtil;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RNDocumentService {
    @Autowired
    private RNDocumentRepository rnDocumentRepository;

    public void saveDocumentOnDisk(Workbook workbook, String filename) {
        URL urlToResourses = DRDocumentServiceImpl.class.getClassLoader().getResource("");
        try {
            //TODO: get the current user logged in order to save files under personal folder
            workbook.save(urlToResourses.getPath() + "files/rn/" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer saveDocumentInDB(Workbook workbook, String filename) {
        Cells cells = workbook.getWorksheets().get(0).getCells();

        RNDocument rnDocument = new RNDocument();

        Float budget = cells.get("D5").getFloatValue();
        rnDocument.setBudget(budget);

        Float personalFunds = cells.get("D6").getFloatValue();
        rnDocument.setPersonalFunds(personalFunds);

        RNResearch rnResearch = new RNResearch();
        rnResearch.setNationalFunds1Identification(cells.get("B8").getStringValue());
        rnResearch.setNationalFunds1(cells.get("D8").getFloatValue());
        rnResearch.setNationalFunds2Identification(cells.get("B9").getStringValue());
        rnResearch.setNationalFunds2(cells.get("D9").getFloatValue());
        rnResearch.setTernaryContractsIdentification(cells.get("B10").getStringValue());
        rnResearch.setTernaryContracts(cells.get("D10").getFloatValue());
        rnDocument.setRnResearch(rnResearch);

        RNSponsors rnSponsors = new RNSponsors();
        rnSponsors.setSponsorName(cells.get("B11").getStringValue());
        rnSponsors.setAmount(cells.get("D11").getFloatValue());
        rnDocument.setRnSponsors(rnSponsors);

        RNOthers rnOthers = new RNOthers();
        rnOthers.setStructuralFundsIdentification(cells.get("B13").getStringValue());
        rnOthers.setStructuralFunds(cells.get("D13").getFloatValue());
        rnOthers.setExternalFundingIdentification(cells.get("B14").getStringValue());
        rnOthers.setExternalFunding(cells.get("D14").getFloatValue());
        rnOthers.setOtherIdentification(cells.get("B15").getStringValue());
        rnOthers.setOther(cells.get("D15").getFloatValue());
        rnOthers.setAdvancePayment(cells.get("D16").getFloatValue());
        rnDocument.setRnOthers(rnOthers);

        List<RNProduct> products = new ArrayList<>();

        int i = 6;
        while (i <= 14) {
            RNProduct rnProduct = new RNProduct();
            rnProduct.setNrCrt(cells.get("G" + i).getIntValue());
            rnProduct.setName(cells.get("H" + i).getStringValue());
            rnProduct.setCodCPV(cells.get("I" + i).getStringValue());
            rnProduct.setUm(cells.get("J" + i).getStringValue());
            rnProduct.setQuantity(cells.get("K" + i).getIntValue());
            rnProduct.setPricePerUnit(cells.get("L" + i).getFloatValue());
            rnProduct.setTotalPrice(cells.get("M" + i).getFloatValue());
            products.add(rnProduct);

            i++;
        }

        rnDocument.setRnProducts(products);

        RNTotal rnTotal = new RNTotal();
        rnTotal.setTotalQuantity(cells.get("K15").getIntValue());
        rnTotal.setTotalPricePerUnit(cells.get("L15").getFloatValue());
        rnTotal.setTotalPrice(cells.get("M15").getFloatValue());
        rnDocument.setRnTotal(rnTotal);

        rnDocument.setDateAdded(new Date());
        rnDocument.setName(filename);
        rnDocument.setUser(UserUtil.getCurrentUser());

        RNDocument savedDoc = rnDocumentRepository.save(rnDocument);

        return savedDoc.getId();
    }
}
