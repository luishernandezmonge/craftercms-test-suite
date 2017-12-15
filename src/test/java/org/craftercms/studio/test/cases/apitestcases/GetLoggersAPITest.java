package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.ServerAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.Test;

/**
 * Created by chris lim
 */

public class GetLoggersAPITest {

    private ServerAPI serverAPI;
    
    public GetLoggersAPITest(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
    	
		serverAPI = new ServerAPI(api, apiConnectionManager);
    }

    @Test(priority=1)
    public void testGetLoggers(){
    	serverAPI.testGetLoggers();
    }
}
