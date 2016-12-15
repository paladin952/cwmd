package application.web.controller;

import application.core.service.RNDocumentService;
import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/rn")
@RestController
public class RNDocumentController {

    @Autowired
    private RNDocumentService rnDocumentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Integer uploadRN(@RequestParam("file") MultipartFile file) throws Exception {
        Workbook workbook = null;
        Integer docId = null;
        try {
            workbook = new Workbook(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cell cell = workbook.getWorksheets().get(0).getCells().get("B8");

        System.out.println("cell.getValue().toString() = " + cell.getValue().toString());

        rnDocumentService.saveDocumentOnDisk(workbook, file.getOriginalFilename());
        return rnDocumentService.saveDocumentInDB(workbook, file.getOriginalFilename());

//        drDocumentService.saveDocumentOnServer(document, file.getOriginalFilename());
//        if (documentId == null) {
//            documentId = drDocumentService.saveDocumentInDBFirstPart(document, file.getOriginalFilename());
//            docId = documentId;
//        } else {
//            drDocumentService.saveDocumentInDBSecondPart(document, documentId);
//            documentId = null;
//        }
    }
}
