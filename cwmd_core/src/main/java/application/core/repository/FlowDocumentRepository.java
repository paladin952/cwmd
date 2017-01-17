package application.core.repository;

import application.core.model.FlowDocument;
import application.core.model.PKs.FlowDocumentPK;

import javax.transaction.Transactional;

@Transactional
public interface FlowDocumentRepository extends CWMDRepository<FlowDocumentPK, FlowDocument> {}
