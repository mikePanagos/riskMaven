package mojo;
import java.io.File;
import java.io.IOException;

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
	private String twitter_api_key = "";
	private String aws_s3_key = "";
	private String aws_s3_secret_access_key = "";
	
	public void updateTwitter() {
		
	};
	
	public void updateS3() {

	};
	
}
