package cn.googift.search;

import java.io.File;

public interface SearchConstants {
    String FielName_GUID = "guid";
    String FielName_NAME = "name";
    String FielName_PRICE = "price";
    String FielName_CATEGORIES = "categories";
    String FielName_DESCRIPTION = "description";

    File indexDir = new File("c:\\__index");
}
