import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

public class Domain_Test {
    @Test
    public void constructor() {
      Assert.assertNotNull(new Domain());
    }

    @Test
    public void adddomain() {
      Domain.addDomain();
      Assert.assertTrue(true);
    }
}
