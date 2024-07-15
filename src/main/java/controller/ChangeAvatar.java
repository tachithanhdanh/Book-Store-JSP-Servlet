package controller;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Customer;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

@WebServlet(name = "ChangeAvatar", value = "/user/change-avatar")
public class ChangeAvatar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String PERMANENT_UPLOAD_DIRECTORY =
            "D:\\github\\tachithanhdanh\\Web\\Java" +
                    "\\Java-Servlet-IntelliJ\\Book-Store-JSP-Servlet" +
                    "\\src\\main\\webapp\\user\\img";
    private static String UPLOAD_DIRECTORY = "user" + File.separator + "img";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 4;  // 4MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 8; // 8MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10; // 10MB

    @Override
    public void init() throws ServletException {
        // Create the directory if it does not exist
//        UPLOAD_DIRECTORY = getServletContext().getInitParameter("avatar-directory");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/change-avatar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check if the request actually contains upload file
        if (!JakartaServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            response.getWriter().println("Error: Form must has enctype=multipart/form-data.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // configure upload settings
        DiskFileItemFactory factory = DiskFileItemFactory.builder()
                // set memory threshold - beyond which files are stored in disk
                .setBufferSize(MEMORY_THRESHOLD)
                // set temporary directory to store files
                .setPath(new File(System.getProperty("java.io.tmpdir")).toPath())
                // build the factory
                .get();


        // Create a new file upload handler
//        @SuppressWarnings({"rawtypes", "unchecked"})
        JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> upload =
                new JakartaServletFileUpload<>(factory);

        // set maximum file size to be uploaded
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // set maximum request size (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String permanentUploadDirectory = PERMANENT_UPLOAD_DIRECTORY;
        String tempUploadDirectory = getServletContext().getRealPath(UPLOAD_DIRECTORY);

        // create the directory if it does not exist
        File permanentUploadDir = new File(permanentUploadDirectory);
        if (!permanentUploadDir.exists()) {
            permanentUploadDir.mkdir();
        }

        File tempUploadDir = new File(tempUploadDirectory);
        if (!tempUploadDir.exists()) {
            tempUploadDir.mkdir();
        }

        try {
            // parse the request's content to extract file data
            List<DiskFileItem> formItems = (List<DiskFileItem>) upload.parseRequest(request);

            if (formItems != null && !formItems.isEmpty()) {
                // iterates over form's fields
                for (DiskFileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        HttpSession session = request.getSession();
                        Customer customer = (Customer) session.getAttribute("loggedCustomer");
                        String fileName = customer.getCustomerId() + "-" + new File(item.getName()).getName();
                        String permanentFilePath = permanentUploadDirectory + File.separator + fileName;
                        String tempFilePath = tempUploadDirectory + File.separator + fileName;
//                        System.out.println(permanentFilePath);
                        File storeFile = new File(permanentFilePath);
                        File tempFile = new File(tempFilePath);

                        // saves the file on disk
                        item.write(storeFile.toPath());
                        item.write(tempFile.toPath());
                        CustomerDAO customerDAO = new CustomerDAOImpl();
                        customer.setAvatar(fileName);
                        customerDAO.update(customer);
                        request.setAttribute("message", "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/user/change-avatar.jsp").forward(request, response);
    }
}