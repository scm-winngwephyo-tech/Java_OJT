package scm.bulletin.board.system.bl.service.report.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.bl.service.report.ReportService;

/**
 * <h2> ReportServiceImpl Class</h2>
 * <p>
 * Process for Displaying ReportServiceImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class ReportServiceImpl implements ReportService{

    /**
     * <h2>doGenerateReport</h2>
     * <p>
     * 
     * </p>
     * 
     * @param postList
     * @param baos
     * @param fileName
     * @param parameter
     * @param reportPath
     * @return
     * @throws JRException
     */
    @Override
    public String doGenerateReport(List<PostDTO> postList,
            HashMap<String, Object> parameter, String reportPath) throws JRException {
        String reportFileName = System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis()
                + ".xlsx";
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(postList);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "postListReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, datasource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, reportFileName);
        exporter.exportReport();
        return reportFileName;
    }

}
