
package org.nuxeo.ecm.platform.oauth2.openid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@RepositoryConfig(cleanup = Granularity.METHOD)
@Deploy("org.nuxeo.ecm.directory.sql")
@Deploy("org.nuxeo.ecm.directory")
@Deploy("org.nuxeo.ecm.directory.api")
@Deploy("org.nuxeo.ecm.platform.login.openid.test")
@Deploy("org.nuxeo.ecm.platform.login.openid.test:OSGI-INF/openid-provider-contrib.xml")
public class TestProvider {

    @Test    
    public void verifyProviderRegistration() {

        OpenIDConnectProviderRegistry registry = Framework.getService(OpenIDConnectProviderRegistry.class);
        Assert.assertNotNull(registry);

        OpenIDConnectProvider provider = registry.getProvider("MY_NAME");
        Assert.assertNotNull(provider);
        Assert.assertEquals("MY_AUTHENTICATION_METHOD", provider.authenticationMethod);

    }

}