package com.github.stevenkin.mybatischecker.util;

import org.jdom2.input.SAXBuilder;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XMLUtil {
    public static boolean checkXML(File xmlFile) throws Exception {
        try {
            SAXBuilder sax = new SAXBuilder();
            sax.setValidation(false);
            sax.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                    InputSource is = new InputSource(XMLUtil.class.getResourceAsStream("/mybatis-3-mapper.dtd"));
                    return is;
                }
            });
            sax.build(xmlFile);
            return true;
        } catch ( Exception e )  {
            e.printStackTrace();
            return false;
        }
    }
}
