/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Apr 26, 2009
 * Time: 11:23:36 PM
 */
package com.mysticcoders.yammer4j;

import com.mysticcoders.yammer4j.domain.ConfigProperties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class YammerTest {

    private Yammer yammer;

    @BeforeClass
    public void setUp() {
        try {

            StringBuilder buildDir = new StringBuilder(System.getProperty("user.dir"));
            buildDir.append("/etc");

            File buildDirFile = new File(buildDir.toString());
            if(!buildDirFile.exists()) {
                if(!buildDirFile.mkdir()) {
                    throw new Exception("Creating build directory failed");
                }
            }

            buildDir.append("/oauth.properties");

            final File oauthFile = new File(buildDir.toString());
            FileInputStream is = new FileInputStream(oauthFile);

            yammer = new Yammer(new ConfigProperties(is) {
                public void writeProperties(Properties props) {
                    try {
                        FileOutputStream os = new FileOutputStream(oauthFile);
                        props.store(os, "yammertest");
                        os.close();
                    } catch(FileNotFoundException e) {
                        e.printStackTrace();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegister() {

        String url =
                yammer.register();

        System.out.println("url:" + url);
    }


    @Test
    public void testAuthorize() {

        boolean successful = yammer.authorize();

        assert successful : "You rock!";
    }


}