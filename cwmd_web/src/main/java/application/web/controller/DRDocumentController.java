package application.web.controller;

import application.core.service.DRDocumentService;
import application.web.misc.ViewPath;
import com.aspose.words.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/document")
@Controller
public class DRDocumentController {

    @Autowired
    private DRDocumentService drDocumentService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadDR() {
        return ViewPath.UPLOAD_DOCUMENT;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadDR(@RequestParam("file") MultipartFile file, @RequestParam("documentId") Integer documentId, ModelMap model) throws Exception {
        String returnView = "";

        Document document = null;
        try {
            document = new Document(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        drDocumentService.saveDocumentOnServer(document, file.getOriginalFilename());
        if (documentId == null) {
            Integer docId = drDocumentService.saveDocumentInDBFirstPart(document, file.getOriginalFilename());
            returnView = ViewPath.UPLOAD_DOCUMENT;
            model.addAttribute("documentId", docId);
        } else {
            drDocumentService.saveDocumentInDBSecondPart(document, documentId);
            returnView = ViewPath.WORKING_AREA;
        }

        return returnView;
    }
}