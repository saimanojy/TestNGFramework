package Utility;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
@DataProvider(name="getPromos")
public Object[][] getPromo(){
	return new Object[][] {
        { "XXX", "not valid" },
        { "AFS-FJK-123", "not valid" },
        { "AFS-FJK-SS0", "not valid" }, 
        { "AF3-FJK-A14", "not valid" },
        { "AF3-FJK-AB3", "not valid" } ,
        { "AFS-FJK-XYZ", "not valid" },
        { "  3-   - 14", "not valid" }, 
        { "AF3XFJKS126", "not valid" },
    };
}
}
