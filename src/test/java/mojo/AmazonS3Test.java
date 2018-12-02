package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class AmazonS3Test{


    @Test public void sendFileTest(){
       WebConnection wc = new WebConnection();
//
       System.out.println("\n\n\nTESTING S3\n\n\n");
       assertTrue("sendFileTest",wc.updateS3().equals("successfully added log.txt to s3 bucket"));
       System.out.println("\n\n\nTESTING S3 PASSED WITH FLYING COLORS\n\n\n");

    }
    @Test public void replayFileTest(){
        WebConnection wc = new WebConnection();
 //
        System.out.println("\n\n\nTESTING S3\n\n\n");
        assertTrue("replayFileTest",(wc.replay()!="error"));
        System.out.println("\n\n\nTESTING S3 PASSED WITH FLYING COLORS\n\n\n");
 
     }
}
