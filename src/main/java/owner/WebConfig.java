package owner;

import org.aeonbits.owner.Config;
import urls.ConstantsURLs;

public interface WebConfig extends Config {
    @Key("baseUrl")
    @DefaultValue(ConstantsURLs.MAIN_URL)
    String getBaseUrl();
    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();
}
