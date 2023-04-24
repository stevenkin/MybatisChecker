package com.github.stevenkin.mybatischecker;

import com.github.stevenkin.mybatischecker.util.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
public class MapperXMLScanner {
    private static final Logger log = LoggerFactory.getLogger(MapperXMLScanner.class);
    private String scanPath;

    public MapperXMLScanner(String scanPath) {
        this.scanPath = scanPath;
    }

    public List<File> scan() {
        Queue<File> queue = new ArrayDeque<>();
        File root = new File(scanPath);
        if (!root.exists() || !root.isDirectory()) {
            throw new RuntimeException("root path must is directory");
        }
        List<File> mapperXMLfiles = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                queue.addAll(Arrays.asList(listFiles));
                continue;
            }
            try {
                if (XMLUtil.checkXML(file)) {
                    mapperXMLfiles.add(file);
                }
            } catch (Exception e) {
                log.error("check xml error", e);
            }
        }
        return mapperXMLfiles;
    }

}
