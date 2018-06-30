package youth.Config;


import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import youth.service.*;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;
    @Autowired
    private NetbarServices netbarServices;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus,netbarServices);
        endpoint.publish("/NetbarServices");//接口发布在 /NetbarServices 目录下
        return endpoint;
    }
}