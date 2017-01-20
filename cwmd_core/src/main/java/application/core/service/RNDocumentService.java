package application.core.service;


import application.core.model.User;
import application.core.model.rn.*;
import application.core.repository.UserRepository;
import application.core.repository.rn.*;
import application.core.utils.UserUtil;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserRepository userRepository;

    public List<RNDocument> getDocumentsByUsername(String username) {
        return rnDocumentRepository.findByUser_Username(username);
    }

    public List<RNDocument> getAllDocuments() {
        return rnDocumentRepository.findAll();
    }

    public List<RNDocument> getPartOfAFlowDocumentsByUsername(String username) {
        return rnDocumentRepository.findByUser_UsernameAndIsPartOfFlow(username, true);
    }

    public List<RNDocument> getAllPartOfAFlowDocuments() {
        return rnDocumentRepository.findByIsPartOfFlow(true);
    }

    public void saveDocumentOnDisk(Workbook workbook, LocalDate date, Integer documentId, HttpServletRequest request) {
        URL urlToResourses = RNDocumentService.class.getClassLoader().getResource("");
        try {
            String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
            String username = UserUtil.getCurrentUsername(request);
            String path = urlToResourses.getPath();
            boolean save = handleMissingDirectories(path, username, documentId);
            if (save) {
                workbook.save(path + "files/" + username + "/rn/" + documentId + "/RN - " + dateString + ".xlsx", SaveFormat.XLSX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer saveDocumentInDB(Workbook workbook, LocalDate date, HttpServletRequest request) throws ParseException {
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

        String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
        Date dateAdded = java.sql.Date.valueOf(date.plusDays(1));
        rnDocument.setDateAdded(dateAdded);
        rnDocument.setName("RN - " + dateString);
        String username = UserUtil.getCurrentUsername(request);
        User user = userRepository.findOne(username);
        rnDocument.setUser(user);

        //// FIXME: 20.12.2016 set the correct status as soon as it known how the integer representation of the status is mapped
        rnDocument.setStatus(1);
        rnDocument.setVersion(0.1f);
        rnDocument.setIsPartOfFlow(false);
        rnDocument.setType("RN");

        RNDocument savedDoc = rnDocumentRepository.save(rnDocument);

        Integer documentId = savedDoc.getId();
        URL urlToResourses = RNDocumentService.class.getClassLoader().getResource("");
        rnDocument.setPath(urlToResourses.getPath() + "files/" + username + "/rn/" + documentId + "/RN - " + dateString + ".xlsx");
        return documentId;
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

    private boolean handleMissingDirectories(String path, String username, Integer documentId) {
        String pathname = path + "files";
        File filesDirectory = new File(pathname);
        boolean ok = filesDirectory.exists();
        if (!ok) {
            ok = filesDirectory.mkdir();
        }
        if (ok) {
            pathname = pathname + "/" + username;
            File userDirectory = new File(pathname);
            ok = userDirectory.exists();
            if (!ok) {
                ok = userDirectory.mkdir();
            }
        }
        if (ok) {
            pathname = pathname + "/rn";
            File rnDirectory = new File(pathname);
            ok = rnDirectory.exists();
            if (!ok) {
                ok = rnDirectory.mkdir();
            }
        }
        if (ok) {
            pathname = pathname + "/" + documentId;
            File rnDirectory = new File(pathname);
            ok = rnDirectory.exists();
            if (!ok) {
                ok = rnDirectory.mkdir();
            }
        }
        return ok;
    }
}
