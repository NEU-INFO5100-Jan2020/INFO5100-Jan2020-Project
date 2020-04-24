package persist;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionToAzureBlob {


    private static CloudStorageAccount storageAccount = null;
    private static CloudBlobClient blobClient = null;
    private static CloudBlobContainer container = null;

    public static String connectString = "DefaultEndpointsProtocol=https;AccountName=5100finalproject;AccountKey=XrHxHbmPgucTuHaCURBunGNj6QPTBYEX8n65ihU8WyExpMafbr8z3oKDe8CA5gMo2qyAeLRAcIbtOO7AFjQqpQ==;EndpointSuffix=core.windows.net";
    static String blobAccount = "5100finalproject";
    static String blobKey = "XrHxHbmPgucTuHaCURBunGNj6QPTBYEX8n65ihU8WyExpMafbr8z3oKDe8CA5gMo2qyAeLRAcIbtOO7AFjQqpQ==";
    static String end_point = "https://5100finalproject.blob.core.windows.net/vehicleimages";
    static String PROTOCOL = "https";
    static String format = "DefaultEndpointsProtocol={0};AccountName={1};AccountKey={2};EndpointSuffix={3}";
    static String containerName = "vehicleimages";

    public static void main(String[] args) {

        initBlob(connectString);

//        1.download to local targetPath from URL
//        String blobPath = "1.jpeg";
//        String targetPath = "src\\main\\resources\\CarImages\\";
//        downloadToFile(blobPath, targetPath);

//        2.Upload from File object
        String relativePath = "src\\main\\resources\\CarImages\\1.jpeg";
        File file = new File(relativePath);
        uploadFile(file, "1.jpeg");

//        3.file existance(check if URL is valid)
//         String fileName = "";
//         imageExist(fileName);


//          4.list all files
//        listBlob("test", false);
//        downloadToStream(blobPath);
    }


    // list all files
    public static void listBlob(String filePrefix, boolean subFolder) {
        // first argument can be used to retrieve blob
        Iterable<ListBlobItem> blobItems = container.listBlobs(filePrefix, subFolder);
        for (ListBlobItem blobItem : blobItems) {
            String uri = blobItem.getUri().toString();
            System.out.println(uri);
        }
    }

    public static List<String > listBlobWithReturn(String filePrefix, boolean subFolder) {
        Iterable<ListBlobItem> blobItems = container.listBlobs(filePrefix, subFolder);
        List<String > urls = new ArrayList<>();
        for (ListBlobItem blobItem : blobItems) {
            String uri = blobItem.getUri().toString();
            urls.add(uri);
        }
        return urls;
    }

    // blobPath : relative blob path including all folder.
    // targetPath: target local path;
    public static void downloadToFile(String blobPath, String targetPath) {
        String finalPath = targetPath.concat(blobPath);

        try {
            CloudBlockBlob blob = container.getBlockBlobReference(blobPath);
            blob.downloadToFile(finalPath);
//            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
//            blob.download(byteOutputStream);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File downloadToStream(String blobPath) {
        try {
            CloudBlockBlob blob = container.getBlockBlobReference(blobPath);
            String targetPath = "src/main/resources/CarImages/downloadFromStream";
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            blob.download(byteOutputStream);

            File file = new File(targetPath);

            byteOutputStream.writeTo(new FileOutputStream(file));
            // blob.downloadToByteArray()
//            Byte[] bytes = blob.downloadToByteArray();
            return file;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void uploadFile(File file, String fileName) {
        try {
            CloudBlockBlob blob = container.getBlockBlobReference(fileName);
            blob.uploadFromFile(file.getPath());
            blob.downloadAttributes();
            long blobSize = blob.getProperties().getLength();
            long localSize = file.length();
            if (blobSize != localSize) {
                System.out.println("Upload Failed!");
                blob.deleteIfExists();
            } else {
                System.out.println("Upload Succeed!");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean imageExist(String fileName) {
        try {
            CloudBlockBlob blob = container.getBlockBlobReference(fileName);
            return blob.exists();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void initBlob(String conString) {
        try {
            storageAccount = CloudStorageAccount.parse(conString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference(containerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
