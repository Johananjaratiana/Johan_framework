package etu1933.framework.file;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class FileUpload
{
    String fileName;
    byte[] bytes;

    public FileUpload(String fileName, byte[] bytes)
    {
        this.fileName = fileName;
        this.bytes = bytes;
    }

    public static void AddFileUpload(ArrayList<FileUpload> fileUploads, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data"))
        {

            if (fileUploads == null) fileUploads = new ArrayList<>();

            Collection<Part> parts = request.getParts();
            for (Part part : parts)
            {
                String fileName = FileUpload.extractFileName(part);
                if (!fileName.isEmpty())
                {
                    byte[] fileBytes = FileUpload.readBytesFromPart(part);
                    FileUpload fileUpload = new FileUpload(fileName, fileBytes);
                    fileUploads.add(fileUpload);
                }
            }

            System.out.println("---------------------------- ALL FILE UPLOAD ------------------------------------");
            for(int a = 0 ; a < fileUploads.size() ; a++)
            {
                FileUpload.displayFileContents(fileUploads.get(a).fileName, fileUploads.get(a).bytes);
                System.out.println("-----------------------------------------------------------------------------------------------");
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }

    private static String extractFileName(Part part)
    {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements)
        {
            if (element.trim().startsWith("filename"))
            {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return "";
    }

    private static byte[] readBytesFromPart(Part part) throws IOException
    {
        InputStream inputStream = part.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1)
        {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
    public static void displayFileContents(String fileName, byte[] bytes)
    {
        System.out.println("File Name: " + fileName);
        System.out.println("File Contents:");
        System.out.println(new String(bytes));
    }
}
