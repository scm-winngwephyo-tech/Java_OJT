package scm.bulletin.board.system.bl.service.post.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import scm.bulletin.board.system.bl.dto.post.PostDTO;
import scm.bulletin.board.system.bl.service.post.PostService;
import scm.bulletin.board.system.persistence.dao.post.PostDAO;
import scm.bulletin.board.system.persistence.dao.user.UserDAO;
import scm.bulletin.board.system.persistence.entity.post.Post;
import scm.bulletin.board.system.persistence.entity.user.User;
import scm.bulletin.board.system.web.form.post.PostForm;
import scm.bulletin.board.system.web.form.user.UserForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author WinNgwePhyo
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
@Primary
public class PostServiceImpl implements PostService {
    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    MessageSource messageSource;
    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;
    /**
     * <h2>postDAO</h2>
     * <p>
     * postDAO
     * </p>
     */
    @Autowired
    private PostDAO postDAO;

    /**
     * <h2>doAddPost</h2>
     * <p>
     * Add Post
     * </p>
     * 
     * @param postForm
     * @param currentUserId
     */
    @Override
    public void doAddPost(@Valid PostForm postForm, int currentUserId) {
        Post post = new Post(postForm);
        this.postDAO.dbAddPost(post, currentUserId, new Date());
    }

    /**
     * <h2>doInsertTitleExist</h2>
     * <p>
     * Insert Title Is Exit or Not
     * </p>
     * 
     * @param title
     * @return
     */
    @Override
    public boolean doInsertTitleExist(String title) {
        Post resultPost = this.postDAO.dbGetPostByTitle(title);
        boolean titleExist = false;
        if (resultPost != null) {
            titleExist = true;
        }
        return titleExist;
    }

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get Post List By PostCreatedUser ID
     * </p>
     * 
     * @param postCreatedUserId
     * @return
     */
    @Override
    public List<PostDTO> doGetPostList(int postCreatedUserId) {
        User user = this.userDAO.dbGetUserbyID(postCreatedUserId);
        List<Post> postList = this.postDAO.dbgetPostList(user);
        List<PostDTO> postListDTO = new ArrayList<>();
        if (postList != null) {
            for (Post post : postList) {
                PostDTO resPostDTO = new PostDTO(post);
                String name = this.userDAO.dbGetUserNamebyID(postCreatedUserId);
                resPostDTO.setCreatedUserName(name);
                postListDTO.add(resPostDTO);
            }
        }
        return postListDTO;
    }

    /**
     * <h2>doSearchPostByLimit</h2>
     * <p>
     * Search Post With Limit
     * </p>
     * 
     * @param currentPage
     * @param recordsPerPage
     * @param postForm
     * @param loginedUser
     * @return
     */
    @Override
    public List<PostDTO> doSearchPostByLimit(int currentPage, int recordsPerPage, PostForm postForm,
            UserForm loginedUser) {
        List<PostDTO> postDTOList = new ArrayList<PostDTO>();
        List<Post> postList = this.postDAO.doGetPostListWithLimit(currentPage, recordsPerPage, postForm, loginedUser);
        if (postList.size() > 0) {
            for (Post post : postList) {
                PostDTO postDto = new PostDTO(post);
                int id = postDto.getCreatedUserId();
                String name = this.userDAO.dbGetUserNamebyID(id);
                postDto.setCreatedUserName(name);
                postDTOList.add(postDto);
            }
        }
        return postDTOList;
    }

