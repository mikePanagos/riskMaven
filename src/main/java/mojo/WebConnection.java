package mojo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;


import java.io.File;

/**
 * @author oscartovar
 *
 */
public class WebConnection {
	private final String twitter_api_key = "";
	private final String aws_s3_key = "";
	private final String aws_s3_secret_access_key = "";
	private final String bucket_name = "mojoandrisk";
	private final String file_path = "log.txt";
	private final String key_name = Paths.get(file_path).getFileName().toString();
	
	public void updateTwitter() {
		
	};
	
	public String updateS3() {
		// final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		// try {
		//     s3.putObject(bucket_name, key_name, new File(file_path));
		// } catch (AmazonServiceException e) {
		//     System.err.println(e.getErrorMessage());
		//     System.exit(1);
		// }
		AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("risky").getCredentials();
            // credentials = new ProfileCredentialsProvider("risky").EnvironmentVariableCredentialsProvider();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (C:\\Users\\Michael\\.aws\\credentials), and is in valid format.",
                    e);
        }

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion("us-east-2")
            .build();
        String bucketName = "riskmichaelpanagos" ;
        String key = "logger";
        try {
          
            System.out.println("Uploading a new object to S3 from a file\n");
            // File f= new File("/log.txt");

            s3.putObject(new PutObjectRequest(bucketName, key, new File(file_path)));
            return "successfully added log.txt to s3 bucket";

           
          
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            return "failed added log.txt to s3 bucket";

        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            return "failed added log.txt to s3 bucket";

        }
	};
	
}
