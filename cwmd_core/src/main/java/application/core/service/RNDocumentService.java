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

/**
 * Service for {@link RNDocument}.
 */
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

    /**
     * Returns all the documents for a username.
     * @param username The username
     * @return A list of {@link RNDocument} of the user
     */
    public List<RNDocument> getDocumentsByUsername(String username) {
        return rnDocumentRepository.findByUser_Username(username);
    }

    /**
     * Returns all the documents.
     * @return A list of {@link RNDocument}.
     */
    public List<RNDocument> getAllDocuments() {
        return rnDocumentRepository.findAll();
    }

    /**
     * Returns all the documents that are part of a flow for a username.
     * @param username The username.
     * @return A list of {@link RNDocument}.
     */
    public List<RNDocument> getPartOfAFlowDocumentsByUsername(String username) {
        return rnDocumentRepository.findByUser_UsernameAndIsPartOfFlow(username, true);
    }

    /**
     * Returns all  the documents that are part of a flow
     * @return A list of {@link RNDocument}.
     */
    public List<RNDocument> getAllPartOfAFlowDocuments() {
        return rnDocumentRepository.findByIsPartOfFlow(true);
    }

    /**
     * Saves a document on the disk with date and ID.
     * @param workbook the document to be saved.
     * @param date The Date when the document was uploaded.
     * @param documentId The ID of the document.
     */
    public void saveDocumentOnDisk(Workbook workbook, LocalDate date, Integer documentId, HttpServletRequest request) {
        URL urlToResourses = RNDocumentService.class.getClassLoader().getResource("");
        try {
            String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
            String username = UserUtil.getCurrentUsername(request);
            String path = urlToResourses.getPath();
            boolean save = handleMissingDirectories(path, username);
            if (save) {
                workbook.save(path + "files/" + username + "/rn/" + documentId + "/RN - " + dateString + ".xlsx", SaveFormat.XLSX);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the document in the database
     * @param workbook The document to be saved.
     * @param date The Date when the document was uploaded.
     * @return The ID of the document
     */
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

        URL urlToResourses = DRDocumentService.class.getClassLoader().getResource("");
        rnDocument.setPath(urlToResourses.getPath() + "files/" + username + "/rn/" + "RN - " + dateString);

        RNDocument savedDoc = rnDocumentRepository.save(rnDocument);

        return savedDoc.getId();
    }

    /**
     * Returns a {@link RNDocument} with a given ID.
     * @param id The ID of the document.
     * @return The document with the given ID.
     */
    public RNDocument getDocument(Integer id) {
        return rnDocumentRepository.findOne(id);
    }

    /**
     * Returns a {@link RNOthers} with a given ID.
     * @param id The ID of the RNOthers.
     * @return The RNOthers with the given ID.
     */
    public RNOthers getRnOthers(Integer id) {
        return rnOthersRepository.findOne(id);
    }

    /**
     * Returns a {@link RNProduct} with a given ID.
     * @param id The ID of the RNProduct.
     * @return The RNProduct with the given ID.
     */
    public RNProduct getRnProduct(Integer id) {
        return rnProductRepository.findOne(id);
    }

    /**
     * Returns a {@link RNResearch} with a given ID.
     * @param id The ID of the RNResearch.
     * @return The RNResearch with the given ID.
     */
    public RNResearch getRnResearch(Integer id) {
        return rnResearchRepository.findOne(id);
    }

    /**
     * Returns a {@link RNSponsors} with a given ID.
     * @param id The ID of the RNSponsors.
     * @return The RNSponsors with the given ID.
     */
    public RNSponsors getRnSponsors(Integer id) {
        return rnSponsorsRepository.findOne(id);
    }

    /**
     * Returns a {@link RNTotal} with a given ID.
     * @param id The ID of the RNTotal.
     * @return The RNTotal with the given ID.
     */
    public RNTotal getRnTotal(Integer id) {
        return rnTotalRepository.findOne(id);
    }

    /**
     * Function to handle missing directories
     * @param path the path to the directory
     * @param username the username of the user
     * @return True if the directories were successfully created, False otherwise
     */
    private boolean handleMissingDirectories(String path, String username) {
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
        return ok;
    }
}