    /**
     * <h2>doSearchPostList</h2>
     * <p>
     * SearchPostList
     * </p>
     * 
     * @param postForm
     * @param loginedUser
     * @return
     */
    @Override
    public List<PostDTO> doSearchPostList(PostForm postForm, UserForm loginedUser) {
        List<PostDTO> postDTOList = new ArrayList<PostDTO>();
        List<Post> postList = this.postDAO.dbGetPostListBySearchData(loginedUser, postForm);
        for (Post post : postList) {
            PostDTO postDTO = new PostDTO(post);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

    /**
     * <h2>dogetPostById</h2>
     * <p>
     * Get Post By Post Id
     * </p>
     * 
     * @param postId
     * @return
     */
    @Override
    public PostForm dogetPostById(Integer postId) {
        Post postResult = this.postDAO.dbGetPostById(postId);
        if (postResult != null) {
            int id = postResult.getCreatedUserId();
            String name = this.userDAO.dbGetUserNamebyID(id);
            PostForm resultPostForm = new PostForm(postResult);
            resultPostForm.setCreatedUserName(name);
            return resultPostForm;
        }
        return null;
    }

    /**
     * <h2>doUpdateTitleExist</h2>
     * <p>
     * Test Update Title is Exist or Not
     * </p>
     * 
     * @param title
     * @param id
     * @return
     */
    @Override
    public Boolean doUpdateTitleExist(String title, Integer postId) {
        boolean updateTitleExist = false;
        List<Post> posList = this.postDAO.dbUpdatePostExist(title);
        Post postById = this.postDAO.dbGetPostById(postId);
        if (posList != null) {
            for (Post post : posList)
                updateTitleExist = post.getTitle() != postById.getTitle() ? true : false;
        }
        return updateTitleExist;
    }

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Post Update
     * </p>
     * 
     * @param postForm
     * @param loginUserForm
     */
    @Override
    public void doUpdatePost(@Valid PostForm postForm, UserForm currentUser) {
        Post post = new Post(postForm);
        if (currentUser.getType().equals("0") || currentUser.getType().equals("1")) {
            Post updatePostById = this.postDAO.dbGetPostById(post.getId());
            Post postTitle = this.postDAO.dbGetPostByTitle(post.getTitle());
            if (updatePostById != null) {
                if (postTitle == null || (postTitle != null && postTitle.getTitle() == updatePostById.getTitle())) {
                    updatePostById.setTitle(post.getTitle());
                    updatePostById.setDescription(post.getDescription());
                    updatePostById.setStatus(post.getStatus());
                    updatePostById.setUpdatedAt(new Date());
                    updatePostById.setCreatedUserId(currentUser.getId());
                }
            }
            this.postDAO.dbUpdatePost(updatePostById);
        }
    }

    /**
     * <h2>doUploadCSV</h2>
     * <p>
     * Upload CSV File
     * </p>
     * 
     * @param uploadFile
     * @param loginUserId
     * @return
     * @throws IOException
     */
    @Override
    public List<String> doUploadCSV(MultipartFile uploadFile, int currentUserId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(uploadFile.getInputStream()));
        List<String> uploadErrors = new ArrayList<>();
        int lineNumber = 1;
        String[] attributes;
        List<Post> postList = new ArrayList<>();
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            ++lineNumber;
            Post post = new Post();
            Date currentDate = new Date();
            attributes = line.split(",");
            if (attributes != null) {
                if (attributes.length == 3) {
                    Post resultPostByTitle = this.postDAO.dbGetPostByTitle(attributes[0]);
                    if (resultPostByTitle != null) {
                        uploadErrors.add("Post Title Data always Exist! Error At Row " + lineNumber);
                        return uploadErrors;
                    }
                    if (attributes[0].equals(null) || attributes[0].equals("") || attributes[0].isEmpty()) {
                        uploadErrors.add("Post Title Data is Empty! Error At Row " + lineNumber);
                        return uploadErrors;
                    }
                    if (attributes[1].equals(null) || attributes[1].equals("") || attributes[1].isEmpty()) {
                        uploadErrors.add("Post Description Data is Empty! Error At Row " + lineNumber);
                        return uploadErrors;
                    }
                    if (attributes[2].length() > 1) {
                        uploadErrors.add("Post Status greater than 1 digit" + lineNumber);
                        return uploadErrors;
                    } else if (!attributes[0].equals("") && !attributes[0].equals(null) && !attributes[0].isEmpty()
                            && !attributes[1].equals("") && !attributes[1].equals(null) && !attributes[1].isEmpty()
                            && resultPostByTitle == null && attributes[2].length() == 1) {
                        post.setTitle(attributes[0]);
                        post.setDescription(attributes[1]);
                        Integer status = Integer.valueOf(
                                (!attributes[2].equals("0") && !attributes[2].equals("1") ? "1" : attributes[2]));
                        post.setStatus(status);
                        post.setCreatedUserId(currentUserId);
                        post.setUpdatedUserId(currentUserId);
                        post.setCreatedAt(currentDate);
                        post.setUpdatedAt(currentDate);
                        postList.add(post);
                    }
                }
                if (attributes.length == 2 || attributes.length == 1 || attributes.length == 0) {
                    uploadErrors.add("Post Status Data is Empty! Error At Row " + lineNumber);
                    if (attributes.length == 0) {
                        uploadErrors.add("Post Title Data is Empty! Error At Row " + lineNumber);
                        return uploadErrors;
                    }
                }
                line = reader.readLine();
            }
        }
        for (Post postData : postList) {
            this.postDAO.dbPostUploadData(postData);
        }
        return uploadErrors;
    }

