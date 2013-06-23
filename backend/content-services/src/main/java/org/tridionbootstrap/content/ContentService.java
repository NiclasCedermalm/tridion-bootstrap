package org.tridionbootstrap.content;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.assets.AssetsBundle;
//import com.yammer.dropwizard.views.ViewBundle;

/**
 * Content Service
 */
public class ContentService extends Service<ContentConfiguration> {


    public static void main(String[] args) throws Exception {
        new ContentService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ContentConfiguration> bootstrap) {
        bootstrap.setName("content-service");
    }

    @Override
    public void run(ContentConfiguration configuration,
                    Environment environment) {
        environment.addResource(new ContentResource());
        environment.addFilter(new ContentFilter(), "/*");
        // TODO: Add an asset bundle + content bundle


        // TODO: Use https://github.com/bazaarvoice/dropwizard-configurable-assets-bundle
    }

}
