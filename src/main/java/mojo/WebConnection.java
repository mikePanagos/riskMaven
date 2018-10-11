package mojo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
	
	public void updateS3() {
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		try {
		    s3.putObject(bucket_name, key_name, new File(file_path));
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
		}
		// AWSCredentials credentials = null;
        // try {
        //     credentials = new ProfileCredentialsProvider("default").getCredentials();
        // } catch (Exception e) {
        //     throw new AmazonClientException(
        //             "Cannot load the credentials from the credential profiles file. " +
        //             "Please make sure that your credentials file is at the correct " +
        //             "location (C:\\Users\\Michael\\.aws\\credentials), and is in valid format.",
        //             e);
        // }

        // AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        //     .withCredentials(new AWSStaticCredentialsProvider(credentials))
        //     .withRegion("us-west-2")
        //     .build();

        // String bucketName = "my-first-s3-bucket-" + UUID.randomUUID();
        // String key = "MyObjectKey";
        // try {
          
        //     System.out.println("Uploading a new object to S3 from a file\n");
        //     s3.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));

           
          
        // } catch (AmazonServiceException ase) {
        //     System.out.println("Caught an AmazonServiceException, which means your request made it "
        //             + "to Amazon S3, but was rejected with an error response for some reason.");
        //     System.out.println("Error Message:    " + ase.getMessage());
        //     System.out.println("HTTP Status Code: " + ase.getStatusCode());
        //     System.out.println("AWS Error Code:   " + ase.getErrorCode());
        //     System.out.println("Error Type:       " + ase.getErrorType());
        //     System.out.println("Request ID:       " + ase.getRequestId());
        // } catch (AmazonClientException ace) {
        //     System.out.println("Caught an AmazonClientException, which means the client encountered "
        //             + "a serious internal problem while trying to communicate with S3, "
        //             + "such as not being able to access the network.");
        //     System.out.println("Error Message: " + ace.getMessage());
        // }
	};
	
}
