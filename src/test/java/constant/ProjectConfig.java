package constant;
import static constant.Reader.getEnvironmentConfig;
public class ProjectConfig {
    public static final String LOCATION="./src/test/resources/properties/common.properties";
    public static final String WEBSITE_URL= getEnvironmentConfig(LOCATION,"url");
}
