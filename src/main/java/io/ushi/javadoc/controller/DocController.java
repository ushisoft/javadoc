package io.ushi.javadoc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Doc主页控制类
 * <p>
 * Created by zhouleibo on 2017/8/30.
 */
@RestController
@RequestMapping("/doc")
public class DocController {

    @RequestMapping(value = "/group/{gid}/artifacts", method = RequestMethod.GET)
    public List<String> artifacts(@PathVariable("gid") String groupId) {

        List<String> g1 = new ArrayList<String>();
        g1.add("A");
        g1.add("B");

        List<String> g2 = new ArrayList<String>();
        g2.add("C");
        g2.add("D");

        Map<String, List<String>> mock = new HashMap<String, List<String>>();
        mock.put("org.springframework", g1);
        mock.put("com.qjdchina", g2);

        return mock.get(groupId);
    }

    @RequestMapping(value = "/group/{gid}/artifact/{aid}/versions", method = RequestMethod.GET)
    public List<String> versions(@PathVariable("gid") String groupId, @PathVariable("aid") String artifactId) {

        List<String> g1 = new ArrayList<String>();
        g1.add("E");
        g1.add("F");

        List<String> g2 = new ArrayList<String>();
        g2.add("G");
        g2.add("H");

        return artifactId.equals("common") ? g1 : g2;
    }
}
