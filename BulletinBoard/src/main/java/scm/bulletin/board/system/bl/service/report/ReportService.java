package scm.bulletin.board.system.bl.service.report;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import scm.bulletin.board.system.bl.dto.post.PostDTO;

/**
 * <h2>ReportService Class</h2>
 * <p>
 * Process for Displaying ReportService
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
public interface ReportService {

    /**
     * <h2>doGenerateReport</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchPostList
     * @param baos
     * @param parameter
     * @param reportPath
     * @return
     * @return String
     */
    String doGenerateReport(List<PostDTO> searchPostList, HashMap<String, Object> parameter, String reportPath)
            throws JRException;

}
