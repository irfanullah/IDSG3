package Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class will read the external configuration from sources
 */
public class SystemConfiguration {
    private static SystemConfiguration systemConfiguration = new SystemConfiguration();
    private static AppEnv appEnv = new AppEnv();

    private SystemConfiguration() {
    }

    /* Static 'instance' method */
    public static SystemConfiguration getInstance(AppEnv appEnv) {
        SystemConfiguration.appEnv = appEnv;
        return systemConfiguration;
    }

    /**
     *
     */
    public AppEnv Read_Properties(AppEnv appEnv) {
        Properties prop = new Properties();
        File file = new File("./src/main/resources/SysConfig/QAVM94.properties");

        FileInputStream fileInput;

        /*------------------------------------------- Read Logger Config -----------------------------------------------------*/
        try {
            fileInput = new FileInputStream(file);
            prop.load(fileInput);
        } catch (IOException exh) {
            System.out.println("IO Execption : Unable to Load/Read Properties file");
        }
        appEnv.setBrowser(prop.getProperty("Browser"));
        appEnv.setDomain(prop.getProperty("Domain"));
        appEnv.setWorkOrderNumber(prop.getProperty("WorkOrderNumber"));
        appEnv.setFirstName(prop.getProperty("FirstName"));
        appEnv.setLastName(prop.getProperty("LastName"));
        appEnv.setPhoneNumber(prop.getProperty("PhoneNumber"));
        appEnv.setStockNumber(prop.getProperty("StockNumber"));
        appEnv.setCustomerNumber(prop.getProperty("CustomerNumber"));
        appEnv.setEmail(prop.getProperty("Email"));
        appEnv.setPassword(prop.getProperty("Password"));
        appEnv.setToken(prop.getProperty("Token"));
        appEnv.setLocation(prop.getProperty("Location"));
        appEnv.setSendReportEmail(prop.getProperty("SendReportEmail"));
        appEnv.setUserID(prop.getProperty("UserID"));
        appEnv.setAPIAddress(prop.getProperty("APIAddress"));
        appEnv.setEnvironment(prop.getProperty("Environment"));
        appEnv.setCompletedWithinDays(prop.getProperty("CompletedWithinDays"));
        appEnv.setToEmailAddress(prop.getProperty("ToEmailAddress"));
        appEnv.setCCEmailAddress(prop.getProperty("CCEmailAddress"));

        return appEnv;
    }
}