    /**
     * <h2>doGenerateDownloadExcelFile</h2>
     * <p>
     * Generate Download Excel File
     * </p>
     * 
     * @param postList
     * @param baos
     * @param fileName
     * @param parameter
     * @param reportPath
     * @return
     * @throws ParseException
     * @throws JRException
     * @throws FileNotFoundException
     */
//    @Override
//    public void doGenerateDownloadExcelFile(List<PostDTO> postList) throws IOException {
//        @SuppressWarnings("resource")
//        Workbook workbook = new XSSFWorkbook();
//        CreationHelper createHelper = workbook.getCreationHelper();
//        Sheet sheet = workbook.createSheet("PostList");
//        Font headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 14);
//        headerFont.setColor(IndexedColors.RED.getIndex());
//        CellStyle headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFont(headerFont);
//        Row headerRow = sheet.createRow(0);
//        String[] columnHeaders = { "Title", "Description", "Status", "Created User", "Updated User", "Created At",
//                "Updated At" };
//
//        for (int i = 0; i < columnHeaders.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columnHeaders[i]);
//            cell.setCellStyle(headerCellStyle);
//        }
//
//        CellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//        int rowNumber = 1;
//        for (PostDTO post : postList) {
//            Row row = sheet.createRow(rowNumber++);
//            row.createCell(0).setCellValue(post.getTitle());
//            row.createCell(1).setCellValue(post.getDescription());
//            if (post.getStatus() == 0) {
//                row.createCell(2).setCellValue("OFF");
//            } else {
//                row.createCell(2).setCellValue("ON");
//            }
//            String name = this.userDAO.dbGetUserNamebyID(post.getCreatedUserId());
//            row.createCell(3).setCellValue(name);
//            row.createCell(4).setCellValue(name);
//            final String OLD_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
//            final String NEW_FORMAT = "yyyy/MM/dd";
//            DateFormat inputFormat = new SimpleDateFormat(OLD_FORMAT);
//            DateFormat outputFormat = new SimpleDateFormat(NEW_FORMAT);
//            Date old_date = null;
//            try {
//                old_date = inputFormat.parse(post.getCreatedAt().toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            String date = outputFormat.format(old_date);
//            row.createCell(5).setCellValue(date);
//            row.createCell(6).setCellValue(date);
//        }
//        for (int i = 0; i < columnHeaders.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        FileOutputStream fileOut;
//        fileOut = new FileOutputStream(
//                System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis() + ".xlsx");
//        workbook.write(fileOut);
//        fileOut.close();
//    }

    /**
     * <h2>doPostDelete</h2>
     * <p>
     * Post Delete By ID
     * </p>
     * 
     * @param postId
     */
    @Override
    public void doPostDelete(Integer postId) {
        Post postDeletedUser = this.postDAO.dbGetPostById(postId);
        postDeletedUser.setDeletedAt(new Date());
    }

    @Override
    public String doJasperGenerateReport(List<PostDTO> postList, HashMap<String, Object> parameter, String reportPath)
            throws JRException {
//        String reportFileName = System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis()
//                + ".xlsx";
//        String reportFileName=System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis()
//        + ".pdf";
        String reportFileName =System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis()
        + ".html";
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(postList);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "postListReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, datasource);
        if (jasperPrint != null) {
            /**
             * 1- export to PDF
             */
           // JasperExportManager.exportReportToPdfFile(jasperPrint,reportFileName);

            /**
             * 2- export to HTML
             */
            JasperExportManager.exportReportToHtmlFile(jasperPrint,reportFileName);

//            JRXlsxExporter exporter = new JRXlsxExporter();
//            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, reportFileName);
//            exporter.exportReport();
        }
        return reportFileName;

    }
}
