package taryfa;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TAR1NameBuilderTest {
    private static TAR1NameBuilder nameBuilder;
    private static ExcelFileBuilder fileBuilder;

    @Before
    public void setUp() {
        nameBuilder = new TAR1NameBuilder();
    }

    @BeforeClass
    public static void setUpClass() {
        fileBuilder = new ExcelFileBuilder();
        fileBuilder.buildFile();
        fileBuilder.buildSheet("TT_centr");
    }

    @Test
    public void shouldSayThatFirstPartNameIsEquals() {
        nameBuilder.buildFirstPartName();

        assertEquals(nameBuilder.getName(), "taryfa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void onWrongParameters_ShouldThrownIllegalArgumentException() {
        nameBuilder.buildThirdPartName(null);
    }

    @Test
    public void shouldSayThatThirdPartNameIsEquals() {
        nameBuilder.buildThirdPartName(fileBuilder.getFile());

        assertEquals(nameBuilder.getName(), "0cf25cf6a0981694d4e5d5fdbd8e36325a3f6fe7");
    }
}