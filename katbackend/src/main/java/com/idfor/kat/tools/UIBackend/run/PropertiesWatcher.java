/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.idfor.kat.tools.UIBackend.run;

import com.idfor.kat.tools.UIBackend.beans.KatBackendProperties;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Class that implements a managed service to watch changes made to the KAT Backend configuration file
 * @author IDFOR-Solutions
 */
public class PropertiesWatcher {

    private final Logger LOGGER = LoggerFactory.getLogger(PropertiesWatcher.class);

    private BundleContext bundleContext;
    private ServiceRegistration propertiesService;

    /**
     * This method is wired to blueprint via a bean definition. It initializes the configuration
     * at feature installation
     * @throws IOException
     */
    public void init() throws IOException {
        Dictionary properties = new Hashtable();
        properties.put("service.pid", "com.idfor.kat.tools.katbackend");
        propertiesService = bundleContext.registerService(ManagedService.class.getName(), new KatBackendProperties(), properties);
        ServiceReference serviceReference = bundleContext.getServiceReference(ConfigurationAdmin.class);
        if (serviceReference != null) {
            ConfigurationAdmin configurationAdmin = (ConfigurationAdmin) bundleContext.getService(serviceReference);
            if (configurationAdmin != null) {
                Configuration configuration = configurationAdmin.getConfiguration("com.idfor.kat.tools.katbackend");
                if(configuration != null) {
                    properties = configuration.getProperties();
                    KatBackendProperties.update(properties);
                }
                else LOGGER.error("Config file com.idfor.kat.tools.katbackend has not been found !");
            }
        }
    }

    /**
     * This method is wired to blueprint via a bean definition. It destroys the configuration
     * at feature un-installation
     */
    public void destroy() {
        if (propertiesService != null) {
            propertiesService.unregister();
            propertiesService = null;
        }
    }

    /**
     * This method is wired to blueprint via a property. It stores the bundle context passed
     * by the blueprint
     */
    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }
}