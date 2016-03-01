package ProblemSet3.programmingProblems;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Paul on 11/1/2015.
 */
public class StringNodeTest extends TestCase {

    @Test
    public void testContains() throws Exception {
        StringNode str = StringNode.convert("fine");
        StringNodeCopy strCpy = StringNodeCopy.convert("fine");
        assertEquals(StringNodeCopy.contains(strCpy, 'f'), StringNode.contains(str, 'f'));

        str = StringNode.convert("fine");
        strCpy = StringNodeCopy.convert("fine");
        assertEquals(StringNodeCopy.contains(strCpy, 'e'), StringNode.contains(str, 'e'));

        str = StringNode.convert("ffff");
        strCpy = StringNodeCopy.convert("ffff");
        assertEquals(StringNodeCopy.contains(strCpy, 'f'), StringNode.contains(str, 'f'));

        str = StringNode.convert("ffff");
        strCpy = StringNodeCopy.convert("ffff");
        assertEquals(StringNodeCopy.contains(strCpy, 'e'), StringNode.contains(str, 'e'));

        str = StringNode.convert("ffff");
        strCpy = StringNodeCopy.convert("ffff");
        assertEquals(StringNodeCopy.contains(strCpy, ' '), StringNode.contains(str, ' '));

        str = StringNode.convert("ff  ff");
        strCpy = StringNodeCopy.convert("ffff  ");
        assertEquals(StringNodeCopy.contains(strCpy, ' '), StringNode.contains(str, ' '));

        str = null;
        strCpy = null;
        assertEquals(StringNodeCopy.contains(strCpy, 'e'), StringNode.contains(str, 'e'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.contains(strCpy, 'e'), StringNode.contains(str, 'e'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.contains(strCpy, '\0'), StringNode.contains(str, '\0'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.contains(strCpy, '\n'), StringNode.contains(str, '\n'));

        str = StringNode.convert("1\n");
        strCpy = StringNodeCopy.convert("1\n");
        assertEquals(StringNodeCopy.contains(strCpy, '\n'), StringNode.contains(str, '\n'));
    }

    @Test
    public void testIndexOf() throws Exception {
        StringNode str = StringNode.convert("fine");
        StringNodeCopy strCpy = StringNodeCopy.convert("fine");
        assertEquals(StringNodeCopy.indexOf(strCpy, 'f'), StringNode.indexOf(str, 'f'));

        str = StringNode.convert("fine");
        strCpy = StringNodeCopy.convert("fine");
        assertEquals(StringNodeCopy.indexOf(strCpy, 'e'), StringNode.indexOf(str, 'e'));

        str = StringNode.convert("ffff");
        strCpy = StringNodeCopy.convert("ffff");
        assertEquals(StringNodeCopy.indexOf(strCpy, 'f'), StringNode.indexOf(str, 'f'));

        str = StringNode.convert("ffff");
        strCpy = StringNodeCopy.convert("ffff");
        assertEquals(StringNodeCopy.indexOf(strCpy, 'e'), StringNode.indexOf(str, 'e'));

        str = null;
        strCpy = null;
        assertEquals(StringNodeCopy.indexOf(strCpy, 'e'), StringNode.indexOf(str, 'e'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.indexOf(strCpy, 'e'), StringNode.indexOf(str, 'e'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.indexOf(strCpy, '\0'), StringNode.indexOf(str, '\0'));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.indexOf(strCpy, '\n'), StringNode.indexOf(str, '\n'));

        str = StringNode.convert("1\n");
        strCpy = StringNodeCopy.convert("1\n");
        assertEquals(StringNodeCopy.indexOf(strCpy, '\n'), StringNode.indexOf(str, '\n'));
    }


    @Test
    public void testLength() throws Exception {
        StringNode str = StringNode.convert("fine");
        StringNodeCopy strCpy = StringNodeCopy.convert("fine");
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

        str = null;
        strCpy = null;
        assertEquals(strCpy.length(strCpy), StringNode.length(str));

        str = StringNode.convert("");
        strCpy = StringNodeCopy.convert("");
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

        str = new StringNode('\0', null);
        strCpy = new StringNodeCopy('\0', null);
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

        str = StringNode.convert("\n");
        strCpy = StringNodeCopy.convert("\n");
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

        str = StringNode.convert("fi\nne\n");
        strCpy = StringNodeCopy.convert("fi\nne\n");
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

        str = StringNode.convert("\nfi\nne\n");
        strCpy = StringNodeCopy.convert("\nfi\nne\n");
        assertEquals(StringNodeCopy.length(strCpy), StringNode.length(str));

    }

    @Test
    public void testPrint() throws Exception {
//        StringNode str = StringNode.convert("fine");
//        StringNodeCopy strCpy = StringNodeCopy.convert("fine");
//        assertEquals(StringNodeCopy.print(strCpy), StringNode.print(str));
//
//        str = StringNode.convert("finea");
//        strCpy = StringNodeCopy.convert("fine");
//        assertNotEquals(StringNodeCopy.copy(strCpy).toString(), StringNode.copy(str).toString());
    }

    @Test
    public void testConcat() throws Exception {
        StringNode str1 = StringNode.convert("abc");
        StringNode str2 = StringNode.convert("012");
        StringNodeCopy strCpy1 = StringNodeCopy.convert("abc");
        StringNodeCopy strCpy2 = StringNodeCopy.convert("012");
//        System.out.println(StringNodeCopy.concat(strCpy1, strCpy2).toString());
//        System.out.println(StringNode.concat(str1, str2).toString());
        assertEquals(StringNodeCopy.concat(strCpy1, strCpy2).toString(), StringNode.concat(str1, str2).toString());

        str1 = StringNode.convert("012");
        str2 = StringNode.convert("012");
        strCpy1 = StringNodeCopy.convert("abc");
        strCpy2 = StringNodeCopy.convert("012");
        assertNotEquals(StringNodeCopy.concat(strCpy1, strCpy2).toString(), StringNode.concat(str1, str2).toString());

        str1 = null;
        str2 = StringNode.convert("012");
        strCpy1 = null;
        strCpy2 = StringNodeCopy.convert("012");
        assertEquals(StringNodeCopy.concat(strCpy1, strCpy2).toString(), StringNode.concat(str1, str2).toString());

        str1 = null;
        str2 = StringNode.convert("012");
        strCpy1 = StringNodeCopy.convert("012");
        strCpy2 = null;
        assertEquals(StringNodeCopy.concat(strCpy1, strCpy2).toString(), StringNode.concat(str1, str2).toString());

    }

    @Test
    public void testCopy() throws Exception {
        StringNode str = StringNode.convert("abc");
        StringNodeCopy strCpy = StringNodeCopy.convert("abc");
//        System.out.println(StringNodeCopy.copy(strCpy).toString());
//        System.out.println(StringNode.copy(str).toString());

        assertEquals(StringNodeCopy.copy(strCpy).toString(), StringNode.copy(str).toString());

        str = StringNode.convert("abca");
        strCpy = StringNodeCopy.convert("abc");
        assertNotEquals(StringNodeCopy.copy(strCpy).toString(), StringNode.copy(str).toString());

        str = null;
        strCpy = null;
        assertNull(StringNodeCopy.copy(strCpy));
        assertNull(StringNode.copy(str));

    }

    @Test
    public void testPrintReverse() throws Exception {
//        StringNode str = StringNode.convert("fine");
//        StringNode.printReverse(str);
    }

    @Test
    public void testReverseString() throws Exception {
//        StringNode str = StringNode.convert("fine");
//        assertEquals(new StringNode('e', new StringNode('n',
//                new StringNode('i',
//                        new StringNode('f', null)))), StringNode.reverseString(str));
    }


    @Test
    public void testToUpperCase() throws Exception {
        StringNode str = StringNode.convert("fInE");
        StringNodeCopy strCpy = StringNodeCopy.convert("finE");
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertEquals(str.toString(), strCpy.toString());

        str = new StringNode('\0', null);
        strCpy = new StringNodeCopy('\0', null);
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertEquals(str.toString(), strCpy.toString());

        str = StringNode.convert("\n");
        strCpy = StringNodeCopy.convert("\n");
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertEquals(str.toString(), strCpy.toString());

        str = StringNode.convert("FI\nne\n");
        strCpy = StringNodeCopy.convert("fi\nnE\n");
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertEquals(str.toString(), strCpy.toString());

        str = StringNode.convert("\nfi\nne\n");
        strCpy = StringNodeCopy.convert("\nai\nne\n");
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertNotEquals(str.toString(), strCpy.toString());

        str = StringNode.convert("AAAABB BB");
        strCpy = StringNodeCopy.convert("aaaABB BB");
        StringNodeCopy.toUpperCase(strCpy);
        StringNode.toUpperCase(str);
        assertEquals(str.toString(), strCpy.toString());
    }

    @Test
    public void testGetNode() throws Exception {
        StringNode str = StringNode.convert("fi ne");
        StringNodeCopy strCpy = StringNodeCopy.convert("fi ne");
//        assertEquals(StringNodeCopy.getNode(strCpy, 2).toString(), StringNode.getNode(str, 2).toString());
//        assertNotEquals(StringNodeCopy.getNode(strCpy, 2).toString(), StringNode.getNode(str, 3).toString());
//
//        str = StringNode.convert("fine");
//        strCpy = StringNodeCopy.convert("fine");
//        assertNull(StringNodeCopy.getNode(strCpy, 24));
//        assertNull(StringNode.getNode(str, 123));
//        assertNull(StringNode.getNode(str, -123));
    }

    @Test
    public void testSubstring() throws Exception {
        StringNode str = StringNode.convert("fine");

        // intermediate
//        System.out.println(StringNode.substring(str, 0, 3).toString());
//        assertEquals("fine", StringNode.substring(str, 2, 5).toString());

        str = StringNode.convert("0123456");
//        System.out.println(StringNode.substring(str, 3, 5).toString());
//
//        System.out.println(StringNode.substring(null, 3, 5).toString());

        assertEquals("12", StringNode.substring(str, 1, 3).toString());
//        assertEquals("12", null);

//        System.out.println(StringNode.substring(str, 2, 2).toString());
        assertNull(StringNode.substring(str, 2, 2));

//        System.out.println(StringNode.substring(str, 0, 3).toString());
        assertEquals("012", StringNode.substring(str, 0, 3).toString());

        assertEquals("12", StringNode.substring(str, 1, 3).toString());
        assertNull(StringNode.substring(str, 2, 2));
        assertEquals("01", StringNode.substring(str, 0, 2).toString());
    }

    @Test
    public void testnthIndexOf() throws Exception {
        StringNode str = StringNode.convert("fifnf");
//        System.out.println(StringNode.nthIndexOf(str, 1, 'f'));
        assertEquals(0, StringNode.nthIndexOf(str, 1, 'f'));
        assertEquals(2, StringNode.nthIndexOf(str, 2, 'f'));
        assertEquals(4, StringNode.nthIndexOf(str, 3, 'f'));
        assertEquals(-1, StringNode.nthIndexOf(str, 4, 'f'));
    }

    @Test
    public void testPrintMirrored() throws Exception {
        StringNode str = StringNode.convert("012");
//        StringNode.printMirrored(str);

//        str = StringNode.convert("");
//        StringNode.printMirrored(str);
//
//        str = StringNode.convert("ab");
//        StringNode.printMirrored(str);
//
//        str = StringNode.convert("-d=");
//        StringNode.printMirrored(str);

    }


}