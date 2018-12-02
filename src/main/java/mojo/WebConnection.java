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
import com.amazonaws.services.cloudformation.model.Output;
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
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;

/**
 * @author oscartovar
 *
 */
public class WebConnection {
    private final String aws_s3_key = "";
    private final String aws_s3_secret_access_key = "";
    private final String bucket_name = "mojoandrisk";
    private final String file_path = "log.txt";
    private final String key_name = Paths.get(file_path).getFileName().toString();

    public String updateS3() {
        AWSCredentials credentials = null;
        try {
            
            // credentials = new ProfileCredentialsProvider("default").getCredentials();
            DefaultAWSCredentialsProviderChain chain=new DefaultAWSCredentialsProviderChain ();
            credentials = chain.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location  and is in valid format.", e);
        }

        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-2").build();
        String bucketName = "riskmichaelpanagos";
        String key = "logger";
        try {

            System.out.println("Uploading a new object to S3 from a file\n");
            // File f= new File("/log.txt");

            s3.putObject(new PutObjectRequest(bucketName, key, new File(file_path)));
            return "successfully added log.txt to s3 bucket";

        } catch (AmazonServiceException ase) {
            return "failed added log.txt to s3 bucket";

        } catch (AmazonClientException ace) {
            return "failed added log.txt to s3 bucket";

        }
    }

    public String replay(){
        AWSCredentials credentials = null;
        try {
            
            // credentials = new ProfileCredentialsProvider("default").getCredentials();
            DefaultAWSCredentialsProviderChain chain=new DefaultAWSCredentialsProviderChain ();
            credentials = chain.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location  and is in valid format.", e);
        }

        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-2").build();
        String bucketName = "riskmichaelpanagos";
        String key = "logger";
        try {

            System.out.println("Uploading a new object to S3 from a file\n");
            // File f= new File("/log.txt");

            S3Object fullObject =s3.getObject(new GetObjectRequest(bucketName, key));
            
            try
            {BufferedReader reader = new BufferedReader(new InputStreamReader(fullObject.getObjectContent()));
            String line = null;
            String outputs="";
            while ((line = reader.readLine()) != null) {
                outputs+=line;
            }
            return outputs;

        }catch(IOException e){
                return "error";
            }
            

        } catch (AmazonServiceException ase) {
            
            return "error";

        } catch (AmazonClientException ace) {
            
            return "error";

        }
    }
  

}
