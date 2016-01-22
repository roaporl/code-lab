package com.lab.rpc.finagle;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.http.Http;
import com.twitter.util.Future;
import com.twitter.util.Promise;
import org.jboss.netty.handler.codec.http.*;

import java.net.InetSocketAddress;

public class Httpserver {

  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    ServerBuilder.safeBuild(new Service<HttpRequest, HttpResponse>() {

                              /**
                               * This is the method to override/implement to create your own Service.
                               */
                              @Override
                              public Future<HttpResponse> apply(HttpRequest request) {
                                Promise<HttpResponse> httpResponsePromise = new Promise<HttpResponse>();
                                httpResponsePromise.setValue(new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK));
                                return httpResponsePromise;
                              }
                            },
                            ServerBuilder.get()
                                .codec(Http.get())
                                .name("PacsSoapServer")
                                .backlog(configuration.serverConfig().tcpBackLog())
                                .maxConcurrentRequests(configuration.serverConfig().maxConcurrentRequests())
                                .keepAlive(true)
                                .bindTo(new InetSocketAddress(configuration.serverConfig().port())));
  }
}
