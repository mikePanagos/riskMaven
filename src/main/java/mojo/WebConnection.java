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
	};
	
}
