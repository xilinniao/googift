package cn.googift.crawler.util.page;

import junit.framework.TestCase;

public class XMLTagRemoverTest extends TestCase {
    public void testRemoveTag() {
        // asd<ta1 name="asdf">asasd</ta1>
        String xml = "asd<ta1 name=\"asdf\">asasd</ta1>";
        assertEquals("asdasasd", XMLTagRemover.removeTag(xml));
    }
}
