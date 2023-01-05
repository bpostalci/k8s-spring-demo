package tr.com.swe599.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j(topic = "k8s-demo")
@RestController
public class DemoController {
    public static final AtomicLong firstEndpointCounter = new AtomicLong(0);
    public static final AtomicLong secondEndpointCounter = new AtomicLong(0);
    public static final AtomicLong thirdEndpointCounter = new AtomicLong(0);

    private String serverAddress = "";

    DemoController() {
        try {
            this.serverAddress = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            log.warn("cannot retrieve server address", e);
        }
    }

    @RequestMapping(value = "/")
    public String root() {
        return "<h1>SWE599 k8s demo</h1>";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String first() {

        log.info("---- /first ----");
        log.info("first endpoint count:" + firstEndpointCounter.incrementAndGet());
        log.info("server address: " + serverAddress);
        log.info("---- /first ----\n");

        return "<p>first endpoint is called with count=" + firstEndpointCounter.get() + "</p><br><p>server address:" + serverAddress + "</p>";
    }

    @RequestMapping(value = "/second", method = RequestMethod.POST)
    public String second(@RequestBody String body) {

        log.info("---- /second ----");
        log.info("body: " + body);
        log.info("second endpoint count:" + secondEndpointCounter.incrementAndGet());
        log.info("server address: " + serverAddress);
        log.info("---- /second ----\n");

        return "<p>second endpoint is called with count=" + secondEndpointCounter.get() + "</p><br><p>server address:" + serverAddress + "</p>";
    }

    @RequestMapping(value = "/third", method = RequestMethod.GET)
    public String third(@RequestParam String param) {

        log.info("---- /third ----");
        log.info("param: " + param);
        log.info("third endpoint count:" + thirdEndpointCounter.incrementAndGet());
        log.info("server address: " + serverAddress);
        log.info("---- /third ----\n");

        return "<p>third endpoint is called with count=" + thirdEndpointCounter.get() + "</p><br><p>server address:" + serverAddress + "</p>";
    }

}
